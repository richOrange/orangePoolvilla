package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Ott;

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
		public int deleteOtt(int ottNo) {
			// DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = 0;

			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("deleteOtt DB 로딩");

				String sql = "DELETE FROM ott WHERE ott_no = ?;";
				stmt = conn.prepareStatement(sql);

				stmt.setInt(1, ottNo);
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 자원 반환
					conn.close();

					// 디버깅 코드
					if (row == 1) {
						System.out.println("삭제 성공");
					} else {
						System.out.println("삭제 실패");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}

}
