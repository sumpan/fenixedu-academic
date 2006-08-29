package net.sourceforge.fenixedu.domain.degree.enrollment.rules;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import net.sourceforge.fenixedu.domain.CurricularCourse;
import net.sourceforge.fenixedu.domain.CurricularCourseGroup;
import net.sourceforge.fenixedu.domain.Enrolment;
import net.sourceforge.fenixedu.domain.ExecutionPeriod;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.degree.enrollment.CurricularCourse2Enroll;
import net.sourceforge.fenixedu.domain.exceptions.EnrolmentRuleDomainException;

public class LETBolonhaEnrolmentRule extends BolonhaEnrolmentRule {

    private static final String PROJECTOI_CODE = "B5Z";

    private static final String DISSERTACAO_CODE = "$60";

    private static final String[] COMMONS = { "AFG", "B50", "ACZ", "AFL" };

    private static final String[] DEGREE = { "ACY", "AH0", "AFC", "AF6", "AF5", "OP", "4I", "0T", "81" };

    public LETBolonhaEnrolmentRule(StudentCurricularPlan studentCurricularPlan,
	    ExecutionPeriod executionPeriod) {
	super(studentCurricularPlan, executionPeriod);
    }

    public List<CurricularCourse2Enroll> apply(
	    List<CurricularCourse2Enroll> curricularCoursesToBeEnrolledIn)
	    throws EnrolmentRuleDomainException {

	final boolean projectoI = isEnrolledInExecutionPeriodOrAproved(PROJECTOI_CODE);
	final boolean dissertacao = isEnrolledInExecutionPeriodOrAproved(DISSERTACAO_CODE);

	if (projectoI) {
	    removeCurricularCourse(curricularCoursesToBeEnrolledIn, DISSERTACAO_CODE);
	}
	
	if (dissertacao) {
	    removeCurricularCourse(curricularCoursesToBeEnrolledIn, PROJECTOI_CODE);
	    removeCurricularCourses(curricularCoursesToBeEnrolledIn, Arrays.asList(DEGREE));
	}

	if (!projectoI && !dissertacao) {
	    removeCurricularCourses(curricularCoursesToBeEnrolledIn, Arrays.asList(DEGREE));
	    //removeCurricularCourses(curricularCoursesToBeEnrolledIn, Arrays.asList(COMMONS));
	}

	return curricularCoursesToBeEnrolledIn;
    }
}
