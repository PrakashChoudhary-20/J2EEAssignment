<head>
<style>
<%@ include file="../resources/css/page.css"%>
</style>
<script>
<%@include file="../resources/js/formValidator.js"%>
</script>
</head>

<body class="welcome">
	<br />
	<h2 align="center">Welcome to Online CourierService!</h2>
	<br />
	<h3 align="center">Please login to access your account</h3>
	<br />
	<form name="loginForm" action="login" onsubmit="return validateLoginForm()" method="GET">

		<table style="height: 67px; width: 361px;" align='center'>
			<tbody>
				<tr>
					<td style="width: 103px;">Email</td>
					<td style="width: 244px;"><input type="text" name="email" id="email"/></td>
				</tr>
				<tr>
					<td style="width: 103px;">Password</td>
					<td style="width: 244px;"><input type="password"
						name="password" id="pwd"/></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<p id="validationError">${error}</p>
					</td>
				</tr>

				<tr>
					<td style="width: 103px;">&nbsp;</td>
					<td style="width: 244px;"><input type="submit" id="submit"></td>
				</tr>
			</tbody>


		</table>
	</form>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</body>