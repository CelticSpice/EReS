/**
    DAO (Data Access Object) for accessing reservable data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reservable;
import java.sql.SQLException;

public class ReservableSQLDAO
{
    private DatabaseDataSource source;
    
    /**
        Constructs a new ReservableSQLDAO
    */
    
    public ReservableSQLDAO()
    {
        source = new DatabaseDataSource();
    }
    
    /**
        Adds a record of a reservable to the database
    
        @param reservable Reservable to add
        @throws SQLException Error adding record
    */
    
    public void addReservable(Reservable reservable) throws SQLException
    {        
        DatabaseConnection connection = source.getDBConnection();
        
        // Check if a record of a location should be added
        ReservableQuery query = new ReservableQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryDistinctByLocation(reservable.getLocation());
        parser.setResultSet(connection.runQuery(query));
        
        if (parser.isResultSetEmpty())
        {
            LocationSQLDAO locationDAO = new LocationSQLDAO();
            locationDAO.addLocation(reservable.getLocation());
        }   
        
        // Check if a record of a timeframe should be added
        query.queryDistinctByTimeframe(reservable.getTimeframe());
        parser.setResultSet(connection.runQuery(query));
        
        if (parser.isResultSetEmpty())
        {
            TimeframeSQLDAO timeframeDAO = new TimeframeSQLDAO();
            timeframeDAO.addTimeframe(reservable.getTimeframe());
        }
        
        // Add reservable
        new RecordAdd(connection).addReservable(reservable);
        
        connection.close();
    }
    
    /**
        Removes a record of a reservable from the database
    
        @param reservable Reservable to remove
        @throws SQLException Error removing record
    */
    
    public void removeReservable(Reservable reservable) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        
        new RecordDelete(connection).deleteReservable(reservable);
        
        // Check if a record of a location should also be removed
        ReservableQuery query = new ReservableQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryDistinctByLocation(reservable.getLocation());
        parser.setResultSet(connection.runQuery(query));
        
        if (parser.isResultSetEmpty())
        {
            LocationSQLDAO locationDAO = new LocationSQLDAO();
            locationDAO.removeLocation(reservable.getLocation());
        }
        
        // Check if a record of a timeframe should also be removed
        query.queryDistinctByTimeframe(reservable.getTimeframe());
        parser.setResultSet(connection.runQuery(query));
        
        if (parser.isResultSetEmpty())
        {
            TimeframeSQLDAO timeframeDAO = new TimeframeSQLDAO();
            timeframeDAO.removeTimeframe(reservable.getTimeframe());
        }
        
        connection.close();
    }
    
    /**
        Updates a record of a reservable on the database
    
        @param reservable Updated reservable
        @throws SQLException Error updating record
    */
    
    public void updateReservable(Reservable reservable) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordUpdate(connection).updateReservable(reservable);
        connection.close();
    }
}