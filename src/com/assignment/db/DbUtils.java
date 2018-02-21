package com.assignment.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.assignment.entities.User;

public class DbUtils {

	public SessionFactory getSessionFactory() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		return factory;
	}
}
