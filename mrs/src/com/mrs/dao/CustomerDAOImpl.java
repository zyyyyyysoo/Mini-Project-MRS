package com.mrs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mrs.dto.Customer;
import com.mrs.dto.Movie;
import com.mrs.dto.Reservation;
import com.mrs.exception.DuplicateException;
import com.mrs.exception.InvalidTransactionException;
import com.mrs.exception.RecordNotFoundException;

import config.ServerInfo;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static CustomerDAOImpl dao = new CustomerDAOImpl();
	
	private CustomerDAOImpl() {};
	public static CustomerDAOImpl getInstance() {
		return dao;
	}

	public CustomerDAOImpl(String serverIp) throws ClassNotFoundException{
        Class.forName(ServerInfo.DRIVER_NAME);
        System.out.println("드라이버 로딩 성공..");
    }
	
	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS);
        System.out.println("Database Connection......");
        return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
	    if(conn!=null) conn.close();  
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		 if(rs!=null) rs.close();
		 closeAll(ps, conn);  
	}
	
	public boolean isExist(String cust_id, Connection conn)throws SQLException{
		conn=getConnect();
	    String sql ="SELECT cust_seq FROM customer WHERE cust_id=?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ps.setString(1,cust_id);
	    ResultSet rs = ps.executeQuery();
//	    System.out.println(rs.next());
	    return rs.next();
	}
	public boolean isExist(int cust_seq, Connection conn)throws SQLException{
		conn=getConnect();
	    String sql ="SELECT cust_seq FROM customer WHERE cust_seq=?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ps.setInt(1,cust_seq);
	    ResultSet rs = ps.executeQuery();
//	    System.out.println(rs.next());
	    return rs.next();
	}
	@Override
	public void addCustomer(Customer cust) throws SQLException, DuplicateException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if(!isExist(cust.getCust_id(), conn)) {
				String query = "INSERT INTO customer(cust_seq,name,cust_id,phone,email,age) VALUES(seq.NEXTVAL,?,?,?,?,?)";
//				String query = "INSERT INTO member(id,name,email,phone) VALUES(seq_id.NEXTVAL,?,?,?)";
				ps = conn.prepareStatement(query);
				System.out.println(ps+ "되냐...?");
				ps.setString(1, cust.getName());
				ps.setString(2, cust.getCust_id());
				ps.setString(3, cust.getPhone());
				ps.setString(4, cust.getEmail());
				ps.setInt(5, cust.getAge());
				System.out.println(ps.executeUpdate()+" ROW INSERT OK");
				System.out.println("잘 ... 되냐?");
			} else {
				throw new DuplicateException("Customer is already exist");
			}
		} finally {
			closeAll(ps, conn);
		}
		
	}

	@Override
	public void deleteCustomer(String cust_id) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if(isExist(cust_id, conn)) {
				String query = "DELETE customer WHERE cust_id=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, cust_id);
				int row = ps.executeUpdate();
				System.out.println(row+" ROW DELETE OK");
			} else {
				throw new RecordNotFoundException("No such a customer");
			}
		} finally {
			closeAll(ps, conn);
		}
		
	}
	
	@Override
	public void deleteCustomer(int cust_seq) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if(isExist(cust_seq, conn)) {
				String query = "DELETE customer WHERE cust_seq=?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, cust_seq);
				int row = ps.executeUpdate();
				System.out.println(row+" ROW DELETE OK");
			} else {
				throw new RecordNotFoundException("No such a customer");
			}
		} finally {
			closeAll(ps, conn);
		}
		
	}
	@Override
	public void updateCustomer(Customer cust) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if(isExist(cust.getCust_id(), conn)) {
				String query = "UPDATE customer SET name=?,phone=?,email=?,age=? WHERE cust_id=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, cust.getName());
				ps.setString(2, cust.getPhone());
				ps.setString(3, cust.getEmail());
				ps.setInt(4, cust.getAge());
				ps.setString(5, cust.getCust_id());
				System.out.println(ps.executeUpdate()+" ROW UPDATE OK");
			} else {
				throw new RecordNotFoundException("No such a customer");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public ArrayList<Movie> getAllMoive() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Movie> movies = new ArrayList<>();
		try {
			conn = getConnect();
			String query = "SELECT movie_code, m_name, d_name, genre, company, grade, capacity FROM movie";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				movies.add(new Movie(
						rs.getInt("movie_code"), 
						rs.getString("m_name"), 
						rs.getString("d_name"),
						rs.getString("genre"), 
						rs.getString("company"), 
						rs.getString("grade"),
						rs.getInt("capacity")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return movies;
	}

	@Override
	//내 예약 목록을 불러오기 위해 cust_seq이 필요한데 고객은 cust_id만 알고 있음.
	//JOIN?
	//우선 cust_seq로 구현
	public ArrayList<Reservation> getReservationList(int cust_seq) throws SQLException {
		ArrayList<Reservation> r = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			String query = "SELECT rsv_code, rsv_date, seat_name, rsv_state, movie_code FROM reservation WHERE cust_seq=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, cust_seq);
			rs = ps.executeQuery();
			while(rs.next()) {
				r.add(new Reservation(rs.getInt("rsv_code"), cust_seq, rs.getString("rsv_date"), rs.getString("seat_name"), rs.getString("rsv_state"), rs.getInt("movie_code")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return r;
	}

	@Override
	public Reservation getReservation(int rsv_code) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation r = null;
		try {
			conn = getConnect();
			String query = "SELECT cust_seq, rsv_date, seat_name, rsv_state, movie_code FROM reservation WHERE rsv_code=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, rsv_code);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				r = new Reservation(rsv_code, rs.getInt("cust_seq"), rs.getString("rsv_date"), rs.getString("seat_name"), rs.getString("rsv_state"), rs.getInt("movie_code"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return r;
	}

	@Override
	public boolean updateCapacity(int code, boolean flag) throws SQLException {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean isPossible = false;
        try {
            conn = getConnect();

            String query = "SELECT capacity FROM movie WHERE code=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, code);

            rs = ps.executeQuery();
            if(rs.next()) {
                int c=rs.getInt("capacity"); //c는 현재 남아 있는 좌석 수
                int newCapacity; // 구매하는 경우                
                if(flag) newCapacity = c-1;
                else newCapacity = c+1;
                String query1 = "UPDATE movie SET capacity=? WHERE movie_code=?";

                ps = conn.prepareStatement(query1);
                ps.setInt(1, newCapacity);
                ps.setInt(2, code);
                
                System.out.println(ps.executeUpdate()+" row updateCapacity()....UPDATE OK"); 
                isPossible = true;
            } 
        }finally {
            closeAll(rs, ps, conn);
        }
        return isPossible;
	}

	@Override
	public void buyTicket(String cust_id, int movie_code) throws SQLException {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn=  getConnect();

            rs = ps.executeQuery();
            if(updateCapacity(movie_code, true)) {

            	// 현재 날짜 구하기
                LocalDate now = LocalDate.now();
         
                // 포맷 정의
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         
                // 포맷 적용
                String formatedNow = now.format(formatter);
                String query1 = "INSERT reservation (rsv_code, rsv_date, seat_name, rsv_state, movie_code) VALUES(seq.NEXTVAL,?,?,?,?)";
                ps = conn.prepareStatement(query1);
                ps.setString(1, formatedNow);
                ps.setString(2, "자유석");
                ps.setString(3, "예매완료");
                ps.setInt(4, movie_code);
                
                System.out.println(ps.executeUpdate()+" row buyTicket()....INSERT OK");
            }
//	            else { //주식이 없는 경우..
//	                //INSERT
//	                String query2 ="INSERT INTO shares (ssn, symbol, quantity) VALUES(?,?,?)";
//	                ps = conn.prepareStatement(query2);
//	                ps.setString(1, ssn);
//	                ps.setString(2, symbol);
//	                ps.setInt(3, quantity);
//
//	                System.out.println(ps.executeUpdate()+" row buyShares()....INSERT OK");
//	            }
        }finally {
            closeAll(rs, ps, conn);
        }
	}

	@Override
	public void refundTicket(String cust_id, int movie_code)
			throws SQLException, InvalidTransactionException, RecordNotFoundException {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn= getConnect();

            rs = ps.executeQuery();
            if(updateCapacity(movie_code, false)) {
            	// 현재 날짜 구하기
                LocalDate now = LocalDate.now();
         
                // 포맷 정의
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         
                // 포맷 적용
                String formatedNow = now.format(formatter);
                String query1 = "DELETE reservation WHERE cust_id=? AND movie_code=?";
                ps = conn.prepareStatement(query1);
                ps.setString(1, cust_id);
                ps.setInt(2, movie_code);

                System.out.println(ps.executeUpdate()+" row refundTicket()....DELETE OK");
            }
        }finally {
            closeAll(rs, ps, conn);
        }
	}

//	@Override
//	public ArrayList<Seat> getSeatList() throws SQLException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		ArrayList<Seat> seats = new ArrayList<>();
//		try {
//			conn = getConnect();
//			String query = "SELECT seat_code, seat_name, seat_state,rsv_code FROM seat";
//			ps = conn.prepareStatement(query);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				seats.add(new Seat(rs.getInt("seat_code"), rs.getString("seat_name"),rs.getBoolean("seat_state"), rs.getInt("rsv_code")));
//			}
//		} finally {
//			closeAll(rs, ps, conn);
//		}
//		return seats;
//	}

//	@Override
//	// 한 예약에 해당하는 seat의 seat_state를 변경하는 것이 목적
//	// seat상태 변경을 위해서 seat_code와 rsv_code가 모두 필요
//	// seat가 Exist한지 check할 필요가 없음
//	// 아래대로 한다? 뭔가 아닌거같은데..
//	// UPDATE seat SET seat_state=? WHERE seat_code=?;
//	// 영화가 끝나면 모든 seat를 available 할 필요가 없긴하네
//	public void updateSeat(Seat seat) throws SQLException, RecordNotFoundException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = getConnect();
////			if(isExist(seat.getSeat_code(), conn)) {
//				String query = "UPDATE seat SET seat_state=? WHERE seat_code=?";
//				ps = conn.prepareStatement(query);
//				ps.setBoolean(1, seat.isSeat_state());
//				ps.setInt(2, seat.getSeat_code());
//				System.out.println(ps.executeUpdate()+" ROW UPDATE OK");
////			} else {
////				throw new RecordNotFoundException("No such a customer");
////			}
//		} finally {
//			closeAll(ps, conn);
//		}
//	}


	
}
