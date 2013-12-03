<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reservations - You Drive</title>
    </head>
    <body>
        <h2>Your Reservations</h2>
        <table>
            <tr>
                <td>Start Time</td>
                <td>End Time</td>
                <td>Vehicle</td>
                <td>Location</td>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>
                        <fmt:formatDate type="time" value="${reservation.startDate}" pattern="h:mm a" />,  
                        <fmt:formatDate type="date" value="${reservation.startDate}" />
                    </td>
                    <td>
                        <fmt:formatDate type="time" value="${reservation.endDate}" pattern="h:mm a" />,  
                        <fmt:formatDate type="date" value="${reservation.endDate}" />
                    </td>
                    <td>${reservation.vehicle.year} ${reservation.vehicle.make} ${reservation.vehicle.model}</td>
                    <td>
                        ${reservation.vehicle.rentalLocation.name}, 
                        ${reservation.vehicle.rentalLocation.streetNumber} ${reservation.vehicle.rentalLocation.streetName}, 
                        ${reservation.vehicle.rentalLocation.city}, ${reservation.vehicle.rentalLocation.state} ${reservation.vehicle.rentalLocation.zipCode}
                    </td>
                    <td>
                        <form method="post" action="CancelReservation"  onsubmit="return confirm(
                        		'Do you really want to cancel this reservation? If the reservation ' +
                        		'is scheduled to start in less than 24 hours, you will be assessed a ' + 
                        		'cancellation fee.');">
                            <input type="hidden" name="reservationId" value="${reservation.id}">
                            <input type="submit" value="Cancel Reservation">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <br>
        <a href="customerHome.html">Go back</a>
    </body>
</html>