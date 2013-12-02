package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teameight.youdrive.entity.RentalLocation;

public class LocationAccess {

    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public LocationAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all locations from the RentalLocations table
     */
    public ArrayList<RentalLocation> getRentalLocations() {
        ArrayList<RentalLocation> rentalLocations = new ArrayList<RentalLocation>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM rentalLocation";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                RentalLocation rentalLocation = constructRentalLocationFromResultSet(resultSet);

                rentalLocations.add(rentalLocation);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalLocations;

    }

    /**
     * Get a particular location with the specified ID from the RentalLocation
     * table
     */
    public RentalLocation getRentalLocation(int id) {
        RentalLocation rentalLocation = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM rentalLocation WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rentalLocation = constructRentalLocationFromResultSet(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalLocation;
    }

    /**
     * Add a new RentalLocation to the database table
     */
    public void addRentalLocation(RentalLocation rentalLocation) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO rentalLocation "
                    + "(name, streetNumber, streetName, city, state, zipCode, capacity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, rentalLocation.getName());
            statement.setString (2, rentalLocation.getStreetNumber());
            statement.setString (3, rentalLocation.getStreetName());
            statement.setString (4, rentalLocation.getCity());
            statement.setString (5, rentalLocation.getState());
            statement.setInt    (6, rentalLocation.getZipCode());
            statement.setInt    (7, rentalLocation.getCapacity());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify an existing RentalLocation by replacing values with those from a
     * new RentalLocation
     */
    public boolean modifyRentalLocation(RentalLocation rentalLocation) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE rentalLocation "
                    + "SET name=?, streetNumber=?, streetName=?, city=?, state=?, zipCode=?, capacity=? "
                    + "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, rentalLocation.getName());
            statement.setString (2, rentalLocation.getStreetNumber());
            statement.setString (3, rentalLocation.getStreetName());
            statement.setString (4, rentalLocation.getCity());
            statement.setString (5, rentalLocation.getState());
            statement.setInt    (6, rentalLocation.getZipCode());
            statement.setInt    (7, rentalLocation.getCapacity());
            statement.setInt    (8, rentalLocation.getId());

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
    public void removeRentalLocation(int id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from rentalLocation where id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a result set retrieved from the MySQL database, instantiate and
     * return a RentalLocation from the database fields
     */
    private RentalLocation constructRentalLocationFromResultSet(ResultSet resultSet) {
        RentalLocation rentalLocation = null;

        try {

            int     id              = resultSet.getInt("id");
            String  name            = resultSet.getString("name");
            String  streetNumber    = resultSet.getString("streetNumber");
            String  streetName      = resultSet.getString("streetName");
            String  city            = resultSet.getString("city");
            String  state           = resultSet.getString("state");
            int     zipCode         = resultSet.getInt("zipCode");
            int     capacity        = resultSet.getInt("capacity");

            rentalLocation = new RentalLocation(id, name, streetNumber, streetName, city, state,
                    zipCode, capacity);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalLocation;
    }
}
