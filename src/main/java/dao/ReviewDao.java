package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReviewDao {
	
	// 사용자 리뷰 목록 확인하는 메서드 
	public ArrayList<HashMap<String, Object>> selectReviewList(String customerId, int beginRow, int rowPerPage) {
		
		// 반환값으로 사용할 변수 선언 
		ArrayList<HashMap<String, Object>> reviewList = new ArrayList<>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT pv.pv_name pvName, loc.location_name locationName"
				+ " , rv.reservation_begin_date reservationBeginDate"
				+ " , rv.reservation_last_date reservationLastDate"
				+ " , r.update_date updateDate"
				+ " FROM review r"
				+ " RIGHT JOIN reservation rv"
				+ " ON r.reservation_no = rv.reservation_no"
				+ " INNER JOIN customer c"
				+ " ON rv.customer_id = c.customer_id"
				+ " INNER JOIN poolvilla pv"
				+ " ON rv.pv_no = pv.pv_no"
				+ " INNER JOIN poolvilla_location loc"
				+ " ON pv.location_no = loc.location_no"
				+ " WHERE c.customer_id = ? AND rv.reservation_status = '이용완료'"
				+ " LIMIT ?,?";
		
		try {
			// DB 연결 
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			// 디버깅 
			System.out.println("[ReviewDao.selectReviewList()] conn : " + conn);
			
			// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			
			// 테이블에 쿼리 내용들을 저장한다 
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 1회용 VO HashMap 인스턴스 생성 
				HashMap<String, Object> map = new HashMap<>();
				
				// map 인스턴스에 데이터 저장 
				map.put("pvName", rs.getString("pvName"));
				map.put("locationName", rs.getString("locationName"));
				map.put("reservationBeginDate", rs.getString("reservationBeginDate"));
				map.put("reservationLastDate", rs.getString("reservationLastDate"));
				map.put("updateDate", rs.getString("updateDate"));
				
				// 동적 배열에 map 데이터 저장 
				reviewList.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료 
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
		
		// 반환값 
		return reviewList;
	}
	
	// 리뷰 목록 전체 행의 수 구하는 메서드 
	public int selectReviewListTotalRow(String customerId) {
		int totalRow = 0; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "";
		
		try {
		

			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[WishListDao.selectReviewListTotalRow()] conn:" + conn);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println("[WishListDao.selectReviewListTotalRow()] totalRow :" + totalRow);
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
}
