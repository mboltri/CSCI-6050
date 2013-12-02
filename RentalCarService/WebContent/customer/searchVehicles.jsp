<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle Search - YouDrive</title>
</head>
<body>
	<h2>Search for Vehicles</h2>
	<form method="get" action="SearchVehicles">
		<table>
			<tr>
				<td>Enter a make:</td>
				<td><input type="text" name="make"></td>
			</tr>
			<tr>
				<td>Enter a model:</td>
				<td><input type="text" name="model"></td>
			</tr>
			<tr>
				<td>Enter year:</td>
				<td><input type="text" pattern="\d{4}" name="year"></td>
			</tr>
			<tr>
				<td>Enter a color:</td>
				<td><input type="text" name="color"></td>
			</tr>

			<tr>
				<td>Enter a maximum mileage:</td>
				<td><input type="number" min="0" name="mileage"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search"></td>
			</tr>

		</table>
	</form>
	
	<br>
	
	<a href="customerHome.html">Go back</a>
</body>
</html>