
<%
	int user = (int) request.getSession().getAttribute("user");
	if (user == 0) {
		response.sendRedirect("/courier-serivce-mvc/");
	}
%>

<head>
<style>
<%@ include file="../resources/css/menu.css"%>
<%@include file="../resources/css/page.css"%>
</style>

<script>
<%@ include file="../resources/js/formValidator.js"%>
</script>
</head>
<body class="placeOrder">
	<br>
	<h2 align="center">Place Order</h2>

	<ul>
		<li><a href="orderForm">Place Order</a></li>
		<li><a href="">Track a package</a></li>
		<li><a href="signOut">Sign Out</a></li>
	</ul>

	<br /> ${message}

	<p>Place your Oder here:</p>
	<form action="placeOrder" onsubmit="return validateOrderForm(this)" method="POST">
		<table style="height: 67px; width: 500px;">
			<tbody>

				<tr>
					<td style="width: 150px;">Item Description</td>
					<td style="width: 280px;"><textarea name="itemDescription"
							id="description"></textarea></td>
				</tr>

				<tr>
					<td style="width: 150px;">Weight (grams)</td>
					<td style="width: 280px;"><input type="text" id="weight"
						name="weight" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Cost of the item</td>
					<td style="width: 280px;"><input type="text" id="actualCost"
						name="actualCost" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Pickup Address</td>
					<td style="width: 280px;"><input type="text" id="pickup"
						name="pickupAddress" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Shipping address</td>
					<td style="width: 280px;"><input type="text" id="ship"
						name="shippingAddress" /></td>
				</tr>

				<tr>
					<td style="width: 150px;">Delivery type</td>
					<td style="width: 280px;"><input type="radio" id="type"
						name="deliveryType" value="normal"><label>Normal
							Delivery</label> <input type="radio" id="type" name="deliveryType"
						value="express"><label>Express Delivery</label></td>
				</tr>
				<tr>
					<td colspan="2" style="width: 244px;"><p id="validationError"></p></td>
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