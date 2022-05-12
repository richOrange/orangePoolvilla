package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Facility;

public class FacilityDao {

	// orangepoolvilla db의 facility 테이블 데이터 입력
	public void insertFacility(String facilityName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO facility(facility_name, update_date) VALUES (?, NOW());";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, facilityName);
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

	// orangepoolvilla db의 facility 테이블 목록 가져오기
	public List<Facility> selectFacilityList() {
		List<Facility> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT facility_no facilityNo, facility_name facilityName, update_date updateDate FROM facility";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Facility f = new Facility();
				f.setFacilityNo(rs.getInt("facilityNo"));
				f.setFacilityName(rs.getString("facilityName"));
				f.setUpdateDate(rs.getString("updateDate"));
				list.add(f);
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

	public int deleteFacility(int facilityNo) {
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;

		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("deleteFacility DB 로딩");

			String sql = "DELETE FROM facility WHERE facility_no = ?;";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, facilityNo);
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환
				conn.close();

				// 디버깅 코드
				if (row == 1) {
					System.out.println("facility 삭제 성공");
				} else {
					System.out.println("facility 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
