/**
    Dialog for the guest to make a reservation & provide reservation details
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.mccanns0131.database.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeReservationDialog extends JDialog
{
    private JButton reserve, cancel;
    private JTextField location, capacity, startDate, startTime, endDate,
            endTime, cost, firstName, lastName, email, phone;
    
    /**
        Constructs a new MakeReservationDialog, initialized with the specified
        reservable that is to be reserved
    
        @param r The reservable to be reserved
    */
    
    public MakeReservationDialog(Reservable r)
    {
        setLayout(new BorderLayout());
        setTitle("Make Reservation");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(r), BorderLayout.CENTER);
        add(buildButtonPanel(r), BorderLayout.SOUTH);
        
        pack();
    }
    
    /**
        Builds & returns the button panel of the dialog, initialized with the
        reservable that is to be reserved
        
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildButtonPanel(Reservable r)
    {
        JPanel panel = new JPanel();
        
        panel.add(reserve = new JButton("Reserve"));
        panel.add(cancel = new JButton("Cancel"));
        
        ButtonController controller = new ButtonController(r);
        
        reserve.addActionListener(controller);
        cancel.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the lower panel of the reservable info panel,
        initialized with the specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildLowerReservableInfoPanel(Reservable r)
    {
        JPanel panel = new JPanel();
        
        panel.add(cost = new JTextField(5));
        
        cost.setText(r.getTimeframe().getCostString());
        
        cost.setEditable(false);
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog, initialized with the
        specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildMainPanel(Reservable r)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        panel.add(buildReservableInfoPanel(r));
        panel.add(buildReservationInfoPanel());
        
        return panel;
    }
    
    /**
        Builds & returns the middle panel of the reservable info panel,
        initialized with the specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildMidReservableInfoPanel(Reservable r)
    {
        JPanel panel = new JPanel();
        
        // Build start datetime panel
        JPanel startDateTimePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        startDateTimePanel.add(new JLabel("Start Date:"));
        startDateTimePanel.add(startDate = new JTextField(7));
        
        startDateTimePanel.add(new JLabel("Start Time:"));
        startDateTimePanel.add(startTime = new JTextField(7));
        
        // Build end datetime panel
        JPanel endDateTimePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        endDateTimePanel.add(new JLabel("End Date:"));
        endDateTimePanel.add(endDate = new JTextField(7));
        
        endDateTimePanel.add(new JLabel("End Time:"));
        endDateTimePanel.add(endTime = new JTextField(7));
        
        startDate.setText(r.getStartDate().toString());
        startTime.setText(r.getStartTime().toString());
        endDate.setText(r.getEndDate().toString());
        endTime.setText(r.getEndTime().toString());
        
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        
        panel.add(startDateTimePanel);
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(endDateTimePanel);
        
        return panel;
    }
    
    /**
        Builds & returns the panel containing information about the reservable,
        initialized with the specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildReservableInfoPanel(Reservable r)
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Reservable Info"));
        
        panel.add(buildUpperReservableInfoPanel(r), BorderLayout.NORTH);
        panel.add(buildMidReservableInfoPanel(r), BorderLayout.CENTER);
        panel.add(buildLowerReservableInfoPanel(r), BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
        Builds & returns the panel containing information about the reservation
    
        @return The built panel
    */
    
    private JPanel buildReservationInfoPanel()
    {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Reservation Info"));
        
        panel.add(new JLabel("First Name:"));
        panel.add(firstName = new JTextField(7));
        
        panel.add(new JLabel("Last Name:"));
        panel.add(lastName = new JTextField(7));
        
        panel.add(new JLabel("Email:"));
        panel.add(email = new JTextField(7));
        
        panel.add(new JLabel("Phone:"));
        panel.add(phone = new JTextField(7));
        
        return panel;
    }
    
    /**
        Builds & returns the upper panel of the reservable info panel,
        initialized with the specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildUpperReservableInfoPanel(Reservable r)
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        panel.add(new JLabel("Location:"));
        panel.add(location = new JTextField(7));
        
        panel.add(new JLabel("Capacity:"));
        panel.add(capacity = new JTextField(3));
        
        location.setText(r.getName());
        capacity.setText(String.valueOf(r.getCapacity()));
        
        location.setEditable(false);
        capacity.setEditable(false);
        
        return panel;
    }
    
    /**
        Controller for the dialog's buttons
    */
    
    private class ButtonController implements ActionListener
    {
        private Reservable reservable;
        
        /**
            Constructs a new ButtonController initialized with the specified
            reservable that is to be reserved
        
            @param r The reservable to be reserved
        */
        
        public ButtonController(Reservable r)
        {
            reservable = r;
        }
        
        /**
            Performs an action on a button being clicked
        
            @param e The ActionEvent
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == reserve)
            {
                if (validateInput())
                {
                    makeReservation();
                    dispose();
                }
            }
            else
                dispose();
        }
        
        /**
            Displays a warning message
        
            @param mesg Warning message to display
        */
        
        private void displayWarning(String mesg)
        {
            JOptionPane.showMessageDialog(null, mesg, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        /**
            Makes & records a reservation
        */
        
        private void makeReservation()
        {
            SystemPreferences prefs = SystemPreferences.getInstance();
            
            try
            {
                DatabaseConnection conn = DatabaseConnection.getConnection(
                    prefs.getDBSettings());
                
                // Create reserver
                String fName = firstName.getText();
                String lName = lastName.getText();
                String e = email.getText();
                String p = phone.getText();
                Reserver reserver = new Reserver(fName, lName, e, p);
                
                // Get reservation info
                String 
                
                // Create reservation
                
            }
            catch (SQLException ex)
            {
                
            }
        }
        
        /**
            Validates the user's input
        
            @return If the user's input is valid
        */
        
        private boolean validateInput()
        {
            if (firstName.getText().isEmpty())
            {
                displayWarning("First name must be entered");
                return false;
            }
            
            if (lastName.getText().isEmpty())
            {
                displayWarning("Last name must be entered");
                return false;
            }
            
            String pattern = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
            
            if (!email.getText().matches(pattern))
            {
                displayWarning("Invalid email address entered");
                return false;
            }
            
            pattern =
                "(?:\\d{1}\\s)?\\(?(\\d{3})\\)?-?\\s?(\\d{3})-?\\s?(\\d{4})";
            
            if (!phone.getText().matches(pattern))
            {
                displayWarning("Invalid phone number entered");
                return false;
            }
            
            return true;
        }
    }
}