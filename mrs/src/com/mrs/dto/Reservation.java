package com.mrs.dto;

public class Reservation {
	private String rsv_code;
	private int cust_seq; //fk
	private String rsv_date;
	private String seat_name;
	private String rsv_state;
	private String movie_code;
	public Reservation(String rsv_code, int cust_seq, String rsv_date, String seat_name, String rsv_state,
			String movie_code) {
		super();
		this.rsv_code = rsv_code;
		this.cust_seq = cust_seq;
		this.rsv_date = rsv_date;
		this.seat_name = seat_name;
		this.rsv_state = rsv_state;
		this.movie_code = movie_code;
	}
	public String getRsv_code() {
		return rsv_code;
	}
	public void setRsv_code(String rsv_code) {
		this.rsv_code = rsv_code;
	}
	public int getCust_seq() {
		return cust_seq;
	}
	public void setCust_seq(int cust_seq) {
		this.cust_seq = cust_seq;
	}
	public String getRsv_date() {
		return rsv_date;
	}
	public void setRsv_date(String rsv_date) {
		this.rsv_date = rsv_date;
	}
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}
	public String getRsv_state() {
		return rsv_state;
	}
	public void setRsv_state(String rsv_state) {
		this.rsv_state = rsv_state;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(String movie_code) {
		this.movie_code = movie_code;
	}
	@Override
	public String toString() {
		return "Reservation [rsv_code=" + rsv_code + ", cust_seq=" + cust_seq + ", rsv_date=" + rsv_date
				+ ", seat_name=" + seat_name + ", rsv_state=" + rsv_state + ", movie_code=" + movie_code + "]";
	}
	
	
}
