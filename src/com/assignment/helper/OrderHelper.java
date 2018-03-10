package com.assignment.helper;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.ui.Model;

import com.assignment.db.DbUtils;
import com.assignment.entities.Order;
import com.assignment.entities.User;

public class OrderHelper {

	private HttpSession httpSession;
	private SessionFactory factory = new DbUtils().getSessionFactory();

	public void processOrder(HttpServletRequest request, Model model) {

		httpSession = request.getSession();
		Session session = factory.getCurrentSession();

		String itemDescription = request.getParameter("itemDescription");
		int weight = Integer.parseInt(request.getParameter("weight"));
		double actualCost = Double.parseDouble(request.getParameter("actualCost"));
		String pickupAddress = request.getParameter("pickupAddress");
		String shippingAddress = request.getParameter("shippingAddress");
		String deliveryType = request.getParameter("deliveryType");
		int userId = (int) httpSession.getAttribute("user");

		double courierCost = getCourierCost(deliveryType, weight);
		model.addAttribute("orderCost", "Cost to be deducted: " + courierCost);
		Order order = new Order(userId, itemDescription, weight, actualCost, pickupAddress, shippingAddress,
				deliveryType, courierCost, "order placed");

		try {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
			model.addAttribute("order", "Order placed successfully.");
		} catch (Exception e) {
			model.addAttribute("order",
					"We have encountered an issue while placing your oder. Please try again later.");
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Model getMyOrders(HttpServletRequest request, Model model) {
		httpSession = request.getSession();
		int userId = (int) httpSession.getAttribute("user");
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String fetchOrders = "FROM Order o WHERE o.userId=" + userId;

		try {
			Query query = session.createQuery(fetchOrders);
			List<Order> orders = query.getResultList();
			model.addAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return model;
	}

	public void getTrackingDetails(int orderId, Model model) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			Order order = (Order) session.get(Order.class, orderId);
			httpSession.setAttribute("trackingData", order);
			model.addAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean checkEligibility(HttpServletRequest request, Model model) {
		httpSession = request.getSession();
		int userId = (int) httpSession.getAttribute("user");
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		boolean isEligible = false;
		try {
			User user = (User) session.get(User.class, userId);
			double balance = user.getBalance();
			double cost = getCourierCost(request.getParameter("deliveryType"),
					Integer.parseInt(request.getParameter("weight")));
			if (balance < cost) {
				isEligible = false;
			} else {
				user.setBalance(user.getBalance() - cost);
				session.update(user);
				session.getTransaction().commit();
				isEligible = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isEligible;
	}

	public double getCourierCost(String deliveryType, int weight) {
		double courierCost;
		if (deliveryType.equalsIgnoreCase("normal")) {
			courierCost = (weight * 5) / 2;
		} else {
			courierCost = (weight * 5) / 1.5;
		}
		return courierCost;
	}
}
