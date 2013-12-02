<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Modify Vehicle - YouDrive Admin</title>
    </head>
    <body>
        <h2>Modifying Vehicle <i>${vehicle.year} ${vehicle.make} ${vehicle.model} (${vehicle.licensePlateNumber})</i></h2>
        <form method="post" action="ModifyVehicle">
            <table>
                    <tr>
                        <td>Make:</td>
                        <td><input type="text" name="make" value="${vehicle.make}"></td>
                    </tr>
                    <tr>
                        <td>Model:</td>
                        <td><input type="text" name="model" value="${vehicle.model}"></td>
                    </tr>
                    <tr>
                        <td>Year:</td>
                        <td><input type="text" pattern="\d{4}" name="year" value="${vehicle.year}"></td>
                    </tr>
                    <tr>
                        <td>Color:</td>
                        <td><input type="text" name="color" value="${vehicle.color}"></td>
                    </tr>
                    <tr>
                        <td>License Plate Number:</td>
                        <td><input type="text" name="licensePlateNumber" value="${vehicle.licensePlateNumber}"></td>
                    </tr>
                    <tr>
                        <td>Mileage:</td>
                        <td><input type="number" min="0" name="mileage" value="${vehicle.mileage}"></td>
                    </tr>
                    <tr>
                        <td>Last service date:</td>
                        <td><input type="date" name="lastServiceDate" value="${vehicle.lastServiceDate}"></td>
                    </tr>
                    <tr>
                        <td>Vehicle Condition:</td>
                        <td><textarea name="vehicleCondition">${vehicle.vehicleCondition}</textarea></td>
                    </tr>
                    <tr>
                        <td>Vehicle Type:</td>
                        <td>
                            <select name="vehicleTypeId">
                                <c:forEach items="${vehicleTypes}" var="vehicleType">
                                    <c:choose>
                                        <c:when test="${vehicleType.id == vehicle.vehicleType.id}">
                                            <option value="${vehicleType.id}" selected>${vehicleType.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${vehicleType.id}">${vehicleType.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Rental Location:</td>
                        <td>
                            <select name="rentalLocationId">
                                <c:forEach items="${rentalLocations}" var="rentalLocation">
                                    <c:choose>
                                        <c:when test="${rentalLocation.id == vehicle.rentalLocation.id}">
                                            <option value="${rentalLocation.id}" selected>${rentalLocation.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${rentalLocation.id}">${rentalLocation.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <input type="hidden" name="id" value="${vehicle.id}">
                        <input type="submit" value="Modify Vehicle">
                    </td>
                    <td><a href="ViewVehicles">Cancel</a></td>
                </tr>
                </table>
        </form>
    </body>
</html>