package edu.mum.coffee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Orderline implements Identity {

	private int id;
	private int quantity;
	private Product product;
	@JsonIgnore
	private Order order;

	Orderline() {
		
	}

	@Override
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getSubtotal() {
		return quantity * product.getPrice();
	}

	public double getPrice() {
		return product.getPrice();
	}
	
	@Override
	public String toString() {
		return "OderLine: {" + "id:" + String.valueOf(id) 
			+ ", quantity:" + String.valueOf(quantity) 
			+ ", productId:" + String.valueOf(product.getId()) + "}";
	}
}
