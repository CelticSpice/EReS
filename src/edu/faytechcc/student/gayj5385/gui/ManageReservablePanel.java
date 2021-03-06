/**
    Admin panel with components enabling the administrator to manage
    reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.gayj5385.gui.renderer.TimeframeRenderer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class ManageReservablePanel extends JPanel
{
    // Fields
    private DefaultListModel timeframes;
    private JButton add, update, delete, searchBtn, clear, help, createReport;
    private JComboBox<ReservableLocation> locations;
    private JList<ReservableTimeframe> timeframeList;
    private JTextField capacity, search, startDate, startTime, endDate, endTime,
                       cost, reserved;

    /**
        Constructs a ManageReservablePanel initialized with the given locations
    */

    public ManageReservablePanel()
    {
        super(new BorderLayout());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildMidPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }

    /**
        Builds & return the bottom panel of this panel

        @return The built panel
    */

    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(add = new JButton("Add"));
        panel.add(update = new JButton("Update"));
        panel.add(delete = new JButton("Delete"));
        panel.add(createReport = new JButton("Create Report"));

        return panel;
    }

    /**
        Build & return the middle panel of this panel

        @return The built panel
    */

    private JPanel buildMidPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        timeframeList = new JList(timeframes = new DefaultListModel());
        timeframeList.setCellRenderer(new TimeframeRenderer());
        JScrollPane scrollPane = new JScrollPane(timeframeList);
        scrollPane.setPreferredSize(new Dimension(255, 225));

        // If the list of locations used to construct the ManageReservablePanel
        // is not empty, display the first location's timeframes initially
        ReservableLocation loc = (ReservableLocation) locations.getSelectedItem();
        if (loc != null)
            setTimeframes(loc.getTimeframes());

        // Build timeframe detail panel
        JPanel timeframeDetailPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        timeframeDetailPanel.add(new JLabel("Start Date:"));
        timeframeDetailPanel.add(startDate = new JTextField(7));
        timeframeDetailPanel.add(new JLabel("Start Time:"));
        timeframeDetailPanel.add(startTime = new JTextField(7));
        timeframeDetailPanel.add(new JLabel("End Date:"));
        timeframeDetailPanel.add(endDate = new JTextField(7));
        timeframeDetailPanel.add(new JLabel("End Time:"));
        timeframeDetailPanel.add(endTime = new JTextField(7));
        timeframeDetailPanel.add(new JLabel("Cost:"));
        timeframeDetailPanel.add(cost = new JTextField(7));
        timeframeDetailPanel.add(new JLabel("Reserved:"));
        timeframeDetailPanel.add(reserved = new JTextField(7));

        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        cost.setEditable(false);
        reserved.setEditable(false);

        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(timeframeDetailPanel);

        return panel;
    }

    /**
        Builds & returns the top panel of this panel

        @return The built panel
    */

    private JPanel buildTopPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Build location selection panel
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel locationComponentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 5);
        locationComponentPanel.add(new JLabel("Location:"), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.ipadx = 125;
        locationComponentPanel.add(locations = new JComboBox(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.ipadx = 0;
        locationComponentPanel.add(new JLabel("Capacity:"), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        locationComponentPanel.add(capacity = new JTextField(5), gbc);

        capacity.setEditable(false);

        locationPanel.add(locationComponentPanel);

        // Build search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel searchComponentPanel = new JPanel(new GridBagLayout());

        JPanel searchButtonsPanel = new JPanel(new FlowLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        searchComponentPanel.add(search = new JTextField(15), gbc);

        searchButtonsPanel.add(searchBtn = new JButton("Search"));
        searchButtonsPanel.add(clear = new JButton("Clear"));
        searchButtonsPanel.add(help = new JButton("Help"));

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        searchComponentPanel.add(searchButtonsPanel, gbc);

        searchPanel.add(searchComponentPanel);

        panel.add(locationPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(searchPanel);

        return panel;
    }

    /**
        Clears the search criteria
    */
    
    public void clearSearch()
    {
        search.setText(null);
    }

    /**
        Returns the search criteria within the search text field
     
        @return The text within the search text field
    */
    
    public String getSearchCriteria()
    {
        return search.getText();
    }

    /**
        Return the selected location

        @return The selected location
    */

    public ReservableLocation getSelectedLocation()
    {
        return (ReservableLocation) locations.getSelectedItem();
    }

    /**
        Return the selected timeframes

        @return The selected timeframes
    */

    public List<ReservableTimeframe> getSelectedTimeframes()
    {
        return timeframeList.getSelectedValuesList();
    }

    /**
        Register a button controller to the panel

        @param controller The controller to register to the buttons on the panel
    */

    public void registerButtonController(ActionListener controller)
    {
        add.addActionListener(controller);
        update.addActionListener(controller);
        delete.addActionListener(controller);
        searchBtn.addActionListener(controller);
        clear.addActionListener(controller);
        help.addActionListener(controller);
        createReport.addActionListener(controller);
    }

    /**
        Register a controller to the locations combo box

        @param controller The controller to register to the locations combo box
    */

    public void registerComboBoxController(ActionListener controller)
    {
        locations.addActionListener(controller);
    }

    /**
        Register a controller to the timeframe list

        @param controller The controller to register to the timeframe list
    */

    public void registerTimeframeListController(
            ListSelectionListener controller)
    {
        timeframeList.addListSelectionListener(controller);
    }

    /**
        Set the locations that can be reserved

        @param locs Locations that can be reserved
    */

    public void setLocations(List<ReservableLocation> locs)
    {
        locations.removeAllItems();
        for (ReservableLocation loc : locs)
            locations.addItem(loc);
    }

    /**
        Set the capacity of the selected location

        @param cap Capacity to display
    */

    public void setCapacity(String cap)
    {
        capacity.setText(cap);
    }

    /**
        Set the cost field

        @param c Cost to set in the cost field
    */

    public void setCost(String c)
    {
        cost.setText(c);
    }

    /**
        Set the end date field

        @param date Date to set in the end date field
    */

    public void setEndDate(String date)
    {
        endDate.setText(date);
    }

    /**
        Set the end time field

        @param time Time to set in the end time field
    */

    public void setEndTime(String time)
    {
        endTime.setText(time);
    }

    /**
        Set the reserved field

        @param reserve Value to set in the reserved field
    */

    public void setReserved(String reserve)
    {
        reserved.setText(reserve);
    }
    
    /**
        Sets the selected location to the specified location
    
        @param loc The location to set as selected
    */
    
    public void setSelectedLocation(ReservableLocation loc)
    {
        locations.setSelectedItem(loc);
    }

    /**
        Set the start date field

        @param date Date to set in the start date field
    */

    public void setStartDate(String date)
    {
        startDate.setText(date);
    }

    /**
        Set the start time field

        @param time Time to set in the start time field
    */

    public void setStartTime(String time)
    {
        startTime.setText(time);
    }

    /**
        Set the timeframes displayed in the list

        @param times Timeframes to display in the list
    */

    public void setTimeframes(List<ReservableTimeframe> times)
    {
        timeframes.removeAllElements();
        if (times != null)
            for (ReservableTimeframe timeframe : times)
                timeframes.addElement(timeframe);
    }
}
