/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

public class Reserver
{
    // Fields
    private String firstName, lastName, email, phone;
    
    /**
        Constructs a new Reserver with the given first & last names,
        email address, & phone number
    
        @param fName First name
        @param lName Last name
        @param e Email address
        @param p Phone number
    */
    
    public Reserver(String fName, String lName, String e, String p)
    {
        firstName = fName;
        lastName = lName;
        email = e;
        phone = p;
    }
    
    /**
        Returns the email address of the Reserver
    
        @return The email address of the Reserver
    */
    
    public String getEmailAddress()
    {
        return email;
    }
    
    /**
        Returns the first name of the reserver
    
        @return The first name of the reserver
    */
    
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
        Returns the last name of the reserver
    
        @return The last name of the reserver
    */
    
    public String getLastName()
    {
        return lastName;
    }
    
    /**
        Returns the full name of the reserver
    
        @return The full name of the Reserver
    */
    
    public String getName()
    {
        return firstName + " " + lastName;
    }
    
    /**
        Returns the phone number of the Reserver
    
        @return The phone number of the Reserver
    */
    
    public String getPhoneNumber()
    {
        return phone;
    }
}