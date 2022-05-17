package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishListDao {
	// 찜 목록 테이블(wish_list)에 상품 번호와 고객 아이디를 등록하는 모델 
	public void insertWishList(int pvNo, String customerId) {
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장
		
		// 찜 목록 테이블 생성 쿼리 
		String sql = "INSERT INTO wish_list VALUES (?,?,NOW())";
		
		try {
			conn = DriverManager.getConnection(dburl,dbuser,dbpw);
			System.out.println("[WishListDao.insertWishList()] conn : " + conn);
			// 자동 커밋을 해제 
			conn.setAutoCommit(false);
			
			// 찜 목록 생성 쿼리를 저장 
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setString(2, customerId);
			
			// 찜 목록이 생성되면 1이라는 숫자값을 row에 저장 
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[WishListDao.insertWishList()] row : " + row);
			} else {
				System.out.println("[WishListDao.insertWishList()] row : 입력 실패");
			}
			
			// 커밋 실행 
			conn.commit();
		} catch (Exception e) {
			try {
				// 예외 발생시 롤백 
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 연결을 종료 
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
