package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import vo.CookingTool;

public class CookingToolDao {
	
	// orangepoolvilla db의 cookingtool 테이블 목록 가져오기
	public ArrayList<CookingTool> selectCookingTool() {
		ArrayList<CookingTool> list = new ArrayList<CookingTool>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[CookingToolDao.selectCookingTool()] 드라이버 로딩 성공");
			
			String sql = "SELECT cooking_tool_no cookingToolNo, cooking_tool_name cookingToolName, update_date updateDate FROM cooking_tool";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CookingTool ct = new CookingTool();
				ct.setCookingToolNo(rs.getInt("cookingToolNo"));
				ct.setCookingToolName(rs.getString("cookingToolName"));
				ct.setUpdateDate(rs.getString("updateDate"));
				list.add(ct);
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
	
	// orangepoolvilla db의 cookingtool 테이블 데이터 입력
	public void insertCookingTool(String cookingToolName) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[CookingToolDao.insertCookingTool()] 드라이버 로딩 성공");
			
			String sql = "INSERT INTO cooking_tool(cooking_tool_name, update_date) VALUES (?, NOW())";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cookingToolName);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[CookingToolDao.insertCookingTool()] cooking tool 입력 성공");
				} else {
					System.out.println("[CookingToolDao.insertCookingTool()] cooking tool 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// orangepoolvilla db의 cookingtool 테이블 데이터 삭제
	public void deleteCookingTool(int cookingToolNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[CookingToolDao.deleteCookingTool()] 드라이버 로딩 성공");
			
			String sql = "DELETE FROM cooking_tool WHERE cooking_tool_no = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, cookingToolNo);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[CookingToolDao.deleteCookingTool()] cooking tool 삭제 성공");
				} else {
					System.out.println("[CookingToolDao.deleteCookingTool()] cooking tool 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
