package com.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	/**
	 * Constructors with parameters.
	 */
	public User(String userName, String email, String phoneNumber, String postalAddress, String billingAddress,
			String deliveryAddress, double balance, int roleType) {
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.postalAddress = postalAddress;
		this.billingAddress = billingAddress;
		this.deliveryAddress = deliveryAddress;
		this.balance = balance;
		this.roleType = roleType;
		this.password = "welcome123";
	}

	public User() {
	}

	/**
	 * All Properties of User class
	 **/

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "postal_address")
	private String postalAddress;

	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "delivery_address")
	private String deliveryAddress;

	@Column(name = "balance")
	private double balance;

	@Column(name = "password")
	private String password;

	@Column(name = "role_type")
	private int roleType;

	/**
	 * Getters & Setters methods
	 **/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	/**
	 * toString method for User
	 **/
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", postalAddress=" + postalAddress + ", billingAddress=" + billingAddress + ", deliveryAddress="
				+ deliveryAddress + ", balance=" + balance + ", password=" + password + ", roleType=" + roleType + "]";
	}
}
