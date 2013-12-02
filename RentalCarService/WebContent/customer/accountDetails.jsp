<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Your Account - YouDrive</title>
    </head>
    <body>
        <h2>Your Account Details</h2>
        <p>Username: ${customerAccount.username}</p>
        <p>Real name: ${customerAccount.firstName} ${customerAccount.lastName}</p>
        <p>Driver's License Number: ${customerAccount.driversLicenseNumber}</p>
        <p>Date joined: ${customerAccount.membershipStartDate}</p>
        <p>
            Membership end date: ${customerAccount.membershipExpirationDate}
            <form method='post' action='ExtendMembership' onsubmit="return confirm('You are about to extend your membership by 6 months. This will charge $${membershipPrice} to your account.');">
                <input type='submit' value='Extend Membership'>
            </form>
        </p>
        <p>Amount owed: <fmt:formatNumber type="currency" value="${customerAccount.balance}"/>
	        <c:if test="${customerAccount.balance > 0}">
			   <a href="payBalance.jsp">Pay balance</a>
			</c:if>
		</p>
        <form method="get" action="modifyCustomerAccount.jsp">
            <input type="hidden" name="username" value="${customerAccount.username}">
            <input type="submit" value="Modify Account Details">
        </form>
        <form method="post" action="RemoveCustomerAccount"  onsubmit="return confirm('Do you really want to delete your account? All reservations will be cancelled without refund.');">
            <input type="hidden" name=username value="${customerAccount.username}">
            <input type="submit" value="Delete Your Account">
        </form>
  
        <br>
        <a href="customerHome.html">Go back</a>
    </body>
</html>