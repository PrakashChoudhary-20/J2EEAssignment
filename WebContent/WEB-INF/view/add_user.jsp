
<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");
	}
%>

<html>
<head>
<style>
<%@ include file="../resources/css/menu.css"%>
<%@ include file="../resources/css/page.css"%>
<%@ include file="../resources/css/table.css"%>
</style>

<script>
<%@ include file="../resources/js/formValidator.js"%>
</script>
</head>
<body class="addUser">
<br/>
	<h2 align="center">Add User</h2>
<p></p>
	<ul>
		<li><a href="adminHome">Home</a></li>
		<li><a href="addUserForm">Add User</a></li>
		<li><a href="addBalanceForm">Add Balance</a></li>
		<li><a href="signOut">Sign Out</a></li>
	</ul>

	<br /> ${message}

	<p>Add a new user in the system</p>
	<form action="addUser" id="addUserForm" onsubmit="return validateAddUserForm(this)" method="POST">
		<table style="height: 67px; width: 400px;">
			<tbody>
				<tr>
					<td style="width: 150px;">User Name</td>
					<td style="width: 250px;"><input type="text" name="userName" id="userName" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Email ID</td>
					<td style="width: 250px;"><input type="text" name="email" id="email" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Phone Number</td>
					<td style="width: 250px;"><input type="text" name="phone" id="phone" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Postal Address</td>
					<td style="width: 250px;"><input type="text"
						name="postalAddress" id="postalAddress" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Billing Address</td>
					<td style="width: 250px;"><input type="text"
						name="billingAddress" id="billingAddress" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Delivery Address</td>
					<td style="width: 250px;"><input type="text"
						name="deliveryAddress" id="deliveryAddress" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Role Type</td>
					<td style="width: 250px;"><select name="roleType" id="role" >
							<option value="-1">--Please Select--</option>
							<option value="2">Customer</option>
							<option value="3">Internal Employee</option>
					</select></td>
				</tr>				
				<tr>
					<td colspan="2" style="width: 250px;"><p id="validationError"></td>				
				</tr>
								<tr>
					<td style="width: 150px;">&nbsp;</td>
					<td style="width: 250px;"><input type="submit"></td>
				</tr>
				
			</tbody>
		</table>
	</form>
</body>
</html>