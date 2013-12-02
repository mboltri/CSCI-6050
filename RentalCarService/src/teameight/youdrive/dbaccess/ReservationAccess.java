package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.entity.Vehicle;

public class ReservationAccess {

    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public ReservationAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all locations from the Reservations table
     */
    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM reservation";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Reservation reservation = constructReservationFromResultSet(resultSet);

                reservations.add(reservation);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;

    }
    
    public ArrayList<Reservation> getReservationsByCustomer(String customerUsername) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM reservation WHERE customerUsername=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, customerUsername);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Reservation reservation = constructReservationFromResultSet(resultSet);

                reservations.add(reservation);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;

    }
    
    public ArrayList<Reservation> getReservationsByVehicle(int vehicleId) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM reservation WHERE vehicleId=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Reservation reservation = constructReservationFromResultSet(resultSet);

                reservations.add(reservation);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;

    }

    /**
     * Get a particular location with the specified ID from the Reservation
     * table
     */
    public Reservation getReservation(int id) {
        Reservation reservation = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM reservation WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reservation = constructReservationFromResultSet(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }

    /**
     * Add a new Reservation to the database table
     */
    public void addReservation(Reservation reservation) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO reservation "
                    + "(startDate, endDate, customerUsername, vehicleId) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setTimestamp   (1, reservation.getStartDate());
            statement.setTimestamp   (2, reservation.getEndDate());
            statement.setString     (3, reservation.getReservationist().getUsername());
            statement.setInt        (4, reservation.getVehicle().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify an existing Reservation by replacing values with those from a
     * new Reservation
     */
    public boolean modifyReservation(Reservation reservation) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE reservation "
                    + "SET startDate=?, endDate=?, customerUsername=?, vehicleId=? "
                    + "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setTimestamp  (1, reservation.getStartDate());
            statement.setTimestamp  (2, reservation.getEndDate());
            statement.setString     (3, reservation.getReservationist().getUsername());
            statement.setInt        (4, reservation.getVehicle().getId());;
            statement.setInt        (5, reservation.getId());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Deletes the record in the database table with the corresponding id
     */
    public void removeReservation(int id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from reservation where id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a result set retrieved from the MySQL database, instantiate and
     * return a Reservation from the database fields
     */
    private Reservation constructReservationFromResultSet(ResultSet resultSet) {
        CustomerAccountAccess customerAccountAccess = new CustomerAccountAccess();
        VehicleAccess         vehicleAccess         = new VehicleAccess();
        Reservation reservation = null;

        try {

            int         id               = resultSet.getInt("id");
            Timestamp   startDate        = resultSet.getTimestamp("startDate");
            Timestamp   endDate          = resultSet.getTimestamp("endDate");
            String      customerUsername = resultSet.getString("customerUsername");
            int         vehicleId        = resultSet.getInt("vehicleId");
            
            CustomerAccount reservationist = customerAccountAccess.getCustomerAccount(customerUsername);
            Vehicle vehicle = vehicleAccess.getVehicle(vehicleId);
            
            reservation = new Reservation(id, startDate, endDate, reservationist, vehicle);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }

    public ArrayList<Reservation> getReservationsByCustomer(CustomerAccount customerAccount) {
        return getReservationsByCustomer(customerAccount.getUsername());
    }
}
