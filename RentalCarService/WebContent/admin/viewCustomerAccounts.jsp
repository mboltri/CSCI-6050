<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Customer Accounts - YouDrive Admin</title>
    </head>
    <body>
        <h2>Customer Accounts</h2>
        <div>
            <form method="post" action="ModifyMembershipPrice">
                The cost of a 6-month membership is $
                <input type='number' min=0 step=0.01 value='${membershipPrice}' name='membershipPrice'>
                <input type='submit' value='Modify'>
            </form>
        </div>
        
        <br>
        
        <table>
            <tr>
                <td>Username</td>
                <td>Real Name</td>
                <td>DL Number</td>
                <td>Membership Start Date</td>
                <td>Membership Expiration Date</td>
                <td>Balance</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${customerAccounts}" var="customerAccount">
                <tr>
                    <td>${customerAccount.username}</td>
                    <td>${customerAccount.lastName}, ${customerAccount.firstName}</td>
                    <td>${customerAccount.driversLicenseNumber}</td>
                    <td>${customerAccount.membershipStartDate}</td>
                    <td>${customerAccount.membershipExpirationDate}</td>
                    <td><fmt:formatNumber type="currency" value="${customerAccount.balance}"/></td>
                    <td>${customerAccount.status}</td>
                    <td>
                        <form method="get" action="ViewCustomerAccount">
                            <input type="hidden" name="username" value="${customerAccount.username}">
                            <input type="submit" value="Modify">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="RemoveCustomerAccount"  onsubmit="return confirm('Do you really want to delete this user account?');">
                            <input type="hidden" name=username value="${customerAccount.username}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
  
        <br>
        <a href="adminHome.html">Go back</a>
    </body>
</html>