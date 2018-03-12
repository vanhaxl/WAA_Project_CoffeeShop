package edu.mum.coffee.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order implements Identity {
	
	private int id;
	private Date orderDate;
	private List<Orderline> orderLines = new ArrayList<Orderline>();
	private Person person;
	
	public Order() {
		
	}
	
	public Order(Date orderDate, Person person) {
		super();
		this.orderDate = orderDate;
		this.orderLines = new ArrayList<Orderline>();
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public List<Orderline> getOrderLines() {
		return Collections.unmodifiableList(orderLines);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public void addOrderLine(Product product, int quantity) {
		Orderline orderLine = new Orderline();
		orderLine.setProduct(product);
		orderLine.setQuantity(quantity);
		orderLine.setOrder(this);
		this.orderLines.add(orderLine);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Order {");
		result.append(person.toString());
		result.append(", OrderLines:[");
		orderLines.forEach((Orderline o) -> result.append(o));
		result.append("]");
		result.append("}");
		return result.toString();
	}
}
