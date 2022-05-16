package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PoolvillaRoom;

public class PoolvillaRoomDao {
	// orangepoolvilla db의 poolvilla_room 테이블 데이터 입력
		public int insertPoolvillaRoom(PoolvillaRoom pr) { 
			
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = -1;
			
			String sql = "INSERT INTO poolvilla_room(pv_no, room_type, room_name, room_info, room_size, update_date) VALUES(?, ?, ?, ?, ?, NOW());"; 
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pr.getPvNo());
				stmt.setString(2, pr.getRoomType()); 
				stmt.setString(3, pr.getRoomName());
				stmt.setString(4, pr.getRoomInfo());
				stmt.setDouble(5, pr.getRoomSize());
				row = stmt.executeUpdate();

				if (row == 1) {
					System.out.println("입력 성공");
				} else {
					System.out.println("입력 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 자원 반환
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}

		// orangepoolvilla db의 poolvilla_room 테이블 목록 가져오기
		public List<PoolvillaRoom> selectPoolvillaRoomList() {
			List<PoolvillaRoom> list = new ArrayList<>();
			// DB 자원준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				String sql = "SELECT room_no roomNo, pv_no pvNo, room_type roomType, room_name roomName, room_info roomInfo, room_size roomSize, update_date updateDate FROM poolvilla_room;";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					PoolvillaRoom pr = new PoolvillaRoom();
					pr.setRoomNo(rs.getInt("roomNo"));
					pr.setPvNo(rs.getInt("pvNo"));
					pr.setRoomType(rs.getString("roomType"));
					pr.setRoomName(rs.getString("roomName"));
					pr.setRoomInfo(rs.getString("roomInfo"));
					pr.setRoomSize(rs.getDouble("roomSize"));
					pr.setUpdateDate(rs.getString("updateDate"));
					list.add(pr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 자원 반환
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
		public int deletePoolvillaRoom(int roomNo) {
			// DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			int row = 0;

			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("deletePoolvillaRoom DB 로딩");

				String sql = "DELETE FROM poolvilla_room WHERE room_no = ?;";
				stmt = conn.prepareStatement(sql);

				stmt.setInt(1, roomNo);
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
