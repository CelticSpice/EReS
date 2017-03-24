/**
    Controller for the locations combo box on the panel enabling the
    administrator to manage reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManageReservableComboBoxController implements ActionListener
{
    // Fields
    private ManageReservablePanel view;
    private Filter<Reservable> filter;
    
    /**
        Constructor - Accepts the view to manage the combo box of
    
        @param v The view
     * @param f
    */
    
    public ManageReservableComboBoxController(ManageReservablePanel v,
            Filter<Reservable> f)
    {
        view = v;
        filter = f;
    }
    
    /**
        Handle combo box action events
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Location location = view.getSelectedLocation();
        view.setCapacity(String.valueOf(location.getCapacity()));
        
        List<Timeframe> timeframes;
        
        if (filter.getPredicate() != null)
        {
            try
            {
                List<Reservable> reservables = new ArrayList();

                for (Location loc : locations)
                {
                    reservables.addAll(loc.deriveReservables());
                }
                
                List<Reservable> filteredReservables;
                filteredReservables = reservables.stream()
                        .filter(filter.getPredicate())
                        .collect(Collectors.<Reservable>toList());
                
                timeframes = new ArrayList();
                
                for (Reservable r : filteredReservables)
                {
//                    System.out.println(r.getTimeframe().toString());
                    if (r.getName().equals(view.getSelectedLocation().getName()))
                        timeframes.add(r.getTimeframe());
                }
                
//                for (Timeframe t : timeframes)
//                {
//                    System.out.println(t.toString());
//                }
                
                view.setTimeframes(timeframes);
            }
            catch (SQLException ex){}
        }
        else
        {
            timeframes = location.getTimeframes();
            view.setTimeframes(timeframes);
        }
    }
}