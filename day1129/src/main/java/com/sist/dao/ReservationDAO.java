package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.sist.db.ConnectionProvider;
import com.sist.vo.PossibleReservationRoomVO;
import com.sist.vo.ReservationVO;

public class ReservationDAO {
	
	
	//예약테이블에 레코드를 추가하는 메소드 정의
	public String insertReservation(ReservationVO r) {
		String result = null;
		try {
			String sql = "insert into reservation("
					+ "reservation_no, "
					+ "checkin, "
					+ "checkout, "
					+ "phone, "
					+ "total, "
					+ "count_guest, "
					+ "customer_no, "
					+ "room_no, "
					+ "eno) "
					+ "values(?,"
					+ " ?, "
					+ "?,"
					+ "?,"
					+ "0,"
					+ "?, "
					+ "?, "
					+ "?, "
					+ "1000)";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, r.getReservation_no());			
			//예약을 성공하면 예약번호를 반환하도록 하기
			String no = getReservationNo();
			pstmt.setString(1, no);
			pstmt.setString(2, r.getCheckin());
			pstmt.setString(3, r.getCheckout());
			pstmt.setString(4, r.getPhone());
		//	pstmt.setInt(5, r.getTotal());
			pstmt.setInt(5, r.getCount_guest());
			pstmt.setInt(6, r.getCustomer_no());
			pstmt.setInt(7, r.getRoom_no());
			
			int re = pstmt.executeUpdate();
			if(re == 1) {
				result = no;
				System.out.println("예약번호:" +no);
				updateTotal(no);
			}
			ConnectionProvider.close(conn, pstmt, null);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return result;
	}
	
	
	
	//예약가능한 룸 목록을 반환하는 메소드 정의
	public ArrayList<PossibleReservationRoomVO> listPossibleReservationRoom(){
		ArrayList<PossibleReservationRoomVO> list = new ArrayList<PossibleReservationRoomVO>();
		String sql = "select room_no, fee, room_type_name, room_state_name"
				+ "		from room r, room_type t, room_state s"
				+ "		where r.room_type_code = t.room_type_code and "
				+ "		r.room_state_code = s.room_state_code and"
				+ "		r.room_state_code = 1";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new PossibleReservationRoomVO(rs.getInt(1), rs.getInt(2), 
						rs.getString(3), rs.getString(4)));
			}
			
			ConnectionProvider.close(conn, stmt, rs);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return list;
	}

	//다음 예약번호를 발행하는 메소드를 정의하기
	public String getReservationNo() {
		String no = "";
		Date today = new Date();
		int	year = today.getYear()+1900;
		int month = today.getMonth()+1;
		int date = today.getDate();
		
		int last = 0;
		String sql = " select nvl(max(substr(reservation_no,9)),0) +1 from reservation where substr(reservation_no,1,8) = to_char(sysdate, 'yyyymmdd')";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				last =rs.getInt(1);
				no = year + "" + month + "" + date + last;		 
				
			}
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return no;
	}	
	
	public void updateTotal(String reservation_no) {
		System.out.println("updateTotal 동작함===>" + reservation_no);
		String sql =" update reservation set total =(select fee* (select checkout- checkin "
				+ "	 from reservation"
				+ "	 where reservation_no = ?) from room where room_no =(select room_no from"
				+ "	 reservation where reservation_no= ?))"
				+ "	 where reservation_no = ?";
		
		try {
			Connection conn =ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation_no);
			pstmt.setString(2, reservation_no);
			pstmt.setString(3, reservation_no);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
	}
}
