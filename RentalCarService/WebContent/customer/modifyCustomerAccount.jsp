<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Modify Your Account - YouDrive</title>
		<link rel="stylesheet" href="styles.css" type="text/css" media="screen" />
	</head>
	<body>
		<h2>Modifying Your Account</h2>
		<form method="post" action="ModifyCustomerAccount">
			<table>
				<tr>
					<td>Username: ${customerAccount.username} (non-changable)</td>
				</tr>
				<tr>
                    <td>First name, last name: </td>
                    <td><input type="text" name="firstName" value="${customerAccount.firstName}"></td>
                    <td><input type="text" name="lastName" value="${customerAccount.lastName}"></td>
                </tr>
                <tr>
                    <td>Drivers License Number: </td>
                    <td><input type="text" name="driversLicenseNumber" value="${customerAccount.driversLicenseNumber}"></td>
                </tr>
				<tr>
					<td>
						<input type="hidden" name="username" value="${customerAccount.username}">
						<input type="submit" value="Modify Your Account">
					</td>
					<td><a href="accountDetails.jsp">Cancel</a></td>
				</tr>
			</table>
		</form>
	</body>
</html>
