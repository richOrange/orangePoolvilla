package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

import java.sql.Connection;

import vo.Supplies;

public class SuppliesDao {
	
	/*
	 * SuppliesDao 목록
	 * 
	 * [host] selectSupplies() 메서드 : supplies 테이블 목록 가져오기
	 * [host] insertSupplies() 메서드 : supplies 테이블 데이터 입력
	 * [host] deleteSupplies() 메서드 : supplies 테이블 데이터 삭제
	 * 
	 * [host] selectPoolvillaSuppliesByPvNo() 메서드 : poolvilla_supplies 테이블 목록 가져오기
	 * [host] insertPoolvillaSupplies() 메서드 : poolvilla_supplies 테이블 데이터 입력
	 * [host] deletePoolvillaSupplies() 메서드 : poolvilla_supplies 테이블 데이터 삭제
	 */
	
	// orangepoolvilla db의 supplies 테이블 목록 가져오기
	public ArrayList<Supplies> selectSupplies() {
		ArrayList<Supplies> list = new ArrayList<Supplies>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			System.out.println("[SuppliesDao.insertSupplies()] 드라이버 로딩 성공");
			
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
					System.out.println("[SuppliesDao.insertSupplies()] supplies 입력 성공");
				} else {
					System.out.println("[SuppliesDao.insertSupplies()] supplies 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// orangepoolvilla db의 supplies 테이블 데이터 삭제
	public int deleteSupplies(int suppliesNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		int row = -1;
		
		try {
			// 데이터베이스 드라이버 연결
			System.out.println("[SuppliesDao.deleteSupplies()] 드라이버 로딩 성공");
			// 오토커밋해제
			conn.setAutoCommit(false);
			
			String deletePoolvillaSuppliesSql = "DELETE FROM poolvilla_supplies WHERE supplies_no = ? ";
			stmt1 = conn.prepareStatement(deletePoolvillaSuppliesSql);
			stmt1.setInt(1, suppliesNo);
			row = stmt1.executeUpdate();
				System.out.println("poolvilla_supplies 삭제한 행의 수 : " + row);
			String sql = "DELETE FROM supplies WHERE supplies_no = ?";
			stmt2 = conn.prepareStatement(sql);
			
			stmt2.setInt(1, suppliesNo);
			row = stmt2.executeUpdate();
			// -디버깅 코드
			if(row == 0) {
				System.out.println("[SuppliesDao.deleteSupplies()] supplies 삭제 성공");
			} else {
				System.out.println("[SuppliesDao.deleteSupplies()] supplies 삭제 실패");
				conn.commit();
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
		return row;
	}
	
	// orangepoolvilla db의 해당 풀빌라의 poolvilla_supplies 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaSuppliesByPvNo(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			System.out.println("[PoolvillaDao.selectPoolvillaSuppliesByPvNo()] 드라이버 로딩 성공");
			
			String sql = "SELECT ps.pv_no pvNo"
					+ "			, ps.supplies_no suppliesNo"
					+ "			, ps.supplies_cnt suppliesCnt"
					+ "			, ps.update_date updateDate"
					+ "			, s.supplies_name suppliesName"
					+ "		FROM poolvilla_supplies ps"
					+ "		INNER JOIN supplies s"
					+ "		ON ps.supplies_no = s.supplies_no"
					+ "		WHERE ps.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				m.put("suppliesNo", rs.getInt("suppliesNo")); // 구비 물품 번호
				m.put("suppliesCnt", rs.getInt("suppliesCnt")); // 구비 물품 개수
				m.put("updateDate", rs.getString("updateDate")); // 구비 물품 글 수정 날짜
				m.put("suppliesName", rs.getString("suppliesName")); // 구비 물품 이름
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
	
	// orangepoolvilla db의 해당 풀빌라의 poolvilla_supplies 테이블 데이터 입력
	public void insertPoolvillaSupplies(int pvNo, int suppliesNo, int suppliesCnt) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			System.out.println("[SuppliesDao.insertPoolvillaSupplies()] 드라이버 로딩 성공");
			
			String sql = "INSERT INTO poolvilla_supplies(pv_no, supplies_no, supplies_cnt, update_date) VALUES (?, ?, ?, NOW())";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setInt(2, suppliesNo);
			stmt.setInt(3, suppliesCnt);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[SuppliesDao.insertPoolvillaSupplies()] poolvilla_supplies 입력 성공");
				} else {
					System.out.println("[SuppliesDao.insertPoolvillaSupplies()] poolvilla_supplies 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// orangepoolvilla db의 해당 풀빌라의 poolvilla_supplies 테이블 데이터 삭제
	public void deletePoolvillaSupplies(int pvNo, int suppliesNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			System.out.println("[SuppliesDao.deletePoolvillaSupplies()] 드라이버 로딩 성공");
			
			String sql = "DELETE FROM poolvilla_supplies WHERE pv_no = ? AND supplies_no = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setInt(2, suppliesNo);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[SuppliesDao.deletePoolvillaSupplies()] poolvilla_supplies 삭제 성공");
				} else {
					System.out.println("[SuppliesDao.deletePoolvillaSupplies()] poolvilla_supplies 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
