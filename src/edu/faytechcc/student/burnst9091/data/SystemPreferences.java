/**
    Preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.security.NoSuchAlgorithmException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SystemPreferences
{
    // Fields
    private EmailPreferences emailPrefs;
    private Preferences prefs;
    
    /**
        Constructor - Accepts the root node of the system preferences
    
        @param root Root node of system preferences
    */
    
    public SystemPreferences(String root)
    {
        prefs = Preferences.userRoot().node(root);
        emailPrefs = new EmailPreferences(prefs.absolutePath() + "/Email");
    }
    
    /**
        GetAdminGetAddress - Return the address for the administrator to
        receive email at
    
        @return The address for the administrator to receive email at
    */
    
    public String getAdminGetAddress()
    {
        return emailPrefs.getAdminGetAddress();
    }
    
    /**
        GetAdminSMTPProperties - Return properties of the administrator's
        SMTP server setup
    
        @return props Properties of the administrator's SMTP server setup
    */
    
    public SMTPProperties getAdminSMTPProperties()
    {
        return emailPrefs.getAdminSMTPProperties();
    }
    
    /**
        GetDBPass - Return the password for the database
    
        @return The password for the database
    */
    
    public String getDBPass()
    {
        return prefs.get("DBPass", "Password");
    }
    
    /**
        GetDBUser - Return the username for the database
    
        @return The username for the database
    */
    
    public String getDBUser()
    {
        return prefs.get("DBUser", "Username");
    }
    
    /**
        GetGuestSMTPProperties - Return properties of the guest's
        SMTP server setup
    
        @return props Properties of the guest's SMTP server setup
    */
    
    public SMTPProperties getGuestSMTPProperties()
    {
        return emailPrefs.getGuestSMTPProperties();
    }
    
    /**
        Init - Initialize the system preferences with default values
    
        @param saltHasher For salting & hashing initial password, if needed
        @throws BackingStoreException Error communicating with preferences
        @throws NoSuchAlgorithmException Error hashing initial password
    */
    
    public void init(SHA256SaltHasher saltHasher)
            throws BackingStoreException, NoSuchAlgorithmException
    {
        if (prefs.get("AdminPass", "").equals(""))
            prefs.put("AdminPass", saltHasher.saltHash(""));            
    }
    
    /**
        SetAdminGetAddress - Set the address the administrator will receive
        emails at
    
        @param address Address the administrator will receive emails at
    */
    
    public void setAdminGetAddress(String address)
    {
        emailPrefs.setAdminGetAddress(address);
    }
    
    /**
        SetAdminSMTPPrefs - Set preferences for the administrator's SMTP server
    
        @param props Preferences of the administrator's SMTP server
    */
    
    public void setAdminSMTPPrefs(SMTPProperties props)
    {
        emailPrefs.setAdminSMTPPrefs(props);
    }
    
    /**
        SetDBPass - Set the password for the database
    
        @param pass The password for the database
    */
    
    public void setDBPass(String pass)
    {
        prefs.put("DBPass", pass);
    }
    
    /**
        SetDBUser - Set the username for the database
    
        @param user The username for the database
    */
    
    public void setDBUser(String user)
    {
        prefs.put("DBUser", user);
    }
    
    /**
        SetGuestSMTPPrefs - Set preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public void setGuestSMTPPrefs(SMTPProperties props)
    {
        emailPrefs.setGuestSMTPPrefs(props);
    }
    
    /**
        UpdateAdminPassword - Update the administrator's password
    
        @param pass The updated administrator password
    */
    
    public void updateAdminPassword(String pass)
    {
        prefs.put("AdminPass", pass);
    }
    
    /**
        ValidateAdminPassword - Validate a string against the stored
        administrator password
    
        @param pass The string to validate against the stored administrator
                    password
        @return Whether the string & the stored administrator password match
    */
    
    public boolean validateAdminPassword(String pass)
    {
        return pass.equals(prefs.get("AdminPass", ""));
    }
}