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
</style>
</head>
<body class="customer">
<br/>
<h2 align="center" id="customerHome">Welcome Dear Customer</h2>
<p></p>
<ul>
	<li><a href="orderForm">Place Order</a></li>
	<li><a href="myOrders">My Orders</a></li>
	<li><a href="signOut">Sign Out</a></li>
</ul>

<br/>

		<table style="height: 67px; width: 400px;">
			<tbody>
				<tr>
					<td style="width: 150px;">User Name</td>
					<td style="width: 250px;">${customer.userName }</td>
				</tr>

				<tr>
					<td style="width: 150px;">Balance</td>
					<td style="width: 250px;">${customer.balance }</td>
				</tr>

			</tbody>
		</table>

<p id="orderSuccess">${order}</p>
${orderCost }
${orderError }
</body>
</html>