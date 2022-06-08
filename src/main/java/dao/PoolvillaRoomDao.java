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

import util.DBUtil;
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
		//리턴값 받을 변수 초기화
		List<PoolvillaRoom> list = new ArrayList<>();
		// DB 자원준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리작성
		String sql = "SELECT room_no roomNo, pv_no pvNo, room_type roomType, room_name roomName, room_info roomInfo, room_size roomSize, update_date updateDate FROM poolvilla_room";
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.selectPoolvillaRoomList] : DB연결");
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
		//결과 행 값 받을 변수 초기화
		int row = 0;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.deletePoolvillaRoom] : DB연결");
			//쿼리 작성
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
					System.out.println("[PoolvillaRoomDao.deletePoolvillaRoom] 삭제 성공");
				} else {
					System.out.println("[PoolvillaRoomDao.deletePoolvillaRoom] 삭제 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// orangepoolvilla db의 해당 풀빌라의 room과 bed 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaRoomNBedByPvNo(int pvNo) {
		//리턴값 받을 변수 초기화
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.selectPoolvillaRoomNBedByPvNo] DB연결");
			//쿼리작성
			String sql = "SELECT rb.bed_no bedNo"
					+ "									, pr.room_no roomNo"
					+ "									, CONCAT(rb.bed_size,' ',rb.bed_cnt,'') bed "
					+ "									, rb.update_date updateDateRB"
					+ "									, pr.pv_no pvNo"
					+ "									, pr.room_type roomType"
					+ "									, pr.room_name roomName"
					+ "									, pr.room_info roomInfo"
					+ "									, pr.room_size roomSize"
					+ "									, pr.update_date updateDatePR"
					+ "									, rp.photo_name photoName"
					+ "									, rp.photo_original_name photoOriginalName"
					+ "								FROM poolvilla_room pr"
					+ "								LEFT JOIN room_bed rb"
					+ "								ON pr.room_no = rb.room_no"
					+ "								LEFT JOIN room_photo rp"
					+ "								ON rp.room_no = pr.room_no"
					+ "								WHERE pr.pv_no = ?"
					+ "								GROUP BY pr.room_no";
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
				m.put("photoName", rs.getString("photoName")); //방사진 경로에 사진 이름
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
		//리턴값 받을 변수 초기화
		List<Map<String,Object>> list = new ArrayList<>();
		// DB 자원준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.selectPoolvillaRoomRoomName] DB연결");

			//쿼리작성
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
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//결과 행 값 받을 변수 초기화
		int row = -1;
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.insertPoolvillaRoom] DB연결");

			//쿼리작성
			String sql = "INSERT INTO poolvilla_room(pv_no, room_type, room_name, room_info, room_size, update_date) VALUES(?, ?, ?, ?, ?, NOW())"; 
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
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//결과 행 수 값 반환할 변수 초기화
		int row = -1;
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.insertRoomBed] DB연결");
			//쿼리 작성
			String sql = "INSERT INTO room_bed(room_no, bed_size, bed_cnt, update_date) VALUES(?, ?, ?, NOW())"; 
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

	// 방 삭제시 orangepoolvilla db의 poolvilla_room 테이블 데이터와 room_bed 데이터 삭제
	public void deletePoolvillaRoomNBed(int pvNo, int roomNo) {
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement rpStmt = null; //delete roomphoto stmt
		PreparedStatement rbStmt = null;//delete roombed stmt
		PreparedStatement prStmt = null;//delete poolvila_room stmt
		//결과 행값 받을 변수 초기화
		int row = 0;

		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] DB 로딩");
			conn.setAutoCommit(false); // 자동 커밋을 해제
			//쿼리1. 방 번호에 따른 room_photo 테이블 정보 삭제 
			String rpSql = "DELETE FROM room_photo WHERE room_no = ?";
			rpStmt = conn.prepareStatement(rpSql);
			rpStmt.setInt(1, roomNo);
			rpStmt.executeUpdate();
			//쿼리2. 방번호에 따른 room_bed 테이블 정보 삭제
			String rbSql = "DELETE FROM room_bed WHERE room_no = ?";
			rbStmt = conn.prepareStatement(rbSql);
			rbStmt.setInt(1, roomNo);
			rbStmt.executeUpdate();
			//쿼리3. 방번호에 따른 poolvilla_room 삭제
			String prsql = "DELETE FROM poolvilla_room WHERE room_no = ?";
			prStmt = conn.prepareStatement(prsql);
			prStmt.setInt(1, roomNo);
			row = prStmt.executeUpdate(); // room 삭제 성공 여부
			if(row==1) {//성공시 commit
				System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] 삭제 성공");
				conn.commit();
			} else {//실패시 rollback
				System.out.println("[PoolvillaRoomDao.deletePoolvillaRoomNBed] 삭제 실패");
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback(); //예외가 발생하면 롤백
			} catch(SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
