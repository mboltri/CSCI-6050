<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Place Reservation - YouDrive</title>
        <script type="text/javascript" src="setDate.js"></script>
    </head>
    <body onload="setDates()">
        <h2>Review the details of your reservation below.</h2>
        <p>
            You are reserving a ${reservation.vehicle.year} ${reservation.vehicle.make} 
            ${reservation.vehicle.model} at the ${reservation.vehicle.rentalLocation.name} location.
        </p>
        <p> 
            The reservation will begin at 
            <fmt:formatDate type="time" pattern="h:mm a" value="${reservation.startDate}" /> on 
            <fmt:formatDate type="date"                   value="${reservation.startDate}" /> and will end at 
            <fmt:formatDate type="time" pattern="h:mm a" value="${reservation.endDate}" /> on 
            <fmt:formatDate type="date"                   value="${reservation.endDate}" />.
        </p>
        <p>
            The cost of this rental will be <fmt:formatNumber type="currency" value="${reservation.cost}" />.
        </p>
        <form method="post" action="PlaceReservation">
            <p>Are you sure you want to place this reservation?</p>
            <p><input type="submit" value="Confirm Reservation"></p>
        </form>
        
        <a href="customerHome.html">Cancel reservation</a>
    </body>
</html>