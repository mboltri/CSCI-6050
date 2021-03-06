<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Location Search - YouDrive</title>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
    </head>
    <body>
        <h2>Search Results</h2>
        <c:choose>
            <c:when test="${fn:length(rentalLocations) == 0}">
                <p>No search results were found that match your criteria.
            </c:when>
            <c:otherwise>
                <table>
		            <tr>
		                <td>Name</td>
		                <td>Address</td>
		                <td>Capacity</td>
		            </tr>
		            <c:forEach items="${rentalLocations}" var="rentalLocation">
		                <tr>
		                    <td>${rentalLocation.name}</td>
		                    <td>${rentalLocation.streetNumber} ${rentalLocation.streetName}, ${rentalLocation.city}, ${rentalLocation.state} ${rentalLocation.zipCode}</td>
		                    <td>${rentalLocation.capacity}</td>
		                </tr>
		            </c:forEach>
		        </table>
            </c:otherwise>
        </c:choose>
        
        
        <br>
        
        <a href="searchLocations.jsp">New Search</a>
        <a href="customerHome.html">Home</a>
    </body>
</html>
