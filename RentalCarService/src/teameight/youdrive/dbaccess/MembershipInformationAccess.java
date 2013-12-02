package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembershipInformationAccess {

    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public MembershipInformationAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the price of a membership (in USD)
     */
    public double getMembershipPrice() {
        double membershipPrice = 1.0;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT price FROM membershipInformation";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
           
            resultSet.next();

            membershipPrice = resultSet.getDouble("price");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membershipPrice;

    }
    
    /**
     * get the length of a membership (in days)
     */
    public int getMembershipLength() {
        int membershipLength = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT lengthInDays FROM membershipInformation";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
           
            resultSet.next();

            membershipLength = resultSet.getInt("lengthInDays");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membershipLength;

    }

    /**
     * Modify the price of a membership
     */
    public boolean modifyMembershipPrice(double newPrice) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE membershipInformation SET price=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDouble (1, newPrice);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    /**
     * Modify the length in days that a membership lasts
     */
    public boolean modifyMembershipLength(int numberOfDays) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE membershipInformation SET lengthInDays=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, numberOfDays);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
