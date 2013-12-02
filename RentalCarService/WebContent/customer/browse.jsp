<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style type="text/css">
			.collapsed { display: none}
			.expanded { display: table }
		</style>
		<script type="text/javascript" src="setDates.js">
			
		</script>
		<title>Rental Locations - YouDrive Admin</title>
	</head>
	<body>
        <h2>Active Rental Locations</h2>
            <c:forEach items="${rentalLocations}" var="rentalLocation">
	            <p>${rentalLocation.name}  (${rentalLocation.streetNumber} ${rentalLocation.streetName}, ${rentalLocation.city}, ${rentalLocation.state} ${rentalLocation.zipCode})</p>
	            <p style="color:gray; margin-left:30px" onclick="vehicleToggle('${rentalLocation.name}')">
	               <span id="${rentalLocation.name}_toggle">Show</span> vehicles
	            </p>
                <table class="collapsed" style="margin-left:30px" id="${rentalLocation.name}_table">
	                <tr>
                        <td>Vehicle</td>
                        <td>Class</td>
                        <td>Color</td>
                        <td>Mileage</td>
                        <td>Hourly Rate</td>
                        <td>Daily Rate</td>
                    </tr>
                    <c:forEach items="${vehicles}" var="vehicle">
                        <c:if test="${vehicle.rentalLocation.id == rentalLocation.id}">
	                        <tr>
	                            <td>${vehicle.year} ${vehicle.make} ${vehicle.model}</td>
                                <td>${vehicle.vehicleType.name}</td>
	                            <td>${vehicle.color}</td>
	                            <td>${vehicle.mileage}</td>
	                            <td><fmt:formatNumber type="currency" value="${vehicle.vehicleType.hourlyPrice}"/> per hour</td>
	                            <td><fmt:formatNumber type="currency" value="${vehicle.vehicleType.dailyPrice}"/> per day</td>
	                        </tr>
                        </c:if>
                    </c:forEach>
                </table>    
            </c:forEach>
        
        <br>
        
        <a href="customerHome.html">Home</a>
    </body>
</html>