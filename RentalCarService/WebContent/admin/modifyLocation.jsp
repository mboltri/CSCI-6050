<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Modify Location - YouDrive Admin</title>
		<link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
	</head>
	<body>
		<h2>Modifying Rental Location <i>${rentalLocation.name}</i></h2>
		<form method="post" action="ModifyLocation">
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value="${rentalLocation.name}"></td>
				</tr>
				<tr>
					<td>Street Number:</td>
					<td><input type="text" name="streetNumber" value="${rentalLocation.streetNumber}"></td>
				</tr>
				<tr>
					<td>Street Name:</td>
					<td><input type="text" name="streetName" value="${rentalLocation.streetName}"></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input type="text" name="city" value="${rentalLocation.city}"></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input type="text" name="state" value="${rentalLocation.state}"></td>
				</tr>
				<tr>
					<td>Zip Code:</td>
					<td><input type="text" pattern="\d{5}" name="zipCode" value="${rentalLocation.zipCode}" ></td>
				</tr>
				<tr>
					<td>Capacity:</td>
					<td><input type="number" name="capacity" value="${rentalLocation.capacity}"></td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="id" value="${rentalLocation.id}">
						<input type="submit" value="Modify Location">
					</td>
					<td><a href="ViewLocations">Cancel</a></td>
				</tr>
			</table>
		</form>
	</body>
</html>
