<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Modify Customer Account - YouDrive Admin</title>
	</head>
	<body>
		<h2>Modifying Customer <i>${customerAccount.firstName} ${customerAccount.lastName}</i></h2>
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
                    <td>Membership Start Date: </td>
                    <td><input type="text" name="membershipStartDate" value="${customerAccount.membershipStartDate}"></td>
                </tr>
                <tr>
                    <td>Membership Expiration Date: </td>
                    <td><input type="text" name="membershipExpirationDate" value="${customerAccount.membershipExpirationDate}"></td>
                </tr>
                <tr>
                    <td>Balance: </td>
                    <td><input type="number" min=0 step=0.01 name="balance" value="${customerAccount.balance}"></td>
                </tr>
				<tr>
					<td>
						<input type="hidden" name="username" value="${customerAccount.username}">
						<input type="submit" value="Modify Customer Account">
					</td>
					<td><a href="ViewCustomerAccounts">Cancel</a></td>
				</tr>
			</table>
		</form>
	</body>
</html>