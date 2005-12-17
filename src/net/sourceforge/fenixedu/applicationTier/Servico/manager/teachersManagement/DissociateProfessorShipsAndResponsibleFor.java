package net.sourceforge.fenixedu.applicationTier.Servico.manager.teachersManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.NonExistingServiceException;
import net.sourceforge.fenixedu.dataTransferObject.InfoProfessorshipWithAll;
import net.sourceforge.fenixedu.domain.IProfessorship;
import net.sourceforge.fenixedu.domain.ISummary;
import net.sourceforge.fenixedu.domain.ITeacher;
import net.sourceforge.fenixedu.domain.Professorship;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.IPersistentProfessorship;
import net.sourceforge.fenixedu.persistenceTier.IPersistentShiftProfessorship;
import net.sourceforge.fenixedu.persistenceTier.IPersistentSummary;
import net.sourceforge.fenixedu.persistenceTier.IPersistentTeacher;
import net.sourceforge.fenixedu.persistenceTier.ISuportePersistente;
import net.sourceforge.fenixedu.persistenceTier.PersistenceSupportFactory;
import net.sourceforge.fenixedu.persistenceTier.teacher.professorship.IPersistentSupportLesson;
import pt.utl.ist.berserk.logic.serviceManager.IService;

/**
 * @author Fernanda Quit�rio 12/Dez/2003
 * 
 */
public class DissociateProfessorShipsAndResponsibleFor implements IService {

	public Map run(Integer teacherNumber, List professorships, List responsibleFors)
			throws FenixServiceException, ExcepcaoPersistencia {
		List professorshipsWithSupportLessons = new ArrayList();
		List professorshipsWithShifts = new ArrayList();
		HashMap professorshipsNotRemoved = new HashMap();

		ISuportePersistente sp = PersistenceSupportFactory.getDefaultPersistenceSupport();
		IPersistentTeacher teacherDAO = sp.getIPersistentTeacher();
		IPersistentProfessorship persistentProfessorship = sp.getIPersistentProfessorship();
		IPersistentSupportLesson persistentSupportLesson = sp.getIPersistentSupportLesson();
		IPersistentShiftProfessorship persistentShiftProfessorship = sp
				.getIPersistentShiftProfessorship();

		if (teacherNumber == null) {
			throw new FenixServiceException("nullTeacherNumber");
		}

		ITeacher teacher = teacherDAO.readByNumber(teacherNumber);
		if (teacher == null) {
			throw new NonExistingServiceException("noTeacher");
		}

		if (professorships != null && responsibleFors != null) {
			List newProfessorships = new ArrayList();
			Iterator iterProfessorships = professorships.iterator();
			while (iterProfessorships.hasNext()) {
				Integer professorshipId = (Integer) iterProfessorships.next();

				IProfessorship professorship = (IProfessorship) persistentProfessorship.readByOID(
						Professorship.class, professorshipId);
				if (professorship == null) {
					throw new FenixServiceException("nullPSNorRF");
				}
				if (!professorship.getTeacher().equals(teacher)) {
					throw new FenixServiceException("notPSNorRFTeacher");
				}
				newProfessorships.add(professorship);
			}

			List newResponsibleFor = new ArrayList();
			Iterator iterResponsibleFor = responsibleFors.iterator();
			while (iterResponsibleFor.hasNext()) {
				Integer responsibleForId = (Integer) iterResponsibleFor.next();

				IProfessorship responsibleFor = (IProfessorship) persistentProfessorship.readByOID(
						Professorship.class, responsibleForId);

				if (responsibleFor == null) {
					throw new FenixServiceException("nullPSNorRF");
				}
				if (!responsibleFor.getTeacher().equals(teacher)) {
					throw new FenixServiceException("notPSNorRFTeacher");
				}
				newResponsibleFor.add(responsibleFor);
			}

			// everything is ok for removal, but first check
			// professorships with support lessons and shifts
			iterProfessorships = newProfessorships.iterator();
			while (iterProfessorships.hasNext()) {
				IProfessorship professorship = (IProfessorship) iterProfessorships.next();

				List supportLessons = persistentSupportLesson.readByProfessorship(professorship
						.getIdInternal());
				List shiftProfessorships = persistentShiftProfessorship
						.readByProfessorship(professorship);

				if ((shiftProfessorships == null || shiftProfessorships.size() == 0)
						&& (supportLessons == null || supportLessons.size() == 0)) {

					IPersistentSummary persistentSummary = sp.getIPersistentSummary();
					List summaryList = persistentSummary.readByTeacher(professorship
							.getExecutionCourse().getIdInternal(), professorship.getTeacher()
							.getTeacherNumber());
					if (summaryList != null && !summaryList.isEmpty()) {
						for (Iterator iterator = summaryList.iterator(); iterator.hasNext();) {
							ISummary summary = (ISummary) iterator.next();
							persistentSummary.simpleLockWrite(summary);
							summary.setProfessorship(null);
							summary.setKeyProfessorship(null);
						}
					}
					deleteProfessorship(persistentProfessorship, professorship);

				} else {
					if (supportLessons.size() > 0) {
						professorshipsWithSupportLessons.add(InfoProfessorshipWithAll
								.newInfoFromDomain(professorship));
					}
					if (shiftProfessorships.size() > 0) {
						professorshipsWithShifts.add(InfoProfessorshipWithAll
								.newInfoFromDomain(professorship));
					}
				}

			}

			iterResponsibleFor = newResponsibleFor.iterator();
			while (iterResponsibleFor.hasNext()) {
				IProfessorship responsibleFor = (IProfessorship) iterResponsibleFor.next();
				responsibleFor.setResponsibleFor(false);
			}
		}

		if (professorshipsWithSupportLessons.size() > 0 || professorshipsWithShifts.size() > 0) {
			professorshipsNotRemoved.put(new String("supportLessons"), professorshipsWithSupportLessons);
			professorshipsNotRemoved.put(new String("shifts"), professorshipsWithShifts);
		}

		return professorshipsNotRemoved;
	}

	private void deleteProfessorship(final IPersistentProfessorship persistentProfessorship,
			final IProfessorship professorshipToDelete) throws ExcepcaoPersistencia {

		if (professorshipToDelete.getAssociatedSummaries() != null)
			professorshipToDelete.getAssociatedSummaries().clear();
		if (professorshipToDelete.getSupportLessons() != null)
			professorshipToDelete.getSupportLessons().clear();

		if (professorshipToDelete.getAssociatedShiftProfessorship() != null)
			professorshipToDelete.getAssociatedShiftProfessorship().clear();

		if (professorshipToDelete.getExecutionCourse().getProfessorships() != null) {
			professorshipToDelete.getExecutionCourse().getProfessorships().remove(professorshipToDelete);
		}
		professorshipToDelete.setExecutionCourse(null);
		if (professorshipToDelete.getTeacher().getProfessorships() != null) {
			professorshipToDelete.getTeacher().getProfessorships().remove(professorshipToDelete);
		}
		professorshipToDelete.setTeacher(null);
		persistentProfessorship.deleteByOID(Professorship.class, professorshipToDelete.getIdInternal());
	}
}