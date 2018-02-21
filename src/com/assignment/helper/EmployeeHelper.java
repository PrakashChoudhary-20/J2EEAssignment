package com.assignment.helper;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.ui.Model;
import com.assignment.db.DbUtils;
import com.assignment.entities.Order;

public class EmployeeHelper {

	private SessionFactory factory = new DbUtils().getSessionFactory();

	@SuppressWarnings("unchecked")
	public List<Order> getPendingOrders() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String fetchOrders = "FROM Order o WHERE o.assignedTo=0";

		try {
			Query query = session.createQuery(fetchOrders);
			List<Order> orders = query.getResultList();
			System.out.println(orders.size());
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public void assignOrder(int orderId, int user, Model model) {

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Order order = session.get(Order.class, orderId);
			order.setAssignedTo(user);
			session.update(order);
			session.getTransaction().commit();
			List<Order> orders = getPendingOrders();
			model.addAttribute("orders", orders);
			model.addAttribute("assign_message", "Order successfully assigned to you.");
		} catch (Exception e) {
			model.addAttribute("assign_message", "Some error occured");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void getAssignedOrder(int user, Model model) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String fetchOrders = "FROM Order o WHERE o.assignedTo=" + user;

		try {
			Query query = session.createQuery(fetchOrders);
			List<Order> orders = query.getResultList();
			model.addAttribute("orderSize", orders.size());
			model.addAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "Some error occured while processing this request.");
		} finally {
			session.close();
		}
	}

	public void updateOrderStatus(int orderId, String orderStatus, int user, Model model) {
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Order order = session.get(Order.class, orderId);
			order.setOrderStatus(orderStatus);
			session.update(order);
			session.getTransaction().commit();
			getAssignedOrder(user, model);
			model.addAttribute("message", "Order successfully updated.");
		} catch (Exception e) {
			model.addAttribute("message", "Some error occured");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
