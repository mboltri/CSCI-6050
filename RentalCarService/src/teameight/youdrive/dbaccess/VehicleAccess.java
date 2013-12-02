package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.entity.VehicleType;

public class VehicleAccess {
    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public VehicleAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all Vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM vehicle";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Vehicle vehicle = constructVehicleFromResultSet(resultSet);

                vehicles.add(vehicle);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;

    }
    
    /**
     * Get vehicles assigned to a particular rental location
     */
    public ArrayList<Vehicle> getVehiclesByLocation(int rentalLocationId) {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM vehicle where rentalLocationId=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, rentalLocationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Vehicle vehicle = constructVehicleFromResultSet(resultSet);

                vehicles.add(vehicle);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;

    }
    /**
     * Get a particular Vehicle with the specified ID from the Vehicle table
     */
    public Vehicle getVehicle(int id) {
        Vehicle vehicle = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM vehicle WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vehicle = constructVehicleFromResultSet(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    /**
     * Add a new Vehicle to the database table
     */
    public boolean addVehicle(Vehicle vehicle) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO vehicle "
                    + "(make, model, year, color, licensePlateNumber, mileage, lastServiceDate, "
                    + "vehicleCondition, rentalLocationId, vehicleTypeId) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1,  vehicle.getMake());
            statement.setString (2,  vehicle.getModel());
            statement.setInt    (3,  vehicle.getYear());
            statement.setString (4,  vehicle.getColor());
            statement.setString (5,  vehicle.getLicensePlateNumber());
            statement.setInt    (6,  vehicle.getMileage());
            statement.setDate   (7,  vehicle.getLastServiceDate());
            statement.setString (8,  vehicle.getVehicleCondition());
            statement.setInt    (9,  vehicle.getRentalLocation().getId());
            statement.setInt    (10, vehicle.getVehicleType().getId());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modify an existing Vehicle by replacing values with those from a new
     * Vehicle
     */
    public boolean modifyVehicle(Vehicle vehicle) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE vehicle "
                    + "SET make=?, model=?, year=?, color=?, licensePlateNumber=?, mileage=?, lastServiceDate=?, "
                    + "vehicleCondition=?, rentalLocationId=?, vehicleTypeId=? " + "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1,  vehicle.getMake());
            statement.setString (2,  vehicle.getModel());
            statement.setInt    (3,  vehicle.getYear());
            statement.setString (4,  vehicle.getColor());
            statement.setString (5,  vehicle.getLicensePlateNumber());
            statement.setInt    (6,  vehicle.getMileage());
            statement.setDate   (7,  vehicle.getLastServiceDate());
            statement.setString (8,  vehicle.getVehicleCondition());
            statement.setInt    (9,  vehicle.getRentalLocation().getId());
            statement.setInt    (10, vehicle.getVehicleType().getId());
            statement.setInt    (11, vehicle.getId());

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
    public void removeVehicle(int id) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from vehicle where id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a result set retrieved from the MySQL database, instantiate and
     * return a Vehicle from the database fields
     */
    private Vehicle constructVehicleFromResultSet(ResultSet resultSet) {
        Vehicle vehicle = null;
        LocationAccess locationAccess = new LocationAccess();
        VehicleTypeAccess vehicleTypeAccess = new VehicleTypeAccess();

        try {

            int             id                  = resultSet.getInt("id");
            String          make                = resultSet.getString("make");
            String          model               = resultSet.getString("model");
            int             year                = resultSet.getInt("year");
            String          color               = resultSet.getString("color");
            String          licensePlateNumber  = resultSet.getString("licensePlateNumber");
            int             mileage             = resultSet.getInt("mileage");
            Date            lastServiceDate     = resultSet.getDate("lastServiceDate");
            String          vehicleCondition    = resultSet.getString("vehicleCondition");
            int             locationId          = resultSet.getInt("rentalLocationId");
            int             vehicleTypeId       = resultSet.getInt("vehicleTypeId");
           
            RentalLocation  rentalLocation      = locationAccess.getRentalLocation(locationId);
            VehicleType     vehicleType         = vehicleTypeAccess.getVehicleType(vehicleTypeId);

            vehicle = new Vehicle(id, rentalLocation, vehicleType, make, model, year, color,
                    licensePlateNumber, mileage, lastServiceDate, vehicleCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

}
