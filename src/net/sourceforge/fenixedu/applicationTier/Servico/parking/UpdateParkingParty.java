package net.sourceforge.fenixedu.applicationTier.Servico.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import net.sourceforge.fenixedu.applicationTier.Service;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.parking.ParkingGroup;
import net.sourceforge.fenixedu.domain.parking.ParkingParty;
import net.sourceforge.fenixedu.domain.parking.ParkingRequest;
import net.sourceforge.fenixedu.domain.parking.ParkingRequestState;
import net.sourceforge.fenixedu.util.LanguageUtils;

import org.joda.time.DateTime;

import pt.utl.ist.fenix.tools.smtp.EmailSender;

public class UpdateParkingParty extends Service {

    public void run(ParkingRequest parkingRequest, final ParkingRequestState parkingRequestState,
            final Long cardCode, final Integer groupId, final String note, final DateTime cardStartDate,
            final DateTime cardEndDate) {
        
        ParkingParty parkingParty = parkingRequest.getParkingParty();
        if (parkingRequestState == ParkingRequestState.ACCEPTED) {               
            parkingParty.setCardStartDate(cardStartDate);
            parkingParty.setCardEndDate(cardEndDate);
            parkingParty.setAuthorized(true);
            parkingParty.setCardNumber(cardCode);
            
            parkingParty.edit(parkingRequest);

            ParkingGroup parkingGroup = rootDomainObject.readParkingGroupByOID(groupId);
            parkingParty.setParkingGroup(parkingGroup);
        }
        parkingRequest.setParkingRequestState(parkingRequestState);
        parkingRequest.setNote(note);

        String email = ((Person) parkingParty.getParty()).getEmail();

        if (note != null && note.trim().length() != 0 && email != null) {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.ParkingResources", LanguageUtils
                    .getLocale());
            List<String> to = new ArrayList<String>();
            to.add(email);
            if (EmailSender.send(bundle.getString("label.fromName"),
                    bundle.getString("label.fromAddress"), to, null, null,
                    bundle.getString("label.subject"), note).isEmpty()) {
            }
        }
    }
}