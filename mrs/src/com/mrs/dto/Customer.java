package com.mrs.dto;

import java.util.ArrayList;

public class Customer {
	private int cust_seq; //cust_seq
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
	public Customer(int cust_seq, String name, String cust_id, String phone, String email, int age) {
		super();
		this.cust_seq = cust_seq;
		this.name = name;
		this.cust_id = cust_id;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}

	public int getCust_seq() {
		return cust_seq;
	}
	public void setCust_seq(int cust_seq) {
		this.cust_seq = cust_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ArrayList<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(ArrayList<Reservation> reservation) {
		this.reservation = reservation;
	}
	@Override
	public String toString() {
		return "Customer [cust_seq=" + cust_seq + ", name=" + name + ", cust_id=" + cust_id + ", phone=" + phone + ", email="
				+ email + ", age=" + age + ", reservation=" + reservation + "]";
	}
	
	
}
