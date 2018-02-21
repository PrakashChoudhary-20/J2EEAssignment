<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");
		;
	}
%>

<html>
<head>
<style>
<%@include file="../resources/css/menu.css"%>
<%@include file="../resources/css/page.css"%>
</style>
</head>
<body class="adminPanel">
	<br />
	<h2 align="center">Welcome to Admin panel</h2>
	<p />
	<ul>
		<li><a href="adminHome">Home</a></li>
		<li><a href="addUserForm">Add User</a></li>
		<li><a href="addBalanceForm">Add Balance</a></li>
		<li><a href="signOut">Sign Out</a></li>
	</ul>

	<br />

	<p>Dashboard:</p>
	<table style="height: 190px;" width="593" id="adminDashboard">
		<tbody>
			<tr>
				<td style="width: 95px;" id="custHead">Customers</td>
				<td style="width: 190px;">
					<table style="height: 37px;" width="341" border="1"
						id="adminDashboard">
						<tbody>
							<tr>
								<th style="width: 140px;">Customer Name</th>
								<th style="width: 140px;">Customer Balance</th>
							</tr>
							<c:forEach items="${customers}" var="customer">
								<tr>
									<td style="width: 140px;">${customer.userName }</td>
									<td style="width: 140px;">${customer.balance }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td style="width: 85px;"></td>
				<td style="width: 294px;">&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 85px;" id="empHead">Employees</td>
				<td style="width: 190px;">
					<table style="height: 38px;" width="345" border="1"
						id="adminDashboard">
						<tbody>
							<tr>
								<th style="width: 140px;">Employee Name</th>
							</tr>
							<c:forEach items="${employees}" var="employee">
								<tr>
									<td style="width: 140px;">${employee.userName }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>

</body>
</html>