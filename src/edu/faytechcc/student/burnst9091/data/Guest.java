/**
    A guest using the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.mccanns0131.database.RecordAdd;
import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Guest
{
    /**
        EmailAdmin - Send an email to the administrator of the system
    
        @param name The name to include in the email
        @param address The email address that may be replied to
        @param message The message to send to the administrator
        @throws AddressException Malformed email address
        @throws MessagingException Error sending message
        @throws UnsupportedEncodingException Failed to encode name in address
    */
    
    public static void emailAdmin(String name, String address, String message)
            throws AddressException, MessagingException,
                   UnsupportedEncodingException
    {
        EmailUtil.emailAdmin(name, address, message);
    }
    
    /**
        MakeReservation - Guest makes a reservation at the given reservable
        & provides contact information, an expected number of attendees, &
        the type of event the reservation entails
    
        @param reservable The reservable the guest reserves
        @param contactInfo The guest's provided contact information
        @param attendence The expected number of attendees
        @param type The event type
        @throws SQLException Error adding the record of the reservation to the
                             database
        @throws RecordExistsException A record of an identical reservation
                                      exists in the database
        @return reservation The reservation made
    */
    
    public static Reservation makeReservation(Reservable reservable,
            ContactInfo contactInfo, int attendence, String type)
            throws SQLException, RecordExistsException
    {
        Reserver reserver = new Reserver(contactInfo);
        Reservation reservation = new Reservation(reserver, reservable,
                                                  attendence, type);
        new RecordAdd().addReservation(reservation);
        return reservation;
    }
}