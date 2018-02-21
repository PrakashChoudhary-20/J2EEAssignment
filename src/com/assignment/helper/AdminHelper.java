package com.assignment.helper;

import java.util.List;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.ui.Model;
import com.assignment.db.DbUtils;
import com.assignment.entities.User;

/*
 *  Helper class to perform operations related to Admin.
 */
public class AdminHelper {

	private SessionFactory factory = new DbUtils().getSessionFactory();

	/*
	 * Admin action = Save User Fetches the user inputs from request & saves
	 * into the database
	 */
	public void saveUser(HttpServletRequest request, Model model) {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String billingAddress = request.getParameter("billingAddress");
		String postalAddress = request.getParameter("postalAddress");
		String deliveryAddress = request.getParameter("deliveryAddress");
		int roleType = Integer.parseInt(request.getParameter("roleType"));
		Session session = factory.getCurrentSession();

		// Database transaction begins here.
		session.beginTransaction();

		User user = new User(userName, email, phone, postalAddress, billingAddress, deliveryAddress, 0.00, roleType);
		try {
			session.save(user);
			model.addAttribute("message", "New user successfully added! Add another?");
			session.getTransaction().commit();

			// Database transaction & session closed.
			session.close();
		} catch (Exception e) {
			model.addAttribute("message", "There was some problem adding a user. Try again later?");
			e.printStackTrace();
		}
	}

	/*
	 * Admin action = add balance to end user's account
	 */
	public void addBalance(HttpServletRequest request, Model model) {

		// Fetching data for all end users into the model object.
		model = fetchFormData(model);

		int userId = Integer.parseInt(request.getParameter("user"));
		double amount = Double.parseDouble(request.getParameter("amount"));

		Session session = factory.getCurrentSession();

		// Database transaction starts here
		session.beginTransaction();

		try {
			User user = (User) session.get(User.class, userId);
			user.setBalance((user.getBalance() + amount));
			session.update(user);
			session.getTransaction().commit();
			model.addAttribute("success_message", "Balance updated for user: " + user.getUserName());
		} catch (Exception e) {
			/*
			 * In case of any exception while performing update operation: 1.
			 * Print the full stack trace for debugging purpose 2. Add attribute
			 * to model to inform admin that there was trouble performing the
			 * operation
			 */
			e.printStackTrace();
			model.addAttribute("error_message", "There was an error completing operation.");
		} finally {
			// Close the session object in both the cases - either success or
			// failure.
			session.close();
		}
	}

	/*
	 * Helper method to avoid duplication of code while fetching all users for
	 * displaying on the add balance page. Returns an object of Model class
	 * which holds List of users retrieved from database.
	 */
	@SuppressWarnings("unchecked")
	public Model fetchFormData(Model model) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String fetchUsers = "FROM User u WHERE u.roleType=2";

		try {
			Query query = session.createQuery(fetchUsers);
			List<User> users = query.getResultList();
			model.addAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public void getUsers(Model model) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<User> customers = session.createQuery("from User u WHERE u.roleType=2").getResultList();
		List<User> employees = session.createQuery("from User u WHERE u.roleType=3").getResultList();
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		session.close();
	}
}