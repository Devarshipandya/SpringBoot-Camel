package com.example.demo.dto;

public class Order {
	
	private int id;
	private String name;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Order(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Order() {
		
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
}
