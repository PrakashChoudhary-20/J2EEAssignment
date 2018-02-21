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
</style>
</head>

<h2>Welcome Dear Employee!</h2>

<ul>
	<li><a href="employee">Show Pending Orders </a></li>
	<li><a href="showAssignedOrders">Assigned Orders</a></li>
	<li><a href="updateOrderStatus">Update Order Status</a></li>
	<li><a href="signOut">Sign Out</a></li>
</ul>

<br />
<br />

<p>Below Orders are assigned to you:</p>

<p>Total Orders assigned: ${orderSize }</p>

<table style="height: 67px; width: 100%;">
	<tbody>
		<tr>
			<td style="width: 5%">Order ID</td>
			<td style="width: 30%">Order Description</td>
			<td style="width: 20%">Pickup Address</td>
			<td style="width: 20%">Shipping Address</td>
			<td style="width: 10%">Current Status</td>
			<td style="width: 10%">New Status</td>
			<td style="width: 5%"></td>
		</tr>

		<c:forEach items="${orders}" var="order">
			<form action="updateOrderStatus" method="POST">
				<tr>
					<td style="width: 10%">${order.id }<input type="hidden"
						name="orderId" value="${order.id }"></td>
					<td style="width: 30%">${order.itemDescription }</td>
					<td style="width: 20%">${order.shippingAddress }</td>
					<td style="width: 20%">${order.pickupAddress }</td>
					<td style="width: 10%">${order.orderStatus }</td>
					<td style="width: 10%"><textarea name="status"></textarea></td>
					<td style="width: 5%"><button type="submit">Update
							Order</button></td>
				</tr>
			</form>
		</c:forEach>

	</tbody>
</table>

</html>