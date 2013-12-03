<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Modify Vehicle Type - YouDrive Admin</title>
		<link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
	</head>
	<body>
		<h2>Modifying Vehicle Type <i>${vehicleType.name}</i></h2>
		<form method="post" action="ModifyVehicleType">
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value="${vehicleType.name}"></td>
				</tr>
				<tr>
					<td>Hourly price:</td>
					<td>$<input type="number" min="0" step="0.01" name="hourlyPrice" value="${vehicleType.hourlyPrice}"></td>
				</tr>
				<tr>
					<td>Daily price:</td>
					<td>$<input type="number" min="0" step="0.01" name="dailyPrice" value="${vehicleType.dailyPrice}"></td>
				</tr>
				<tr>
					<td>Notes:</td>
					<td><textarea rows="3" name="notes">${vehicleType.notes}</textarea></td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="id" value="${vehicleType.id}">
						<input type="submit" value="Modify Vehicle Type">
					</td>
					<td><a href="ViewVehicleTypes">Cancel</a></td>
				</tr>
			</table>
		</form>
	</body>
</html>
