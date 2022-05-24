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

import vo.PoolvillaRoom;

public class PoolvillaRoomDao {
	
	/*
	 * PoolvillaRoomDao 목록
	 * 
	 * [host] selectPoolvillaRoomList() 메서드 : poolvilla_room 테이블 목록 가져오기 
	 * [host] deletePoolvillaRoom() 메서드 : poolvilla_pool 테이블 데이터 삭제
	 * 
	 * [host] selectPoolvillaRoomNBedByPvNo() 메서드 : room과 bed 테이블 목록 가져오기
	 * [host] selectPoolvillaRoomRoomName() 메서드 : pvNo에 따른 poolvilla_room 테이블 room_name 가져오기
	 * [host] insertPoolvillaRoom() 메서드 : poolvilla_room 테이블 데이터 입력
	 * [host] insertRoomBed() 메서드 : room_bed 테이블 데이터 입력
	 * [host] deletePoolvillaRoomNBed() 메서드 : poolvilla_room 테이블 데이터와 room_bed 데이터 삭제
	 */
	
	// orangepoolvilla db의 poolvilla_room 테이블 목록 가져오기
	public List<PoolvillaRoom> selectPoolvillaRoomList() {
		List<PoolvillaRoom> list = new ArrayList<>();
		// DB 자원준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT room_no roomNo, pv_no pvNo, room_type roomType, room_name roomName, room_info roomInfo, room_size roomSize, update_date updateDate FROM poolvilla_room";
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

	// orangepoolvilla db의 poolvilla_room 테이블 데이터 삭제
	public int deletePoolvillaRoom(int roomNo) {
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;

		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("deletePoolvillaRoom DB 로딩");

			String sql = "DELETE FROM poolvilla_room WHERE room_no = ?";
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
					System.out.println("[PoolvillaRoomDao.insertPoolvillaRoom] 삭제 성공");
				} else {
					System.out.println("[PoolvillaRoomDao.insertPoolvillaRoom] 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// orangepoolvilla db의 해당 풀빌라의 room과 bed 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaRoomNBedByPvNo(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[PoolvillaDao.selectPoolvillaRoomNBedByPvNo()] 드라이버 로딩 성공");

			String sql = "SELECT rb.bed_no bedNo"
					+ "			, pr.room_no roomNo"
					+ "			, GROUP_CONCAT(CONCAT(rb.bed_size, ' ', rb.bed_cnt) SEPARATOR ', ') bed"
					+ "			, rb.update_date updateDateRB"
					+ "			, pr.pv_no pvNo"
					+ "			, pr.room_type roomType"
					+ "			, pr.room_name roomName"
					+ "			, pr.room_info roomInfo"
					+ "			, pr.room_size roomSize"
					+ "			, pr.update_date updateDatePR"
					+ "		FROM poolvilla_room pr "
					+ "		LEFT JOIN room_bed rb "
					+ "		ON pr.room_no = rb.room_no "
					+ "		WHERE pr.pv_no = ? "
					+ "		GROUP BY pr.room_no ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("bedNo", rs.getInt("bedNo")); // 침대 번호
				m.put("roomNo", rs.getInt("roomNo")); // 방 번호
				m.put("bed", rs.getString("bed")); // 침대 사이즈 + 수량
				m.put("updateDateRB", rs.getString("updateDateRB")); // room_bed 테이블의 글 수정 날짜
				m.put("pvNo", rs.getInt("pvNo")); // 풀빌라 번호
				m.put("roomType", rs.getString("roomType")); // 방 유형
				m.put("roomName", rs.getString("roomName")); // 방 이름
				m.put("roomInfo", rs.getString("roomInfo")); // 방 정보
				m.put("roomSize", rs.getInt("roomSize")); // 방 사이즈
				m.put("updateDatePR", rs.getString("updateDatePR")); // poolvilla_room 테이블의 글 수정 날짜
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
	
	// orangepoolvilla db의 poolvilla_room 테이블 room_name 가져오기
	public List<Map<String,Object>> selectPoolvillaRoomRoomName(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		// DB 자원준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT room_no roomNo"
					+ "			, room_name roomName"
					+ "		FROM poolvilla_room "
					+ "		WHERE pv_no = ? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("roomNo", rs.getInt("roomNo")); // 방 번호
				m.put("roomName", rs.getString("roomName")); // 방 이름
				list.add(m);
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

	// orangepoolvilla db의 poolvilla_room 테이블 데이터 입력
	public int insertPoolvillaRoom(PoolvillaRoom pr) { 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = -1;
		
		String sql = "INSERT INTO poolvilla_room(pv_no, room_type, room_name, room_info, room_size, update_date) VALUES(?, ?, ?, ?, ?, NOW())"; 
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
				System.out.println("[PoolvillaRoomDao.insertPoolvillaRoom] 입력 성공");
			} else {
				System.out.println("[PoolvillaRoomDao.insertPoolvillaRoom] 입력 실패");
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
	
	// orangepoolvilla db의 room_bed 테이블 데이터 입력
	public void insertRoomBed(int pvNo, String bedSize, int bedCnt) { 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = -1;
		
		String sql = "INSERT INTO room_bed(room_no, bed_size, bed_cnt, update_date) VALUES(?, ?, ?, NOW())"; 
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			stmt.setString(2, bedSize); 
			stmt.setInt(3, bedCnt);
			
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환
				stmt.close();
				conn.close();
				
				// 디버깅
				if (row == 1) {
					System.out.println("[PoolvillaRoomDao.insertRoomBed] 입력 성공");
				} else {
					System.out.println("[PoolvillaRoomDao.insertRoomBed] 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// orangepoolvilla db의 poolvilla_room 테이블 데이터와 room_bed 데이터 삭제
	public void deletePoolvillaRoomNBed(int pvNo, int roomNo) {
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement rpStmt = null;
		PreparedStatement rbStmt = null;
		PreparedStatement prStmt = null;
		
		int row = 0;

		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] DB 로딩");
			conn.setAutoCommit(false); // 자동 커밋을 해제
			
			String rpSql = "DELETE FROM room_photo WHERE room_no = ?";
			rpStmt = conn.prepareStatement(rpSql);
			
			rpStmt.setInt(1, roomNo);
			
			rpStmt.executeUpdate();
			
			String rbSql = "DELETE FROM room_bed WHERE room_no = ?";
			rbStmt = conn.prepareStatement(rbSql);

			rbStmt.setInt(1, roomNo);
			
			rbStmt.executeUpdate();
			
			String prsql = "DELETE FROM poolvilla_room WHERE room_no = ?";
			prStmt = conn.prepareStatement(prsql);

			prStmt.setInt(1, roomNo);
			
			row = prStmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환
				conn.close();

				// 디버깅 코드
				if (row == 1) {
					System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] 삭제 성공");
				} else {
					System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
