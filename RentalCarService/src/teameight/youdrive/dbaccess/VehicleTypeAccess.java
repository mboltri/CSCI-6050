package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teameight.youdrive.entity.VehicleType;

public class VehicleTypeAccess {
    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public VehicleTypeAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all VehicleTypes
     */
    public ArrayList<VehicleType> getVehicleTypes() {
        ArrayList<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM vehicleType";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                VehicleType vehicleType = constructVehicleTypeFromResultSet(resultSet);

                vehicleTypes.add(vehicleType);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleTypes;

    }

    /**
     * Get a particular VehicleType with the specified ID from the VehicleType
     * table
     */
    public VehicleType getVehicleType(int id) {
        VehicleType vehicleType = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM vehicleType WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vehicleType = constructVehicleTypeFromResultSet(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleType;
    }

    /**
     * Add a new VehicleType to the database table
     */
    public boolean addVehicleType(VehicleType vehicleType) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO vehicleType " 
                    + "(name, hourlyPrice, dailyPrice, notes) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, vehicleType.getName());
            statement.setDouble(2, vehicleType.getHourlyPrice());
            statement.setDouble(3, vehicleType.getDailyPrice());
            statement.setString(4, vehicleType.getNotes());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modify an existing VehicleType by replacing values with those from a new
     * VehicleType
     */
    public boolean modifyVehicleType(VehicleType vehicleType) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE vehicleType "
                    + "SET name=?, hourlyPrice=?, dailyPrice=?, notes=? " 
                    + "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, vehicleType.getName());
            statement.setDouble (2, vehicleType.getHourlyPrice());
            statement.setDouble (3, vehicleType.getDailyPrice());
            statement.setString (4, vehicleType.getNotes());
            statement.setInt    (5, vehicleType.getId());

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
    public void removeVehicleType(int id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from vehicleType where id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a result set retrieved from the MySQL database, instantiate and
     * return a VehicleType from the database fields
     */
    private VehicleType constructVehicleTypeFromResultSet(ResultSet resultSet) {
        VehicleType vehicleType = null;

        try {

            int     id          = resultSet.getInt("id");
            String  name        = resultSet.getString("name");
            double  hourlyPrice = resultSet.getDouble("hourlyPrice");
            double  dailyPrice  = resultSet.getDouble("dailyPrice");
            String  notes       = resultSet.getString("notes");

            vehicleType = new VehicleType(id, name, hourlyPrice, dailyPrice, notes);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleType;
    }
}
