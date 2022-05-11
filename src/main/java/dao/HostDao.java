package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Reservation;

public class HostDao {
	public ArrayList<HashMap<String, Object>> selectReservationStatusCount() {
		ArrayList<HashMap<String, Object>> reservationStatusList = new ArrayList<HashMap<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		/*
			SELECT reservation_status reservationStatus, COUNT(*) cnt 
			FROM reservation
			GROUP BY reservationStatus; 
			
			WHERE reservation_status = '결제완료' <- ? ; ( 사용 X ) 
		 */
		String sql = "SELECT reservation_status reservationStatus, COUNT(*) cnt FROM reservation GROUP BY reservationStatus";
		
		// #### 3306 포트로 변경하기 ! ####
		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		String dbuser = "root";
		String dbpw = "java1234"; 
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 로딩 성공 <-- [장주현] HostDao.selectReservationStatusCount()");
			
			conn = DriverManager.getConnection(dburl,dbuser,dbpw);
			System.out.println(conn + " <-- [장주현] conn // HostDao.selectReservationStatusCount()");
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("reservationStatus", rs.getString("reservationStatus"));
				map.put("cnt", rs.getInt("cnt"));
				reservationStatusList.add(map);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
		return reservationStatusList;
		
	}
	
	public ArrayList<Reservation> selectReservationList(int rowPerPage, int beginRow, String reservationStatus) {
		Reservation reservation = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		/*
			SELECT customer_id, pv_no, concat(reservation_begin_date,' ~ ',reservation_last_date) AS reservation_date, 
			reservation_status, create_date, update_date 
			FROM reservation 
			ORDER BY reservation_date DESC 
			LIMIT ?,?;
		 */
		String sql = "";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 로딩 성공 <-- HostDao.selectReservationList() ");
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}
