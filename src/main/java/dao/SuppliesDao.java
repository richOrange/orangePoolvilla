package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import vo.Supplies;

public class SuppliesDao {
	
	// orangepoolvilla db의 supplies 테이블 목록 가져오기
	public ArrayList<Supplies> selectSupplies() {
		ArrayList<Supplies> list = new ArrayList<Supplies>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[SuppliesDao.selectSupplies()] 드라이버 로딩 성공");
			
			String sql = "SELECT supplies_no suppliesNo, supplies_name suppliesName, update_date updateDate FROM supplies";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Supplies supplies = new Supplies();
				supplies.setSuppliesNo(rs.getInt("suppliesNo"));
				supplies.setSuppliesName(rs.getString("suppliesName"));
				supplies.setUpdateDate(rs.getString("updateDate"));
				list.add(supplies);
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
		
		return list;
	}
	
	// orangepoolvilla db의 supplies 테이블 데이터 입력
	public void insertSupplies(String suppliesName) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[SuppliesDao.insertCookingTool()] 드라이버 로딩 성공");
			
			String sql = "INSERT INTO supplies(supplies_name, update_date) VALUES (?, NOW())";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, suppliesName);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[SuppliesDao.insertCookingTool()] supplies 입력 성공");
				} else {
					System.out.println("[SuppliesDao.insertCookingTool()] supplies 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// orangepoolvilla db의 supplies 테이블 데이터 삭제
	public void deleteSupplies(int suppliesNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[SuppliesDao.deleteSupplies()] 드라이버 로딩 성공");
			
			String sql = "DELETE FROM supplies WHERE supplies_no = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, suppliesNo);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[SuppliesDao.deleteSupplies()] supplies 삭제 성공");
				} else {
					System.out.println("[SuppliesDao.deleteSupplies()] supplies 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
