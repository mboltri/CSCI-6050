<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Search - YouDrive</title>
</head>
<body>
	<h2>Search for Locations</h2>
	<form method="get" action="SearchLocations">
		<table>
			<tr>
				<td>Enter a city:</td>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<td>Enter a state:</td>
				<td><input type="text" name="state"></td>
			</tr>
			<tr>
				<td>Enter a zip code:</td>
				<td><input type="text" pattern="\d{5}" name="zipCode"></td>
			</tr>
			<tr>
				<td>Enter maximum capacity:</td>
				<td><input type="text" name="capacity"></td>
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