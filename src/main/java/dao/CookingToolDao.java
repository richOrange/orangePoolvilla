package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import vo.CookingTool;
import vo.PoolvillaCookingTool;

public class CookingToolDao {
	
	/*
	 * CookingToolDao 목록
	 * 
	 * [host] selectCookingToolList() 메서드 : cookingtool 테이블 목록 가져오기
	 * [host] insertCookingTool() 메서드 : cookingtool 테이블 데이터 입력
	 * [host] deleteCookingTool() 메서드 : cookingtool 테이블 데이터 삭제
	 * 
	 * [host] selectPoolvillaCookingToolList() 메서드 : pvNo에 따른 poolvilla_cooking_tool 테이블 목록 가져오기
	 * [host] insertPoolvillaCookingTool() 메서드 : pvNo에 따른 poolvilla_cooking_tool 테이블 데이터 입력
	 * [host] deletePoolvillaCookingTool() 메서드 : pvNo와 cookingToolNo에 따른 poolvilla_cooking_tool 테이블 데이터 삭제
	 */
	
	// orangepoolvilla db의 cookingtool 테이블 목록 가져오기
	public ArrayList<CookingTool> selectCookingToolList() {
		ArrayList<CookingTool> list = new ArrayList<CookingTool>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
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
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
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
	public int deleteCookingTool(int cookingToolNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		int row = -1;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
			System.out.println("[CookingToolDao.deleteCookingTool()] 드라이버 로딩 성공");
			// 오토커밋 해제
			conn.setAutoCommit(false);
			String deletePoolvillaCookingToolSql = "DELETE FROM poolvilla_cooking_tool WHERE cooking_tool_no = ? ";
			String sql = "DELETE FROM cooking_tool WHERE cooking_tool_no = ?";
			stmt1 = conn.prepareStatement(deletePoolvillaCookingToolSql);
			stmt1.setInt(1, cookingToolNo);
			row = stmt1.executeUpdate();
				System.out.println("poolvilla_cooking_tool 삭제한 행의 수 :" +row);
			stmt2 = conn.prepareStatement(sql);
			
			stmt2.setInt(1, cookingToolNo);
			row = stmt2.executeUpdate();
			if(row == 0) {
				System.out.println("[CookingToolDao.deleteCookingTool()] cooking tool 삭제 성공");
			} else {
				System.out.println("[CookingToolDao.deleteCookingTool()] cooking tool 삭제 실패");
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// pvNo에 따른 orangepoolvilla db의 poolvilla_cooking_tool 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaCookingToolList(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
			System.out.println("[CookingToolDao.selectPoolvillaCookingTool()] 드라이버 로딩 성공");
			
			String sql = "SELECT pct.pv_no pvNo"
					+ "			, ct.cooking_tool_no cookingToolNo"
					+ "			, ct.cooking_tool_name cookingToolName"
					+ "			, pct.cooking_tool_cnt cookingToolCnt"
					+ "			, pct.update_date updateDate"
					+ " FROM cooking_tool ct "
					+ " INNER JOIN poolvilla_cooking_tool pct "
					+ " ON pct.cooking_tool_no = ct.cooking_tool_no "
					+ " WHERE pct.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); // 풀빌라 번호
				m.put("cookingToolNo", rs.getInt("cookingToolNo")); // 취사 도구 번호
				m.put("cookingToolCnt", rs.getInt("cookingToolCnt")); // 취사 도구 수량
				m.put("cookingToolName", rs.getString("cookingToolName")); // 취사 도구 이름
				m.put("updateDate", rs.getString("updateDate")); // poolvilla_cooking_tool 글 날짜
				list.add(m);
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
	
	// pvNo에 따른 orangepoolvilla db의 poolvilla_cooking_tool 테이블 데이터 입력
	public List<PoolvillaCookingTool> insertPoolvillaCookingTool(int pvNo, int cookingToolNo, int cookingToolCnt) {
		List<PoolvillaCookingTool> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
			System.out.println("[CookingToolDao.insertPoolvillaCookingTool()] 드라이버 로딩 성공");
			
			String sql = "INSERT INTO poolvilla_cooking_tool(pv_no, cooking_tool_no, cooking_tool_cnt, update_date) VALUES (?, ?, ?, NOW())";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setInt(2, cookingToolNo);
			stmt.setInt(3, cookingToolCnt);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// 디버깅 코드
				if(row == 1) {
					System.out.println("[CookingToolDao.insertPoolvillaCookingTool()] poolvilla cooking tool 입력 성공");
				} else {
					System.out.println("[CookingToolDao.insertPoolvillaCookingTool()] poolvilla cooking tool 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// pvNo와 cookingToolNo에 따른 orangepoolvilla db의 poolvilla_cooking_tool 테이블 데이터 삭제
	public void deletePoolvillaCookingTool(int pvNo, int cookingToolNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "mariadb1234");
			System.out.println("[CookingToolDao.deletePoolvillaCookingTool()] 드라이버 로딩 성공");
			
			String sql = "DELETE FROM poolvilla_cooking_tool WHERE pv_no = ? AND cooking_tool_no = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setInt(2, cookingToolNo);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[CookingToolDao.deletePoolvillaCookingTool()] poolvilla cooking tool 삭제 성공");
				} else {
					System.out.println("[CookingToolDao.deletePoolvillaCookingTool()] poolvilla cooking tool 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
