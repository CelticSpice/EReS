/**
    The main frame of the application
    CSC-289 - Group 4
    @author Jessica Gay, Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{    
    /**
        Constructs a new MainFrame
    */
    
    public MainFrame()
    {
        setTitle("Event Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        add(new MainPanel(this));

        setVisible(true);
    }
}