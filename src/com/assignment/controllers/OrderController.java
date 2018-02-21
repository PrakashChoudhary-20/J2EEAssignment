package com.assignment.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.assignment.helper.OrderHelper;

@Controller
public class OrderController {

	private OrderHelper orderHelper = new OrderHelper();

	@RequestMapping("/orderForm")
	public String showOrderForm() {
		return "place_order";
	}

	@RequestMapping("/placeOrder")
	public String processOrder(HttpServletRequest request, Model model) {
		orderHelper.processOrder(request, model);
		return "customer";
	}

	@RequestMapping("/myOrders")
	public String showMyOrders(HttpServletRequest request, Model model) {
		orderHelper.getMyOrders(request, model);
		return "placed_orders";
	}

	@RequestMapping("/trackMe")
	public String trackOrder(HttpServletRequest request, Model model) {
		showMyOrders(request, model);
		int orderId = Integer.parseInt(request.getParameter("trackButton"));
		System.out.println(orderId);
		orderHelper.getTrackingDetails(orderId, model);
		return "placed_orders";
	}
}
