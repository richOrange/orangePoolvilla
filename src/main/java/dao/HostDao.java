package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
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
				+ " WHERE LEVEL > 4"
				+ " ORDER BY level desc";
		
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
	
	public void insertHost(Host host) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "INSERT INTO host (host_id, host_pw, level, name, email, phone, create_date, update_date)"
				+ " VALUES (?,PASSWORD(?),5,?,?,?,NOW(),NOW())";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.insertHost()] conn:" + conn);
			// 자동 커밋을 해제
			conn.setAutoCommit(false);
			
			// 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, host.getHostId());
			stmt.setString(2, host.getHostPw());
			
			stmt.setString(3, host.getName());
			stmt.setString(4, host.getEmail());
			stmt.setString(5, host.getPhone());
			
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[HostDao.insertHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.insertHost()] row : 입력 실패");
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public void deleteHost(String hostId, String hostPw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		// 관리자 삭제 쿼리가 적용이 되었는제 확인하기 위해 만든 정수형 변수 
		int row = 0;
		
		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장
		
		String sql = "DELETE FROM host WHERE host_id = ? AND host_pw = PASSWORD(?)";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			// 자동 커밋을 해제 
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, hostId);
			stmt.setString(2, hostPw);
			
			// 쿼리 실행 
			row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("[HostDao.deleteHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.deleteHost()] row : 입력 실패");
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		 
	}
	
	public void updateHost(Host host) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int row = 0;
		
		String dburl = "jdbc:mariadb://localhost:3307/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장
		
		// comma 자리에 AND 사용하면 에러 발생함 
		String sql = "UPDATE host"
				+ " SET host_pw = PASSWORD(?) , `level` = ? , `name` = ?"
				+ " , email = ? , phone = ? , create_date = NOW() , update_date = NOW()"
				+ " WHERE host_id = ?";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, host.getHostPw());
			stmt.setInt(2, host.getLevel());
			stmt.setString(3, host.getName());
			stmt.setString(4, host.getEmail());
			stmt.setString(5, host.getPhone());
			stmt.setString(6, host.getHostId());
			
			row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("[HostDao.updateHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.updateHost()] row : 입력 실패");
			}
			
			conn.commit();
		} catch (SQLException e) { 
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	// 회원 상세보기 모델 
	public Customer selectCustomerDetail(String customerId) {
		
		Customer customer = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT customer_id, customer_pw, `name`, gender, birth_date, email, phone, `level`, create_date, update_date"
				+ " FROM customer"
				+ " WHERE customer_id = ?"; 
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/orangepoolvilla","root","java1234");
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setCustomerPw(rs.getString("customer_pw"));
				customer.setName(rs.getString("name"));
				customer.setGender(rs.getString("gender"));
				customer.setBirthDate(rs.getString("birth_date"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setLevel(rs.getInt("level"));
				customer.setCreateDate(rs.getString("create_date"));
				customer.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return customer;
	}
	
	// 회원 상세보기 모델 
		public ArrayList<HashMap<String, Object>> selectCustomerList() {
			
			ArrayList<HashMap<String, Object>> customerList = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null; 
			
			String sql = "SELECT customer_id, `name`"
					+ " FROM customer"
					+ " ORDER BY update_date DESC"; 
			
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/orangepoolvilla","root","java1234");
			
				stmt = conn.prepareStatement(sql);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("customerId", rs.getString("customer_id"));
					map.put("name", rs.getString("name"));
					customerList.add(map);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			return customerList;
		}
}
