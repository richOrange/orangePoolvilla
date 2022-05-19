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

import vo.Facility;
import vo.PoolvillaFacility;

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
	// pvNo에 따른 facility정보 출력
	public List<Map<String, Object>> selectPoolvillaFacilityListByPvNo(int pvNo){
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT pf.pv_no pvNo"
					+ "		, pf.facility_no facilityNo"
					+ "		, pf.update_date updateDate"
					+ "		, pf.facility_cnt facilityCnt"
					+ "		, f.facility_name facilityName"
					+ " FROM poolvilla_facility pf INNER join facility f"
					+ " ON pf.facility_no = f.facility_no"
					+ " WHERE pv_no = ?;";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				map.put("facilityNo", rs.getInt("facilityNo"));
				map.put("updateDate", rs.getString("updateDate"));
				map.put("facilityCnt", rs.getInt("facilityCnt"));
				map.put("facilityName", rs.getString("facilityName"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// pvNo에 따른 orangepoolvilla db의 poolvilla_facility 테이블 데이터 입력
			public List<PoolvillaFacility> insertPoolvillaFacility(int pvNo, int facilityNo, int facilityCnt) {
				List<PoolvillaFacility> list = new ArrayList<>();
				
				// 데이터베이스 자원 준비
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int row = 0;
				
				try {
					// 데이터베이스 드라이버 연결
					conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
					System.out.println("[FacilityDao.insertPoolvillaFacility()] 드라이버 로딩 성공");
					
					String sql = "INSERT INTO poolvilla_facility(pv_no, facility_no, facility_cnt, update_date) VALUES (?, ?, ?, NOW())";
					stmt = conn.prepareStatement(sql);
					
					stmt.setInt(1, pvNo);
					stmt.setInt(2, facilityNo);
					stmt.setInt(3, facilityCnt);
					row = stmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						// 데이터베이스 자원 반환
						conn.close();
						
						// 디버깅 코드
						if(row == 1) {
							System.out.println("[FacilityDao.insertPoolvillaFacility()] poolvilla facility 입력 성공");
						} else {
							System.out.println("[FacilityDao.insertPoolvillaFacility()] poolvilla facility 입력 실패");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return list;
			}
	
	// pvNo와 facilityNo에 따른 orangepoolvilla db의 poolvilla_facility 테이블 데이터 삭제
			public void deletePoolvillFacility(int pvNo, int facilityNo) {
				// 데이터베이스 자원 준비
				Connection conn = null;
				PreparedStatement stmt = null;
				int row = 0;
				
				try {
					// 데이터베이스 드라이버 연결
					conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
					System.out.println("[FacilityDao.deletePoolvillaFacility()] 드라이버 로딩 성공");
					
					String sql = "DELETE FROM poolvilla_facility WHERE pv_no = ? AND facility_no = ?";
					stmt = conn.prepareStatement(sql);
					
					stmt.setInt(1, pvNo);
					stmt.setInt(2, facilityNo);
					row = stmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						// 데이터베이스 자원 반환
						conn.close();
						
						// -디버깅 코드
						if(row == 1) {
							System.out.println("[FacilityDao.deletePoolvillaFacility()] poolvilla facility 삭제 성공");
						} else {
							System.out.println("[FacilityDao.deletePoolvillaFacility()] poolvilla facility 삭제 실패");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
}
