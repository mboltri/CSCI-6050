package teameight.youdrive.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import teameight.youdrive.entity.AccountCredentials;

public class AccountCredentialsAccess {

    public final String URL = "jdbc:mysql://uml.cs.uga.edu:3306/team8";
    public final String USERNAME = "team8";
    public final String PASSWORD = "oomethods";

    public AccountCredentialsAccess() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public AccountCredentials getAccountCredentials(String username) {
        AccountCredentials accountCredentials = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM accountCredentials WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                
                String password = resultSet.getString("password");
                String role     = resultSet.getString("role");
                
                accountCredentials = new AccountCredentials(username, password, role);
            }
            
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountCredentials;

    }

    public boolean addAccountCredentials(AccountCredentials accountCredentials) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO accountCredentials "
                    + "(username, password, role) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, accountCredentials.getUsername());
            statement.setString (2, accountCredentials.getPassword());
            statement.setString (3, accountCredentials.getRole());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifyAccountCredentials(AccountCredentials accountCredentials) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE accountCredentials "
                    + "SET password=?, role=? "
                    + "WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString (1, accountCredentials.getPassword());
            statement.setString (2, accountCredentials.getRole());
            statement.setString (3, accountCredentials.getUsername());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void removeAccountCredentials(String username) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "delete from accountCredentials where username=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAccountCredentials(AccountCredentials account) {
        removeAccountCredentials(account.getUsername());
    }

}
