<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="javax.script.*"%>

<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");;
	}
%>

<head>
<style>
<%@include file="../resources/css/menu.css"%>
<%@include file="../resources/css/modal.css"%>
<%@include file="../resources/css/page.css"%>
</style>


<script type="text/javascript">
	var modal = document.getElementById('myModal');

	function showStatus($status, $id) {
		document.getElementById('orderStatus').innerHTML = "Your order " +$id + ", current status is: " + $status;
	}

	function close() {
		alert("Yeah!");
//		document.getElementById('myModal').remove();
	}
</script>
</head>
<body class="placedOrders">
<br />
<ul>
	<li><a href="orderForm">Place Order</a></li>
	<li><a href="myOrders">My Orders</a></li>
	<li><a href="signOut">Sign Out</a></li>
</ul>

<p id = "orderStatus"></p>
<table style="height: 67px; width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%">Order ID</td>
			<td style="width: 20%">Order Description</td>
			<td style="width: 20%">Actual Cost of tde item</td>
			<td style="width: 20%">Shipping Address</td>
			<td style="width: 20%">Track Order</td>
		</tr>

		<c:forEach items="${orders}" var="order">
			<tr>
				<td style="width: 20%">${order.id }</td>
				<td style="width: 20%">${order.itemDescription }</td>
				<td style="width: 20%">${order.actualCost }</td>
				<td style="width: 20%">${order.shippingAddress }</td>
				<td style="width: 20%"><button id="track" name="trackButton"
						name="Track" onClick="showStatus('${order.orderStatus }', '${order.id}')">Track</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>