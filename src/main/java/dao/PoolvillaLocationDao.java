package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.PoolvillaLocation;

public class PoolvillaLocationDao {
	// orangepoolvilla db의 poolvilla_location 테이블 데이터 입력
	public void insertPoolvillaLocation(String locationName) {
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//쿼리 작성
		String sql = "INSERT INTO poolvilla_location(location_name, update_date) VALUES (?, NOW());";
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, locationName);
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

	// orangepoolvilla db의 poolvilla_location 테이블 목록 가져오기
	public List<PoolvillaLocation> selectPoolvillaLocationList() {
		//리턴 변수 초기화
		List<PoolvillaLocation> list = new ArrayList<>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			//쿼리 작성
			String sql = "SELECT location_no locationNo, location_name locationName, update_date updateDate FROM poolvilla_location";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				PoolvillaLocation pl = new PoolvillaLocation();
				pl.setLocationNo(rs.getInt("locationNo"));
				pl.setLocationName(rs.getString("locationName"));
				pl.setUpdateDate(rs.getString("updateDate"));
				list.add(pl);
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
	// orangepoolvilla db의 poolvilla_location 테이블 데이터 삭제
	public int deletePoolvillaLocation(int locationNo) {
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//결과 행 받을 변수 초기화
		int row = 0;

		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("deletePoolvillaLocation DB 로딩");

			String sql = "DELETE FROM poolvilla_location WHERE location_no = ?;";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, locationNo);
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환
				conn.close();

				// 디버깅 코드
				if (row == 1) {
					System.out.println("location 삭제 성공");
				} else {
					System.out.println("location 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
