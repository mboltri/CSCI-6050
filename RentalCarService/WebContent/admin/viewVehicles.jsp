<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Vehicles - YouDrive Admin</title>
	</head>
	<body>
		<h2>Vehicles</h2>
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
		
		<br>
		
		<h3>Add a vehicle</h3>
		<form method="post" action="AddVehicle">
			<table>
					<tr>
						<td>Make:</td>
						<td><input type="text" name="make"></td>
					</tr>
					<tr>
						<td>Model:</td>
						<td><input type="text" name="model"></td>
					</tr>
					<tr>
						<td>Year:</td>
						<td><input type="text" pattern="\d{4}" name="year"></td>
					</tr>
					<tr>
						<td>Color:</td>
						<td><input type="text" name="color"></td>
					</tr>
					<tr>
						<td>License Plate Number:</td>
						<td><input type="text" name="licensePlateNumber"></td>
					</tr>
					<tr>
						<td>Mileage:</td>
						<td><input type="number" min="0" name="mileage"></td>
					</tr>
					<tr>
						<td>Last service date:</td>
						<td><input type="date" name="lastServiceDate"></td>
					</tr>
					<tr>
						<td>Vehicle Condition:</td>
						<td><textarea name="vehicleCondition"></textarea></td>
					</tr>
					<tr>
						<td>Vehicle Type:</td>
						<td>
							<select name="vehicleTypeId">
								<c:forEach items="${vehicleTypes}" var="vehicleType">
									<option value="${vehicleType.id}">${vehicleType.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Rental Location:</td>
						<td>
							<select name="rentalLocationId">
								<c:forEach items="${rentalLocations}" var="rentalLocation">
									<option value="${rentalLocation.id}">${rentalLocation.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="Submit" value="Add Vehicle"></td>
					</tr>
				</table>
		</form>
		
		<br>
		<a href="adminHome.html">Go back</a>
	</body>
</html>