package com.mrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mrs.dto.Customer;
import com.mrs.dto.Movie;
import com.mrs.dto.Reservation;
import com.mrs.dto.Seat;
import com.mrs.exception.DuplicateException;
import com.mrs.exception.InvalidTransactionException;
import com.mrs.exception.RecordNotFoundException;


public interface CustomerDAO {
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn)throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn)throws SQLException;

	//Customer...CRUD
	void addCustomer(Customer cust)throws SQLException,DuplicateException;
	void deleteCustomer(String cust_id)throws SQLException,RecordNotFoundException;
	void updateCustomer(Customer cust)throws SQLException,RecordNotFoundException;

	//Schedule Manage...CRUD
	//void addSchedule(ScheduleRec sche)throws SQLException,DuplicateIDException;
	//void deleteSchedule(String sche_code)throws SQLException,RecordNotFoundException;
	//void updateSchedule(ScheduleRec sche)throws SQLException,RecordNotFoundException;
	
   ArrayList<Movie> getAllMoive() throws SQLException; // All Movie List
//   ArrayList<Reservation> getAllSchedule() throws SQLException,RecordNotFoundException; //상영시간-현재시간 >= 5분인 영화 목록을 보여줌
   ArrayList<Reservation> getReservationList(int cust_seq) throws SQLException; // Customer Movie List
   Reservation getReservation(int rsv_code) throws SQLException;

	//비지니스 로직
//   ArrayList<Seat> getSeatList() throws SQLException; // 빈 Seat 목록 조회
//   void updateSeat(Seat seat) throws SQLException, RecordNotFoundException; // seat상태변경
   void buyTicket(String cust_id, String sche_code, String seat_name) throws SQLException;
   void refundTicket(String rsv_code) throws SQLException,InvalidTransactionException,RecordNotFoundException;
	
   //init
//   void initSeat() // 현재시간=상영종료시간 -> 모든 시트 사용 가능(이거 아닌데..)
}
