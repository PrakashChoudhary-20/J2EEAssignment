package com.assignment.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.helper.AdminHelper;
import com.assignment.helper.LoginHelper;

@Controller
public class LoginController {

	AdminHelper adminHelper = new AdminHelper();

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		LoginHelper loginHelper = new LoginHelper();
		String userType = loginHelper.validateLogin(request);
		switch (userType) {
		case "admin":
			adminHelper.getUsers(model);
			return "admin";
		case "customer":
			loginHelper.getUser(model);
			return "customer";
		case "employee":
			EmployeeController e = new EmployeeController();
			e.getPendingOrders(model);
			return "employee";
		default:
			model.addAttribute("error", "Invalid Credentials");
			return "welcome";
		}
	}
}