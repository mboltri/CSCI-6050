<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Pay account balance</title>
        <link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
    </head>
    <body>
        <h2>${paymentMessage}</h2>
        <p>
            Your account has a balance of <fmt:formatNumber type="currency" value="${customerAccount.balance}"/>..
        </p>
        <form method="post" action="PayBalance">
            <table>
                <tr>
                    <td>Credit card type: </td>
                    <td>
                        <select>
                            <option>Visa</option>
                            <option>Mastercard</option>
                            <option>American Express</option>
                            <option>Discover</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Card number: </td>
                    <td><input type="text"></td>
                    <td>CSV: </td>
                    <td><input type="text" style="width:50px"></td>
                </tr>
                <tr>
                    <td>Expiration: </td>
                    <td>
                        <select>
		                    <c:forEach begin="1" end="12" var="month">
							    <option><fmt:formatNumber type="number" minIntegerDigits="2" value="${month}"/></option>
							</c:forEach>
						</select>/
						<select>
                            <c:forEach begin="13" end="23" var="day">
                                <option>${day}</option>
                            </c:forEach>
                        </select>
					</td>
                </tr>
                <tr>
                    <td>Amount to pay: $</td>
                    <td><input type="number" min="0.01" step="0.01" name="paymentAmount" value="${customerAccount.balance}"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Pay Balance"></td>
                    <td colspan="2"><a href="ViewCustomerAccount">Cancel payment</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>
