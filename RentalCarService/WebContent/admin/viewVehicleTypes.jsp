<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Vehicle Types - YouDrive Admin</title>
	</head>
	<body>
		<h2>Vehicle Types</h2>
		<table>
			<tr>
				<td>Name</td>
				<td>Hourly Price</td>
				<td>Daily Price</td>
				<td>Notes</td>
			</tr>
			<c:forEach items="${vehicleTypes}" var="vehicleType">
				<tr>
					<td>${vehicleType.name}</td>
					<td><fmt:formatNumber type="currency" value="${vehicleType.hourlyPrice}"/></td>
					<td><fmt:formatNumber type="currency" value="${vehicleType.dailyPrice}"/></td>
					<td>${vehicleType.notes}</td>
					<td>
						<form method="get" action="ViewVehicleType">
							<input type="hidden" name="id" value="${vehicleType.id}">
							<input type="submit" value="Modify">
						</form>
					</td>
					<td>
						<form method="post" action="RemoveVehicleType"  onsubmit="return confirm('Do you really want to delete this vehicle type?');">
							<input type="hidden" name="id" value="${vehicleType.id}">
							<input type="submit" value="Delete">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<br>
		
		<h3>Add a vehicle type</h3>
		<form method="post" action="AddVehicleType">
			<table>
					<tr>
						<td>Name of new vehicle type:</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>Hourly price:</td>
						<td>$<input type="number" min="0" step="0.01" name="hourlyPrice"></input></td>
					</tr>
					<tr>
						<td>Daily price:</td>
						<td>$<input type="number" type="number" min="0" step="0.01" name="dailyPrice"></input></td>
					</tr>
					<tr>
						<td>Notes:</td>
						<td><textarea rows="3" name="notes"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="Submit" value="Add Vehicle Type"></td>
					</tr>
				</table>
		</form>
		
		<br>
		<a href="adminHome.html">Go back</a>
	</body>
</html>