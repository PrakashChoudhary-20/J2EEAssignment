package com.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	/*
	 * All properties of Order class
	 */

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "item_description")
	private String itemDescription;

	@Column(name = "weight")
	private int weight;

	@Column(name = "actual_cost")
	private double actualCost;

	@Column(name = "pickup_address")
	private String pickupAddress;

	@Column(name = "shipping_address")
	private String shippingAddress;

	@Column(name = "delivery_type")
	private String deliveryType;

	@Column(name = "courier_cost")
	private double courierCost;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "assigned_to")
	private int assignedTo;

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Order(int userId, String itemDescription, int weight, double actualCost, String pickupAddress,
			String shippingAddress, String deliveryType, double courierCost, String orderStatus) {
		super();
		this.userId = userId;
		this.itemDescription = itemDescription;
		this.weight = weight;
		this.actualCost = actualCost;
		this.pickupAddress = pickupAddress;
		this.shippingAddress = shippingAddress;
		this.deliveryType = deliveryType;
		this.courierCost = courierCost;
		this.orderStatus = orderStatus;
	}

	public Order() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public double getCourierCost() {
		return courierCost;
	}

	public void setCourierCost(double courierCost) {
		this.courierCost = courierCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", itemDescription=" + itemDescription + ", weight=" + weight
				+ ", actualCost=" + actualCost + ", pickupAddress=" + pickupAddress + ", shippingAddress="
				+ shippingAddress + ", deliveryType=" + deliveryType + ", courierCost=" + courierCost + ", orderStatus="
				+ orderStatus + ", assignedTo=" + assignedTo + "]";
	}

}