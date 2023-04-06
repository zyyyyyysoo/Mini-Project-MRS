package com.mrs.test;

import com.mrs.dao.CustomerDAOImpl;
import com.mrs.dto.Customer;

import config.ServerInfo;

public class MovieReservationTest {

	public static void main(String[] args) {
		
		System.out.println("jisu");
		CustomerDAOImpl dao=CustomerDAOImpl.getInstance();
		
//		try {
//			dao.addCustomer(new Customer("minji", "mj25801", "01066092345", "d@gmail.com", 28));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			System.out.println(";;;;;;;;;;;");
//		}
		
//		try {
//			dao.deleteCustomer(null);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
//	static {
//		try {
//			Class.forName(ServerInfo.DRIVER_NAME);
//	        System.out.println("드라이버 로딩 성공..");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

}
