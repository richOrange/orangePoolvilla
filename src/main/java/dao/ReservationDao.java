package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vo.Reservation;

public class ReservationDao {
	//customer 개인 예약 목록 보기 메서드
	//myReservationController에서 호출
	public List<Map<String,Object>> selectMyReservationList (String customerId,String reservationStatus){
		List<Map<String,Object>> myReservationList = new ArrayList<>();
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[CustomerDao.checkIdInCustomer()] 드라이버 로딩 성공");
			
			String sql = "SELECT res.reservation_no reservationNo"
					+ "					, res.customer_id customerId"
					+ "					, res.pv_no pvNo"
					+ "					, pv.pv_name pvName"
					+ "					, res.reservation_begin_date reservationBeginDate"
					+ "					, res.reservation_last_date reservationLastDate"
					+ "					, res.reservation_status reservationStatus"
					+ "					, res.create_date createDate "
					+ "					, res.update_date updateDate "
					+ "		FROM reservation res "
					+ "		INNER JOIN poolvilla pv "
					+ "		ON res.pv_no = pv.pv_no "
					+ "		WHERE res.customer_id = ?"
					+ "		AND res.reservation_status LIKE ? "
					+ "		ORDER BY res.update_date DESC ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);//customerId로 검색
			stmt.setString(2, "%"+reservationStatus+"%");//예약상태
			rs = stmt.executeQuery();
			
			while (rs.next()) {
			Map<String,Object> m = new HashMap<>();
			m.put("reservationNo", rs.getInt("reservationNo"));
			m.put("customerId", rs.getString("customerId"));
			m.put("pvNo", rs.getInt("pvNo"));
			m.put("pvName", rs.getString("pvName"));
			m.put("reservationBeginDate", rs.getString("reservationBeginDate"));
			m.put("reservationLastDate", rs.getString("reservationLastDate"));
			m.put("reservationStatus", rs.getString("reservationStatus"));
			m.put("createDate", rs.getString("createDate"));
			m.put("updateDate", rs.getString("updateDate"));
			myReservationList.add(m);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return myReservationList;
	}
	//예약 기능 메서드
	public int insertReservation(Reservation reservation) {
		int row = -1;
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//DB에 요청
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				conn.setAutoCommit(false); // 오토커밋해제
				//예약가능한지 검색하는 쿼리
				String checkReservationsql = "SELECT COUNT(*) cnt  "
						+ "								FROM reservation res "
						+ "								WHERE ((res.reservation_begin_date >= STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_begin_date < STR_TO_DATE(?,'%Y-%m-%d')) "
						+ "								OR (res.reservation_last_date > STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_last_date < STR_TO_DATE(?,'%Y-%m-%d'))) "
						+ "								AND res.pv_no = ?";
				stmt = conn.prepareStatement(checkReservationsql);
				stmt.setString(1, reservation.getReservationBeginDate());//checkIn 입력
				stmt.setString(2, reservation.getReservationLastDate()); //chekOut입력
				stmt.setString(3, reservation.getReservationBeginDate()); //checkIn입력
				stmt.setString(4, reservation.getReservationLastDate()); //checkOut입력
				stmt.setInt(5, reservation.getPvNo()); //풀빌라 번호 입력
				rs =stmt.executeQuery();//check
				if(rs.next()) {
					row = rs.getInt("cnt"); // 예약날짜가 겹치는 검색결과가 있으면 1, 없으면 0
				}
				if(row==1) { // row가 1이면 예약날짜 중복이 있으므로 예약불가
					System.out.println("[ReservationDao.insertReservation] 예약불가, 중복");
					row = 2; //row가 2이면 중복 오류임을 알수 있음
				}else if(row==0) {
					//row가 0이면 중복 값이 없으므로 insert
					PreparedStatement stmt2 = null;
					String insertReservationSql = "INSERT INTO reservation (customer_id,pv_no,reservation_begin_date,reservation_last_date,reservation_status,create_date,update_date)"
							+ "																	 VALUES (?,?,?,?,'결제대기',NOW(),NOW())  ";
					
				stmt2=conn.prepareStatement(insertReservationSql);
				stmt2.setString(1, reservation.getCustomerId());
				stmt2.setInt(2, reservation.getPvNo());
				stmt2.setString(3, reservation.getReservationBeginDate());
				stmt2.setString(4,reservation.getReservationLastDate());
				row = stmt2.executeUpdate(); // 결과값 새로 row에 입력 row가 1이면 성공, 0이면 중복은 안됫지만 실패
				}
				conn.commit(); //최종 커밋
			} catch (Exception e) {
				try {
					conn.rollback(); //예외가 발생하면 롤백
				} catch(SQLException e1) {
				e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					//DB자원반납 제일 중요한 conn 반납
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return row;
	}


}
