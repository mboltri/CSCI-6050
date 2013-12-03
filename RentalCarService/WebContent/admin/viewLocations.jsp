<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Rental Locations - YouDrive Admin</title>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
	</head>
	<body>
		<h2>Active Rental Locations</h2>
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
					<td>
						<form method="get" action="ViewLocation">
							<input type="hidden" name="id" value="${rentalLocation.id}">
							<input type="submit" value="Modify">
						</form>
					</td>
					<td>
						<form method="post" action="RemoveLocation"  onsubmit="return confirm('Do you really want to delete this rental location?');">
							<input type="hidden" name="id" value="${rentalLocation.id}">
							<input type="submit" value="Delete">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<br>
		
		<h3>Add a location</h3>
		<form method="post" action="AddLocation">
			<table>
					<tr>
						<td>Location name:</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>Street number: </td>
						<td><input type="text" name="streetNumber" style="width: 40px"></td>
					</tr>
                        <td>Street name: </td>
                        <td><input type="text" name="streetName"></td>
					<tr>
                        <td>City: </td>
                        <td><input type="text" name="city"></td>
					</tr>
					<tr>
                        <td>State: </td>
                        <td><input type="text" name="state" style="width: 40px"></td>
					</tr>
                    <tr>
                        <td>Zip code: </td>
                        <td><input type="text" pattern="\d{5}" name="zipCode"></td>
                    </tr>
					<tr>
						<td>Vehicle Capacity:</td>
						<td><input type="number" min=0 name="capacity"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="Submit" value="Add Rental Location"></td>
					</tr>
				</table>
		</form>
		
		<br>
		<a href="adminHome.html">Go back</a>
	</body>
</html>