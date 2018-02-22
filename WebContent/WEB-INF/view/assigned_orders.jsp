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
<%@ include file="../resources/css/menu.css"%>
<%@ include file="../resources/css/page.css"%>
<%@ include file="../resources/css/table.css"%>
</style>

<script type="text/javascript">
<%@ include file="../resources/js/formValidator.js"%>
</script>
</head>
<body class="assignedOrders">
<h2 align="center">Welcome Dear Employee</h2>

<ul>
	<li><a href="employee">Show Pending Orders </a></li>
	<li><a href="showAssignedOrders">Assigned Orders</a></li>
	<li><a href="signOut">Sign Out</a></li>
</ul>

<br />
<br />

<p>Total Orders assigned: ${orderSize }</p>
<p>Below Orders are assigned to you:</p>

<table style="height: 67px;" width="100%" border="1">
	<tbody>
		<tr>
			<th style="width: 5%">Order ID</th>
			<th style="width: 20%">Order Description</th>
			<th style="width: 15%">Pickup Address</th>
			<th style="width: 15%">Shipping Address</th>
			<th style="width: 10%">Current Status</th>
			<th style="width: 10%">New Status</th>
			<th style="width: 5%"></th>
		</tr>

		<c:forEach items="${orders}" var="order">
			<form action="updateOrderStatus" onsubmit="return validateStatus(this)" method="POST" >
				<tr>
					<td style="width: 10%">${order.id }<input type="hidden"
						name="orderId" value="${order.id }"></td>
					<td style="width: 20%">${order.itemDescription }</td>
					<td style="width: 15%">${order.shippingAddress }</td>
					<td style="width: 15%">${order.pickupAddress }</td>
					<td style="width: 10%">${order.orderStatus }</td>
					<td style="width: 10%"><textarea name="status"></textarea></td>
					<td style="width: 5%">
					<button type="submit">Update
							Order</button></td>
				</tr>
			</form>
		</c:forEach>

	</tbody>
</table>
<p id="validationError"></p>
</html>