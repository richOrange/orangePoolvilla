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

import vo.Ott;
import vo.PoolvillaOtt;

public class OttDao {
	
	public void insertOtt(String ottName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ott(ott_name, update_date) VALUES (?, NOW());";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ottName);
			int row = stmt.executeUpdate();

			if (row == 1) {
				System.out.println("입력 성공");
			} else {
				System.out.println("입력 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
		// orangepoolvilla db의 ott 테이블 목록 가져오기
		public List<Ott> selectOttList() {
			List<Ott> list = new ArrayList<>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				String sql = "SELECT ott_no ottNo, ott_name ottName, update_date updateDate FROM ott";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Ott o = new Ott();
					o.setOttNo(rs.getInt("ottNo"));
					o.setOttName(rs.getString("ottName"));
					o.setUpdateDate(rs.getString("updateDate"));
					list.add(o);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		
		// pvno에 따른 orangepoolvilla db의 해당 풀빌라의 ott 테이블 목록 가져오기
		public List<Map<String,Object>> selectPoolvillaOttByPvNo(int pvNo) {
			List<Map<String,Object>> list = new ArrayList<>();
			
			// 데이터베이스 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				// 데이터베이스 드라이버 연결
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("[PoolvillaDao.selectPoolvillaOttByPvNo()] 드라이버 로딩 성공");
				
				String sql = "SELECT po.pv_no pvNo"
						+ "			, po.ott_no ottNo"
						+ "			, po.update_date updateDate"
						+ "			, o.ott_name ottName"
						+ "		FROM poolvilla_ott po"
						+ "		INNER JOIN ott o"
						+ "		ON po.ott_no = o.ott_no"
						+ "		WHERE po.pv_no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pvNo);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Map<String,Object> m = new HashMap<>();
					m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
					m.put("ottNo", rs.getInt("ottNo")); // ott 번호
					m.put("updateDate", rs.getString("updateDate")); // ott 글 수정 날짜
					m.put("ottName", rs.getString("ottName")); // ott 이름
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
		
		public int deleteOtt(int ottNo) {
			// DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt1 = null;
			PreparedStatement stmt2 = null;
			int row = -1;

			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("deleteOtt DB 로딩");
				// 오토커밋해제
				conn.setAutoCommit(false);
				String deletePoolvillaOttSql = "DELETE FROM poolvilla_ott WHERE ott_no = ? ";
				stmt1 = conn.prepareStatement(deletePoolvillaOttSql);
				stmt1.setInt(1, ottNo);
				row = stmt1.executeUpdate();
					System.out.println("poolvilla_ott 삭제한 행의 수 :" + row);
					String sql = "DELETE FROM ott WHERE ott_no = ?;";
					stmt2 = conn.prepareStatement(sql);
					stmt2.setInt(1, ottNo);
					row = stmt2.executeUpdate();
					if(row==0) {//결과가 0이면 삭제 실패, 롤백
						System.out.println("ott 삭제 실패");
						conn.rollback();
					} else {//결과가 0이 아니면 삭제 성공, 최종커밋
					System.out.println("ott 삭제 성공");
					conn.commit();
					}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 자원 반환
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		// pvNo에 따른 orangepoolvilla db의 poolvilla_ott 테이블 데이터 입력
		public List<PoolvillaOtt> insertPoolvillaOtt(int pvNo, int ottNo) {
			List<PoolvillaOtt> list = new ArrayList<>();
			
			// 데이터베이스 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int row = 0;
			
			try {
				// 데이터베이스 드라이버 연결
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("[OttDao.insertPoolvillaOtt()] 드라이버 로딩 성공");
				
				String sql = "INSERT INTO poolvilla_ott(pv_no, ott_no, update_date) VALUES (?, ?, NOW())";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, pvNo);
				stmt.setInt(2, ottNo);
				row = stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// 데이터베이스 자원 반환
					conn.close();
					
					// 디버깅 코드
					if(row == 1) {
						System.out.println("[OttDao.insertPoolvillaOtt()] poolvilla ott 입력 성공");
					} else {
						System.out.println("[OttDao.insertPoolvillaOtt()] poolvilla ott 입력 실패");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return list;
		}
		
		// pvNo와 ottNo에 따른 orangepoolvilla db의 poolvilla_ott 테이블 데이터 삭제
		public void deletePoolvillaOtt(int pvNo, int ottNo) {
			// 데이터베이스 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = 0;
			
			try {
				// 데이터베이스 드라이버 연결
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("[OttDao.deletePoolvillaOtt()] 드라이버 로딩 성공");
				
				String sql = "DELETE FROM poolvilla_ott WHERE pv_no = ? AND ott_no = ?";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, pvNo);
				stmt.setInt(2, ottNo);
				row = stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// 데이터베이스 자원 반환
					conn.close();
					
					// -디버깅 코드
					if(row == 1) {
						System.out.println("[OttDao.deletePoolvillaOtt()] poolvilla ott 삭제 성공");
					} else {
						System.out.println("[OttDao.deletePoolvillaOtt()] poolvilla ott 삭제 실패");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

}
