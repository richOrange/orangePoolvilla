package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Host;
import vo.Reservation;

public class HostDao {

	// RESERVATION 테이블의 예약 상태별 갯수 가져오는 메서드
	public ArrayList<HashMap<String, Object>> selectReservationStatusCount() {
		ArrayList<HashMap<String, Object>> reservationStatusList = new ArrayList<HashMap<String, Object>>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		/*
		 * SELECT reservation_status reservationStatus, COUNT(*) cnt FROM reservation
		 * GROUP BY reservationStatus;
		 * 
		 * WHERE reservation_status = '결제완료' <- ? ; ( 사용 X )
		 */
		String sql = "SELECT reservation_status reservationStatus, COUNT(*) cnt FROM reservation GROUP BY reservationStatus";

		// #### 3306 포트로 변경하기 ! ####
		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		String dbuser = "root";
		String dbpw = "java1234";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("[HostDao.selectReservationStatusCount()] : 드라이버 로딩 성공");

			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectReservationStatusCount()] conn:" + conn);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
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

	// RESERVATION 테이블 전체 목록 가져오는 메서드
	public ArrayList<HashMap<String, Object>> selectReservationList(int rowPerPage, int beginRow,
			String reservationStatus) {
		ArrayList<HashMap<String, Object>> reservationList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		/*
		 * SELECT customer_id, pv_no, concat(reservation_begin_date,' ~
		 * ',reservation_last_date) AS reservation_date, reservation_status,
		 * create_date, update_date FROM reservation ORDER BY reservation_date DESC
		 * LIMIT ?,?;
		 */
		String sql = null;

		try {
			// 2022/05/12 이후 뺴야함
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("[HostDao.selectReservationList()] : 드라이버 로딩 성공");

			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectReservationList()] conn:" + conn);

			if (reservationStatus.equals("")) {
				sql = "SELECT customer_id customerId, pv_no pvNo, concat(reservation_begin_date,' ~ ',reservation_last_date) AS reservationDate"
						+ ", reservation_status reservationStatus, create_date createDate, update_date updateDate"
						+ "	FROM reservation" + "	ORDER BY reservationDate DESC" + " LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
			} else {
				sql = "SELECT customer_id customerId, pv_no pvNo, concat(reservation_begin_date,' ~ ',reservation_last_date) AS reservationDate"
						+ ", reservation_status reservationStatus, create_date createDate, update_date updateDate"
						+ "	FROM reservation" + " WHERE reservationStatus = ?" + "	ORDER BY reservationDate DESC"
						+ " LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, reservationStatus);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			}

			rs = stmt.executeQuery(); // 

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("customerId", rs.getString("customerId"));
				map.put("pvNo", rs.getInt("pvNo"));
				map.put("reservationDate", rs.getString("reservationDate"));
				map.put("reservationStatus", rs.getString("reservationStatus"));
				map.put("createDate", rs.getString("createDate"));
				map.put("updateDate", rs.getString("updateDate"));
				reservationList.add(map);
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

		return reservationList;

	}

	// RESERVATION 테이블 전체 행 갯수 구하는 메서드
	public int selectReservationTotalRow() {
		int totalRow = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "SELECT COUNT(*) cnt FROM reservation";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("[HostDao.selectTotalRow()] : 드라이버 로딩 성공");

			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectTotalRow()] conn:" + conn);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println("[HostDao.selectTotalRow()] totalRow :" + totalRow);
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

		return totalRow;
	}
	
	// HOST 테이블 전체 컬럼 구하는 메서드 
	public ArrayList<Host> selectHostList() {
		ArrayList<Host> hostList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "SELECT host_id hostId, host_pw hostPw, level, name, email, phone"
				+ ", create_date createDate, update_date updateDate"
				+ " FROM host"
				+ " WHERE LEVEL > 4";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectHostList()] conn:" + conn);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Host host = new Host();
				
				host.setHostId(rs.getString("hostId"));
				host.setHostPw(rs.getString("hostPw"));
				host.setLevel(rs.getInt("level"));
				host.setName(rs.getString("name"));
				host.setEmail(rs.getString("email"));
				host.setPhone(rs.getString("phone"));
				host.setCreateDate(rs.getString("createDate"));
				host.setUpdateDate(rs.getString("updateDate"));
				
				hostList.add(host);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}

		return hostList;
	}
}
