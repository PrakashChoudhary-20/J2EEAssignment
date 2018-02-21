package com.assignment.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entities.Order;
import com.assignment.helper.EmployeeHelper;

@Controller
public class EmployeeController {

	private EmployeeHelper employeeHelper = new EmployeeHelper();

	@RequestMapping("/employee")
	public void loadEmployee(Model model) {
		getPendingOrders(model);
	}

	@RequestMapping("/showAssignedOrders")
	public String getAssignedOrders(HttpServletRequest request, Model model) {
		int user = (int) request.getSession().getAttribute("user");
		employeeHelper.getAssignedOrder(user, model);
		return "assigned_orders";
	}

	@RequestMapping("/updateOrderStatus")
	public String updateOrder(HttpServletRequest request, Model model) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String orderStatus = request.getParameter("status");
		int user = (int) request.getSession().getAttribute("user");
		employeeHelper.updateOrderStatus(orderId, orderStatus, user, model);
		return "assigned_orders";
	}

	@RequestMapping("/assignOrder")
	public String assignOrder(HttpServletRequest request, Model model) {
		int user = (int) request.getSession().getAttribute("user");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		employeeHelper.assignOrder(orderId, user, model);
		return "employee";
	}

	public void getPendingOrders(Model model) {
		List<Order> orders = employeeHelper.getPendingOrders();
		model.addAttribute("orders", orders);
	}
}
