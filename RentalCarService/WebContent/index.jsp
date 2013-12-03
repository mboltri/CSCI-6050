<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome to YouDrive</title>
    </head>
    <body>
        <h2>Welcome to YouDrive, the rental car service for everyone!</h2>
        <p>
            If you have used YouDrive before, please log in below. Otherwise, 
            <a href="customer/createAccount-1.jsp">create an account</a> now.
        </p>
        <p style="color:red">${logInErrorMessage}</p>
        <form method="post" action="LogIn">
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
                    <td><input type="submit" value="Log In"></td>
                </tr>
            </table>
        </form>
    </body>
</html>