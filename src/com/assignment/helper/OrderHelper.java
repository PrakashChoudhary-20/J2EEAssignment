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

public class OrderHelper {

	private HttpSession httpSession;
	private SessionFactory factory = new DbUtils().getSessionFactory();

	public void processOrder(HttpServletRequest request, Model model) {

		httpSession = request.getSession();
		Session session = factory.getCurrentSession();

		double courierCost;
		String itemDescription = request.getParameter("itemDescription");
		int weight = Integer.parseInt(request.getParameter("weight"));
		double actualCost = Double.parseDouble(request.getParameter("actualCost"));
		String pickupAddress = request.getParameter("pickupAddress");
		String shippingAddress = request.getParameter("shippingAddress");
		String deliveryType = request.getParameter("deliveryType");
		int userId = (int) httpSession.getAttribute("user");

		if (deliveryType.equalsIgnoreCase("normal")) {
			courierCost = (weight * 5) / 2;
		} else {
			courierCost = (weight * 5) / 1.5;
		}
		Order order = new Order(userId, itemDescription, weight, actualCost, pickupAddress, shippingAddress,
				deliveryType, courierCost, "order placed");

		try {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
			model.addAttribute("order", "Order placed successfully. Order ID:" + order.getId());
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
}
