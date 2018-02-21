// @author: Prakash Choudhary

/* This function validates the login form.
 * Validation takes place on the basis of:
 * Email address should be a valid (non-empty) email address
 * Password should not be empty.
 * 
 * Return Type: BOOLEAN
 * 		TRUE  : If email = valid & password = non-empty.
 * 		FALSE : If email / password = empty or not valid.
 */
function validateLoginForm() {
	var form = document.forms["loginForm"];
	var email = form["email"].value;
	var password = form["password"].value;
	var pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	if (!pattern.test(email)) {
		document.getElementById("validationError").innerHTML = "Please enter a valid email address.";
		return false;
	} else if (password == "") {
		document.getElementById("validationError").innerHTML = "Please enter your password.";
		return false;
	}
}

function validateAddUserForm(form) {
	var isValid = true;
	var message = "Invalid values in fields: "
	var elements = form.elements;

	for (var i = 0, element; element = elements[i++];) {
		console.log(element.id + " = " + element.value);
		switch (element.id) {
		case "userName":
			if (element.value == "") {
				isValid = false;
				message += "User name/";
			}
			break;
		case "phone":
			if (!isValidPhone(element.value)) {
				isValid = false;
				message += " Phone /";
			}
			break;
		case "email":
			if (!isValidEmail(element.value)) {
				isValid = false;
				message += " Email /";
			}
			break;
		case "billingAddress", "postalAddress", "deliveryAddress":
			if (element.value == "") {
				isValid = false;
				message += " Address/ ";
			}
			break;
		case "role":
			if (element.value == -1) {
				isValid = false;
				message += " Role type /";
			}
			break;
		}
	}
	if (!isValid)
		document.getElementById("validationError").innerHTML = message;
	return isValid;
}

function isValidEmail(email) {
	var pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	return pattern.test(email);
}

function isValidPhone(phone) {
	var pattern = /^\d{10}$/;
	return pattern.test(phone);
}

function validateAddBalanceForm(form) {
	var user = form["user"].value;
	var balance = form["amount"].value;

	var pattern = /^\d+(\.\d+)?$/;
	var isValid = true;

	if (user == -1) {
		document.getElementById("validationError").innerHTML = "Select a user from list";
		isValid = false;
	} else if (!pattern.test(balance) || balance == "") {
		document.getElementById("validationError").innerHTML = "Balance should be numeric";
		isValid = false;
	}
	return isValid;
}

function validateOrderForm(form) {
	var elements = form.elements;
	var pattern = /^\d+(\.\d+)?$/;
	var errorFields = [];
	for (var i = 0, element; element = elements[i++];) {
		switch (element.id) {
		case "description":
			if (element.value == "")
				errorFields.push(" Description ");
			break;
		case "weight":
			if (!pattern.test(element.value) || element.value == "")
				errorFields.push(" Item Weight ");
		case "actualCost":
			if (!pattern.test(element.value) || element.value == "")
				errorFields.push(" actual cost ");
			break;
		case "pickup", "ship":
			if (element.value == "")
				errorFields.push(" address ");
			break;
		case "type":
			var isSelected = false;
			if(element.value == "normal" || element.value=="express")
				{
				 if(element.checked){
					 break;
				 }
				 else{
					 errorFields.push("delivery type ");
				 }
				}
		}
	}
	if (errorFields.length == 1)
		return true;
	else {
		if(errorFields.length != Array.from(new Set(errorFields)).length){
			errorFields = Array.from(new Set(errorFields));
		}
	}
		showError(errorFields);
		return false;
	}

function showError(fields) {
	document.getElementById("validationError").innerHTML = "Validation Errors at : "
			+ fields.join() + "Fields.";
}