<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Place Reservation - YouDrive</title>
        <script type="text/javascript" src="setDate.js"></script>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
    </head>
    <body onload="setDates()">
        <h2>Select your reservation date and time</h2>
        <form method="get" action="FindAvailableVehicles">
            <table>
                <tr>
                    <td>Start date and time: </td>
                    <td><input type="date" name="startDate" id="startDate" required></td>
                    <td><input type="time" name="startTime" id="startTime" step="900" required></td>
                </tr>
                <tr>
                    <td>End date and time: </td>
                    <td><input type="date" name="endDate" id="endDate"required></td>
                    <td><input type="time" name="endTime" id="endTime" step="900" required></td>
                </tr>
                <tr>
                    <td>Location: </td>
                    <td>
                        <select name="rentalLocationId">
                            <c:forEach items="${rentalLocations}" var="rentalLocation">
                                <option value="${rentalLocation.id}">${rentalLocation.name} (${rentalLocation.zipCode})</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Find Available Vehicles"></td>
                </tr>
            </table>
            
            <a href="customerHome.html">Go back</a>
        </form>
    </body>
</html>
