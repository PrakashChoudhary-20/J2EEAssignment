package com.assignment.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.assignment.helper.AdminHelper;

@Controller
public class AdminController {

	private AdminHelper adminHelper = new AdminHelper();

	
	@RequestMapping("/adminHome")
	public String Home(Model model)
	{
		adminHelper.getUsers(model);
		return "admin";
	}
	@RequestMapping("/addUserForm")
	public String showAddUserForm() {
		return "add_user";
	}

	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("user") != null) {
			adminHelper.saveUser(request, model);
			return "add_user";
		} else {
			model.addAttribute("invalid_session", "Please login first.");
			return "/";
		}
	}

	@RequestMapping("/addBalanceForm")
	public String showAddBalanceForm(Model model) {
		model = adminHelper.fetchFormData(model);
		return "add_balance";
	}

	@RequestMapping("/addBalance")
	public String addBalance(HttpServletRequest request, Model model) {
		adminHelper.addBalance(request, model);
		return "add_balance";
	}

	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "welcome";
	}
}