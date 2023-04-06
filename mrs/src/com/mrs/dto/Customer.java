package com.mrs.dto;

import java.util.ArrayList;

public class Customer {
	private int id; //cust_seq
	private String name;
	private String cust_id;
	private String phone;
	private String email;
	private int age;
	ArrayList<Reservation> reservation; 
	
	public Customer() {};
	public Customer(String name, String cust_id, String phone, String email, int age) {
		super();
		this.name = name;
		this.cust_id = cust_id;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}
	public Customer(int id, String name, String cust_id, String phone, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.cust_id = cust_id;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}
	
	
}
