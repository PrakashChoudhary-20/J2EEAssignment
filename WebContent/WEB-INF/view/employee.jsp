<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");
		;
	}
%>

<head>
<style>
<%@ include file="../resources/css/menu.css"%>
<%@ include file="../resources/css/page.css"%>
<%@ include file="../resources/css/table.css"%>
</style>
</head>
<body class="employee">
<br/>
<h2 align="center">Welcome Dear Employee</h2>

<ul>
	<li><a href="employee">Show Pending Orders </a></li>
	<li><a href="showAssignedOrders">Assigned Orders</a></li>
	<li><a href="signOut">Sign Out</a></li>
</ul>

<br />
<br />

<p>Here is the list of Orders that are pending to be processed.</p>
<p>Click on assign button, so that the order is assigned to you &
	you can then start processing.</p>

${assign_message }

<table style="height: 67px;" width="100%" border="1">
	<tbody>
		<tr>
			<th style="width: 10%">Order ID</th>
			<th style="width: 20%">Order Description</th>
			<th style="width: 10%">Actual Cost of the item</th>
			<th style="width: 20%">Pickup Address</th>
			<th style="width: 20%">Shipping Address</th>
			<th style="width: 10%">Assign</th>
		</tr>

		<c:forEach items="${orders}" var="order">
			<form action="assignOrder" method="GET">
				<tr>
					<td style="width: 10%" align="center"><input type="text" name="orderId"
						value="${order.id }" readonly="readonly" /></td>
					<td style="width: 20%" align="center">${order.itemDescription }</td>
					<td style="width: 10%" align="center">${order.actualCost }</td>
					<td style="width: 20%" align="center">${order.shippingAddress }</td>
					<td style="width: 20%" align="center">${order.pickupAddress }</td>
					<td style="width: 10%" align="center"><button type="submit" id=${order.id}>Assign</button></td>
				</tr>
			</form>
		</c:forEach>
	</tbody>
</table>
</body>
</html>