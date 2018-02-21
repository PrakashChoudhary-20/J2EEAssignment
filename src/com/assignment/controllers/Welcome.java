package com.assignment.controllers;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.assignment.db.DbUtils;

@Controller
public class Welcome {

	public SessionFactory factory = new DbUtils().getSessionFactory();;

	@RequestMapping("/")
	public String showPage() {
		return "welcome";
	}
}
