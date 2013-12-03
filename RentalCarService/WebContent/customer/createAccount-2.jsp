<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>YouDrive Account Creation</title>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
    </head>
    <body>
        <h2>We're glad you've decided to join YouDrive</h2>
        <p>
            We need a little bit more information about you to complete your profile.
        </p>
        <form method="post" action="AddCustomerAccount">
            <table>
                <tr>
                    <td>First name: </td>
                    <td><input type="text" name="firstName"></td>
                </tr>
                <tr>
                    <td>Last name: </td>
                    <td><input type="text" name="lastName"></td>
                </tr>
                <tr>
                    <td>Driver's license number: </td>
                    <td><input type="text" name="driversLicenseNumber"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <b>
                            The cost of a membership is 
                            <fmt:formatNumber type="currency" value="${membershipPrice}"/>. 
                            Memberships remain active for 6 months.
                        </b>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Complete Account Creation"></td>
                    <td><a href="RemoveAccountCredentials">Cancel</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>
