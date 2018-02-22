package com.assignment.helper;

import java.util.List;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.ui.Model;

import com.assignment.db.DbUtils;
import com.assignment.entities.User;

public class LoginHelper {

	private String email;
	private String password;
	private SessionFactory factory = new DbUtils().getSessionFactory();
	private List<User> users;
	private HttpSession httpSession;

	public void readRequestParameters(HttpServletRequest request) {
		this.email = request.getParameter("email");
		this.password = request.getParameter("password");
	}

	/*
	 * Return TRUE when the results is not empty. Return FALSE when the results
	 * is empty.
	 */
	@SuppressWarnings("unchecked")
	public boolean isValidLogin() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String hql = "FROM User u WHERE u.email = '" + email + "' AND u.password='" + password + "'";
		Query query = session.createQuery(hql);
		this.users = query.getResultList();
		if (users.isEmpty()) {
			session.close();
			return false;
		} else {
			System.out.println(users.get(0).getId());
			session.close();
			httpSession.setAttribute("user", users.get(0).getId());
			return true;
		}
	}

	public String validateLogin(HttpServletRequest request) {
		String userType = "";
		httpSession = request.getSession(true);
		readRequestParameters(request);
		if (isValidLogin()) {
			int type = getUserType();
			if (type == 1)
				userType = "admin";
			else if (type == 2)
				userType = "customer";
			else
				userType = "employee";
		}
		return userType;
	}

	public int getUserType() {
		int type = 0;
		for (User user : users) {
			type = user.getRoleType();
		}
		return type;
	}

	public void getUser(HttpServletRequest request, Model model) {
		httpSession = request.getSession(true);
		int userId = (int) httpSession.getAttribute("user");
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		User user = session.get(User.class, userId);
		model.addAttribute("customer", user);
		session.close();
	}
}
