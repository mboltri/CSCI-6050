<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>YouDrive Account Creation</title>
    </head>
    <body>
        <h2>We're glad you've decided to join YouDrive</h2>
        <p>To start, create a username and password and enter them below.</p>
        <p style="color:red">${accountCreationErrorMessage}</p>
        <form method="post" action="AddAccountCredentials">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>Reenter password: </td>
                    <td><input type="password" name="passwordConfirm" required></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="role" value="customer"></td>
                    <td><input type="submit" value="Create Account"></td>
                </tr>
            </table>
            
            <a href="../LogOut">Go back</a>
        </form>
    </body>
</html>