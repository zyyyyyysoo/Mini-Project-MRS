package com.mrs.dto;

public class Seat {
	private int seat_code;
	private String seat_name;
	private boolean seat_state;
	private String rsv_code;
	public Seat(int seat_code, String seat_name, boolean seat_state, String rsv_code) {
		super();
		this.seat_code = seat_code;
		this.seat_name = seat_name;
		this.seat_state = seat_state;
		this.rsv_code = rsv_code;
	}
	public int getSeat_code() {
		return seat_code;
	}
	public void setSeat_code(int seat_code) {
		this.seat_code = seat_code;
	}
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}
	public boolean isSeat_state() {
		return seat_state;
	}
	public void setSeat_state(boolean seat_state) {
		this.seat_state = seat_state;
	}
	public String getRsv_code() {
		return rsv_code;
	}
	public void setRsv_code(String rsv_code) {
		this.rsv_code = rsv_code;
	}
	@Override
	public String toString() {
		return "Seat [seat_code=" + seat_code + ", seat_name=" + seat_name + ", seat_state=" + seat_state
				+ ", rsv_code=" + rsv_code + "]";
	}
	
	
}
