<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Vehicle Search - YouDrive Admin</title>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
    </head>
    <body>
        <h2>Search Results</h2>
        <c:choose>
            <c:when test="${fn:length(vehicles) == 0}">
                <p>No search results were found that match your criteria.
            </c:when>
            <c:otherwise>
                <table>
		            <tr>
		                <td>Make</td>
		                <td>Model</td>
		                <td>Year</td>
		                <td>Color</td>
		                <td>License Plate Number</td>
		                <td>Mileage</td>
		                <td>Last Service Date</td>
		                <td>Condition</td>
		                <td>Vehicle Type</td>
		                <td>Hourly Price</td>
		                <td>Daily Price</td>
		                <td>Rental Location</td>
		            </tr>
		            <c:forEach items="${vehicles}" var="vehicle">
		                <tr>
		                    <td>${vehicle.make}</td>
		                    <td>${vehicle.model}</td>
		                    <td>${vehicle.year}</td>
		                    <td>${vehicle.color}</td>
		                    <td>${vehicle.licensePlateNumber}</td>
		                    <td>${vehicle.mileage}</td>
		                    <td>${vehicle.lastServiceDate}</td>
		                    <td>${vehicle.vehicleCondition}</td>
		                    <td>${vehicle.vehicleType.name}</td>
		                    <td><fmt:formatNumber type="currency" value="${vehicle.vehicleType.hourlyPrice}"/></td>
		                    <td><fmt:formatNumber type="currency" value="${vehicle.vehicleType.dailyPrice}"/></td>
		                    <td>${vehicle.rentalLocation.name}</td>
		                    <td>
		                        <form method="get" action="ViewVehicle">
		                            <input type="hidden" name="id" value="${vehicle.id}">
		                            <input type="submit" value="Modify">
		                        </form>
		                    </td>
		                    <td>
		                        <form method="post" action="RemoveVehicle"  onsubmit="return confirm('Do you really want to delete this vehicle?');">
		                            <input type="hidden" name="id" value="${vehicle.id}">
		                            <input type="submit" value="Delete">
		                        </form>
		                    </td>
		                </tr>
		            </c:forEach>
		        </table>
            </c:otherwise>
        </c:choose>
        
        
        <br>
        
        <a href="searchVehicles.jsp">New Search</a>
        <a href="adminHome.html">Administrator Home</a>
    </body>
</html>
