package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PoolvillaPool;

public class PoolvillaPoolDao {
	// orangepoolvilla db의 poolvilla_pool 테이블 데이터 입력
		public int insertPoolvillaPool(PoolvillaPool pp) { 
			// DB 자원 준비
			
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = -1;
			String sql = "INSERT INTO poolvilla_pool(pv_no, pool_name, pool_width, pool_length, depth, hot_water, indoor_outdoor, update_date) VALUES(?, ?, ?, ?, ?, ?, ?, NOW());"; 
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pp.getPvNo());
				stmt.setString(2, pp.getPoolName());
				stmt.setDouble(3, pp.getPoolWidth());
				stmt.setDouble(4, pp.getPoolLength());
				stmt.setDouble(5, pp.getDepth());
				stmt.setString(6, pp.getHotWater());
				stmt.setString(7, pp.getIndoorOutdoor());
				System.out.println("stmt :" );
				row = stmt.executeUpdate();
				System.out.println("row :" + row);
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
			return row;
		}

		// orangepoolvilla db의 poolvilla_pool 테이블 목록 가져오기
		public List<PoolvillaPool> selectPoolvillaPoolList() {
			List<PoolvillaPool> list = new ArrayList<>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				String sql = "SELECT pool_no poolNo, pv_no pvNo, pool_name poolName, pool_width poolWidth, pool_length poolLength, depth, hot_water hotWater, indoor_outdoor indoorOutdoor, update_date updateDate FROM poolvilla_pool;";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					PoolvillaPool pp = new PoolvillaPool();
					pp.setPoolNo(rs.getInt("poolNo"));
					pp.setPvNo(rs.getInt("pvNo"));
					pp.setPoolName(rs.getString("poolName"));
					pp.setPoolWidth(rs.getDouble("poolWidth"));
					pp.setPoolLength(rs.getDouble("poolLength"));
					pp.setDepth(rs.getDouble("depth"));
					pp.setHotWater(rs.getString("hotWater"));
					pp.setIndoorOutdoor(rs.getString("indoorOutdoor"));
					pp.setUpdateDate(rs.getString("updateDate"));
					list.add(pp);
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
		// orangepoolvilla db의 poolvilla_pool 테이블 데이터 삭제
		public int deletePoolvillaPool(int poolNo) {
			// DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = 0;

			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("deletePoolvillaPool DB 로딩");

				String sql = "DELETE FROM poolvilla_pool WHERE pool_no = ?;";
				stmt = conn.prepareStatement(sql);

				stmt.setInt(1, poolNo);
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
		// pvNo에 따른 pool 정보 출력
		public List<PoolvillaPool> selectPoolvillaPoolListByPvNo(int pvNo) {
			List<PoolvillaPool> list = new ArrayList<>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				String sql = "SELECT pool_no poolNo"
						+ "		, pv_no pvNo"
						+ "		, pool_name poolName"
						+ "		, pool_width poolWidth"
						+ "		, pool_length poolLength"
						+ "		, depth, hot_water hotWater"
						+ "		, indoor_outdoor indoorOutdoor"
						+ "		, update_date updateDate"
						+ " FROM poolvilla_pool"
						+ " WHERE pv_no = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pvNo);
				
				rs = stmt.executeQuery();
				while (rs.next()) {
					PoolvillaPool pp = new PoolvillaPool();
					pp.setPoolNo(rs.getInt("poolNo"));
					pp.setPvNo(rs.getInt("pvNo"));
					pp.setPoolName(rs.getString("poolName"));
					pp.setPoolWidth(rs.getDouble("poolWidth"));
					pp.setPoolLength(rs.getDouble("poolLength"));
					pp.setDepth(rs.getDouble("depth"));
					pp.setHotWater(rs.getString("hotWater"));
					pp.setIndoorOutdoor(rs.getString("indoorOutdoor"));
					pp.setUpdateDate(rs.getString("updateDate"));
					list.add(pp);
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
	}
