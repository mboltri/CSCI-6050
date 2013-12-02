package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import teameight.youdrive.entity.AccountCredentials;
import teameight.youdrive.entity.CustomerAccount;

public class CustomerAccountAccess {

    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public CustomerAccountAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        ArrayList<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM customerAccount";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                CustomerAccount customerAccount = constructCustomerAccountFromResultSet(resultSet);

                customerAccounts.add(customerAccount);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerAccounts;

    }

    /**
     * Get a particular location with the specified username from the CustomerAccount
     * table
     */
    public CustomerAccount getCustomerAccount(String username) {
        CustomerAccount customerAccount = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM customerAccount WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                customerAccount = constructCustomerAccountFromResultSet(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerAccount;
    }

    public CustomerAccount getCustomerAccount(AccountCredentials account) {
        return getCustomerAccount(account.getUsername());
    }

    /**
     * Add a new CustomerAccount to the database table
     */
    public void addCustomerAccount(CustomerAccount customerAccount) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO customerAccount "
                    + "(username, firstName, lastName, driversLicenseNumber, membershipStartDate, membershipExpirationDate, balance) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString (1, customerAccount.getUsername());
            statement.setString (2, customerAccount.getFirstName());
            statement.setString (3, customerAccount.getLastName());
            statement.setString (4, customerAccount.getDriversLicenseNumber());
            statement.setDate   (5, customerAccount.getMembershipStartDate());
            statement.setDate   (6, customerAccount.getMembershipExpirationDate());
            statement.setDouble (7, customerAccount.getBalance());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify an existing CustomerAccount by replacing values with those from a
     * new CustomerAccount
     */
    public boolean modifyCustomerAccount(CustomerAccount customerAccount) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE customerAccount "
                    + "SET firstName=?, lastName=?, driversLicenseNumber=?, membershipStartDate=?, membershipExpirationDate=?, balance=?"
                    + "WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, customerAccount.getFirstName());
            statement.setString (2, customerAccount.getLastName());
            statement.setString (3, customerAccount.getDriversLicenseNumber());
            statement.setDate   (4, customerAccount.getMembershipStartDate());
            statement.setDate   (5, customerAccount.getMembershipExpirationDate());
            statement.setDouble (6, customerAccount.getBalance());
            statement.setString (7, customerAccount.getUsername());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Deletes the record in the database table with the corresponding username
     */
    public void removeCustomerAccount(String username) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from customerAccount where username=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a result set retrieved from the database, instantiate and
     * return a CustomerAccount from the database fields
     */
    private CustomerAccount constructCustomerAccountFromResultSet(ResultSet resultSet) {
        CustomerAccount customerAccount = null;

        try {

            String username                 = resultSet.getString("username");
            String firstName                = resultSet.getString("firstName");
            String lastName                 = resultSet.getString("lastName");
            String driversLicenseNumber     = resultSet.getString("driversLicenseNumber");
            Date   membershipStartDate      = resultSet.getDate("membershipStartDate");
            Date   membershipExpirationDate = resultSet.getDate("membershipExpirationDate");
            double balance                  = resultSet.getDouble("balance");

            customerAccount = new CustomerAccount(username, firstName, lastName, 
                    driversLicenseNumber, membershipStartDate, membershipExpirationDate, balance);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerAccount;
    }
}
