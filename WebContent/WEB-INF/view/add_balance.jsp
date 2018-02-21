<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");
	}
%>
<html>

<head>
<style>
<%@include file="../resources/css/menu.css"%>
<%@include file="../resources/css/page.css"%>
<%@include file="../resources/css/table.css"%>
</style>

<script>
<%@include file="../resources/js/formValidator.js"%>
</script>

</head>
<body class="addBalance">
	<br />
	<h2 align="center">Add Balance</h2>
	<p></p>
	<ul>
		<li><a href="adminHome">Home</a></li>
		<li><a href="addUserForm">Add User</a></li>
		<li><a href="addBalanceForm">Add Balance</a></li>
		<li><a href="signOut">Sign Out</a></li>
	</ul>

	<br />

	<p class="successMessage">${success_message }</p>
	<p>Add Balance to the user account</p>
	<form action="addBalance" onsubmit="return validateAddBalanceForm(this)" method="POST">
	<table style="height: 67px; width: 400px;">
		<tbody>
			<tr>
				<td style="width: 150px;">User</td>
				<td style="width: 250px;"><select name="user" id="user">
						<option value="-1">--Please Select--</option>
						<c:forEach items="${users}" var="user">
							<option value="${user.id}">${user.email}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td style="width: 150px;">Amount to add:</td>
				<td style="width: 250px;"><input type="text" name="amount"
					id="amount" /></td>
			</tr>
			<tr><td style="width:150px"></td>
				<td style="width: 250px;"><p id="validationError"></p></td>
			</tr>

			<tr>
				<td style="width: 103px;">&nbsp;</td>
				<td style="width: 244px;"><input type="submit"></td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>