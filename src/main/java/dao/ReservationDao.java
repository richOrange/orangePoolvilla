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
import vo.Reservation;

public class ReservationDao {
	
	
	/*-----------------------------------------공통 영역-------------------------------------------*/
	//요청값으로 예약상태 변경하는 메서드
	public int updateReservationStatusOfReservation(String reservationStatus,int reservationNo,String reservationStatusEditor ) {
		int row = -1; // 쿼리문 실패시 -1 반환
		String updateDate = null; // 변경된 updateDate 저장할 변수 초기화
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt1 = null; // reservation update에 사용
		PreparedStatement stmt2 = null; // select reservation_update_date에 사용
		PreparedStatement stmt3 = null; // reservation_status_history insert에 사용
		ResultSet rs = null; // select reservation_update_date에 사용 
		try {
			//DB에 요청
			conn = DBUtil.getConnection();
			System.out.println("[ReservationDao.updateReservationStatusOfReservation] DB연결");
			//오토커밋해제
			conn.setAutoCommit(false);
			//1. reservation테이블의 상태를 변경
			String checkReservationsql = "UPDATE reservation"
					+ "					 SET reservation_status = ?"
					+ "							,update_date= NOW() "
					+ "					WHERE reservation_no = ? ";
			stmt1 = conn.prepareStatement(checkReservationsql);
			stmt1.setString(1, reservationStatus);//reservationStatus 입력
			stmt1.setInt(2, reservationNo);//reservationStatus 입력
			row =stmt1.executeUpdate();//check
			if(row==0) { // 수정실패
				System.out.println("[ReservationDao.updateReservationStatus] reservation테이블 수정실패");
			}else if(row==1) { //수정성공 시에만 다음 진행
				System.out.println("[ReservationDao.updateReservationStatus] reservation테이블 수정성공");
			
				//2. reservation테이블의 update_date값을 select
				String selectReservationUpdateDateSpl = "SELECT res.update_date updateDate"
						+ "				FROM reservation res "
						+ "				WHERE res.reservation_no = ? ";
				stmt2 = conn.prepareStatement(selectReservationUpdateDateSpl);
				stmt2.setInt(1, reservationNo);
				rs = stmt2.executeQuery();
				if(rs.next()){ //변경된 reservation테이블의 updateDate를 저장
					updateDate = rs.getString("updateDate");
				}
				
				//3. reservation_status_history에 insert
				String insertReservationStatusHistorySql ="INSERT INTO reservation_status_history (reservation_no"
						+ "																	,reservation_status"
						+ "																	,reservation_status_update_date"
						+ "																	,reservation_status_editor ) "
						+ "												VALUES (?,?,?,?) ";
				stmt3 = conn.prepareStatement(insertReservationStatusHistorySql);
				stmt3.setInt(1, reservationNo);
				stmt3.setString(2, reservationStatus);
				stmt3.setString(3,updateDate);
				stmt3.setString(4,reservationStatusEditor);
				row = stmt3.executeUpdate(); // insert의 결과 물을 row에 저장
				if(row==0) {//row가 0일시, insert실패 롤백
					System.out.println("[ReservationDao.updateReservationStatus] reservation_status_history테이블 입력실패");
					conn.rollback();//롤백
				} else if(row==1) {//row가 1이면, insert 성공, 최종 커밋
					System.out.println("[ReservationDao.updateReservationStatus] reservation_status_history테이블 입력성공");
					conn.commit(); //최종 커밋
				}
			}
		} catch (Exception e) {
			try {
				conn.rollback(); //예외가 발생하면 롤백
			} catch(SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//DB자원반납
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		return row; //성공여부 반환
	}
	//예약상태 : 결제완료, 체크인 날짜가 오늘인 예약을 Select 하는 메서드
	public List<Integer> selectReservationBytodayCheckIn(String today){
		List<Integer> list = new ArrayList<>(); //예약상태 : 결제완료, 오늘 체크인이라 예약상태를 변경해야하는 예약을 리스트에 저장하는 변수 초기화
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[ReservationDao.selectReservationBytodayCheckIn()] 드라이버 로딩 성공");
			//쿼리 입력
			String sql = "SELECT reservation_no reservationNo"
					+ "			 FROM reservation "
					+ "			WHERE reservation_status = '결제완료' "
					+ "			AND reservation_begin_date = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, today);//오늘 날짜 입력
			rs = stmt.executeQuery();
			
			while (rs.next()) {
			list.add(rs.getInt("reservationNo"));
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
	//예약상태 : 이용중, 체크아웃 날짜가 오늘인 예약을 Select 하는 메서드
	public List<Integer> selectReservationBytodayCheckOut(String today){
		List<Integer> list = new ArrayList<>(); //예약상태 : 결제완료, 오늘 체크인이라 예약상태를 변경해야하는 예약을 리스트에 저장하는 변수 초기화
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[ReservationDao.selectReservationBytodayCheckOut()] 드라이버 로딩 성공");
			//쿼리 입력
			String sql = "SELECT reservation_no reservationNo"
					+ "			 FROM reservation "
					+ "			WHERE reservation_status = '이용중' "
					+ "			AND reservation_last_date = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, today);//오늘 날짜 입력
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("reservationNo"));
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
	
	
	
	
	/*-----------------------------------------개인 관련 영역-------------------------------------------*/
	
	
	//customer 개인 예약 목록 보기 메서드
	//myReservationController에서 호출
	public List<Map<String,Object>> selectMyReservationList (String customerId,String reservationStatus){
		//리턴값 받을 변수 초기화
		List<Map<String,Object>> myReservationList = new ArrayList<>();
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[ReservationDao.selectMyReservationList()] 드라이버 로딩 성공");
			//쿼리 작성
			String sql = "SELECT res.reservation_no reservationNo"
					+ "					, res.customer_id customerId"
					+ "					, res.pv_no pvNo"
					+ "					, pv.pv_name pvName"
					+ "					, res.reservation_begin_date reservationBeginDate"
					+ "					, res.reservation_last_date reservationLastDate"
					+ "					, res.reservation_status reservationStatus"
					+ "					, res.create_date createDate "
					+ "					, res.update_date updateDate "
					+ "		FROM reservation res "
					+ "		INNER JOIN poolvilla pv "
					+ "		ON res.pv_no = pv.pv_no "
					+ "		WHERE res.customer_id = ?"
					+ "		AND res.reservation_status LIKE ? "
					+ "		ORDER BY res.update_date DESC ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);//customerId로 검색
			stmt.setString(2, "%"+reservationStatus+"%");//예약상태
			rs = stmt.executeQuery();
			
			while (rs.next()) {
			Map<String,Object> m = new HashMap<>();
			m.put("reservationNo", rs.getInt("reservationNo"));
			m.put("customerId", rs.getString("customerId"));
			m.put("pvNo", rs.getInt("pvNo"));
			m.put("pvName", rs.getString("pvName"));
			m.put("reservationBeginDate", rs.getString("reservationBeginDate"));
			m.put("reservationLastDate", rs.getString("reservationLastDate"));
			m.put("reservationStatus", rs.getString("reservationStatus"));
			m.put("createDate", rs.getString("createDate"));
			m.put("updateDate", rs.getString("updateDate"));
			myReservationList.add(m);
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
		
		return myReservationList;
	}
	//예약 기능 메서드
	public int insertReservation(Reservation reservation) {
		//결과행 받을 변수 초기화
		int row = -1;
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//DB에 요청
			try {
				//DB 연결
				conn = DBUtil.getConnection();
				System.out.println("[reservationDao.insertReservation()] 드라이버 로딩 성공");
				conn.setAutoCommit(false); // 오토커밋해제
				//예약가능한지 검색하는 쿼리
				String checkReservationsql = "SELECT COUNT(*) cnt  "
						+ "								FROM reservation res "
						+ "								WHERE ((res.reservation_begin_date >= STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_begin_date < STR_TO_DATE(?,'%Y-%m-%d')) "
						+ "								OR (res.reservation_last_date > STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_last_date < STR_TO_DATE(?,'%Y-%m-%d'))) "
						+ "								AND res.pv_no = ?";
				stmt = conn.prepareStatement(checkReservationsql);
				stmt.setString(1, reservation.getReservationBeginDate());//checkIn 입력
				stmt.setString(2, reservation.getReservationLastDate()); //chekOut입력
				stmt.setString(3, reservation.getReservationBeginDate()); //checkIn입력
				stmt.setString(4, reservation.getReservationLastDate()); //checkOut입력
				stmt.setInt(5, reservation.getPvNo()); //풀빌라 번호 입력
				rs =stmt.executeQuery();//check
				if(rs.next()) {
					row = rs.getInt("cnt"); // 예약날짜가 겹치는 검색결과가 있으면 1, 없으면 0
				}
				if(row==1) { // row가 1이면 예약날짜 중복이 있으므로 예약불가
					System.out.println("[ReservationDao.insertReservation] 예약불가, 중복");
					row = 2; //row가 2이면 중복 오류임을 알수 있음
				}else if(row==0) {
					//row가 0이면 중복 값이 없으므로 insert
					PreparedStatement stmt2 = null;
					String insertReservationSql = "INSERT INTO reservation (customer_id,pv_no,reservation_begin_date,reservation_last_date,reservation_status,create_date,update_date)"
							+ "																	 VALUES (?,?,?,?,'결제대기',NOW(),NOW())  ";
					
				stmt2=conn.prepareStatement(insertReservationSql);
				stmt2.setString(1, reservation.getCustomerId());
				stmt2.setInt(2, reservation.getPvNo());
				stmt2.setString(3, reservation.getReservationBeginDate());
				stmt2.setString(4,reservation.getReservationLastDate());
				row = stmt2.executeUpdate(); // 결과값 새로 row에 입력 row가 1이면 성공, 0이면 중복은 안됫지만 실패
				}
				
				if(row==1) {// 최종적으로 row가 1이면 성공 커밋
					conn.commit(); //최종 커밋
				}else {//그이외에는 실패, rollback
					conn.rollback();
				}
			} catch (Exception e) {
				try {
					conn.rollback(); //예외가 발생하면 롤백
				} catch(SQLException e1) {
				e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					//DB자원반납 제일 중요한 conn 반납
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return row;
	}
	
	
	/*-----------------------------------------관리자 관련 영역-------------------------------------------*/	
	// RESERVATION 테이블의 예약 상태 중 결제대기,취소대기,이용중 갯수 가져오는 메서드
	public ArrayList<HashMap<String, Object>> selectWaitReservationStatusCount() {
		ArrayList<HashMap<String, Object>> selectWaitReservationStatusCount = new ArrayList<HashMap<String, Object>>();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[HostDao.selectWaitReservationStatusCount()] : 드라이버 로딩 성공");
			//쿼리 작성
			String sql = "SELECT reservation_status reservationStatus"
					+ "					, COUNT(*) cnt "
					+ "		FROM reservation "
					+ "		WHERE reservation_status = '결제대기' "
					+ "		OR reservation_status = '취소대기' "
					+ "		OR reservation_status = '이용중' "
					+ "GROUP BY reservationStatus";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("reservationStatus", rs.getString("reservationStatus"));
				map.put("cnt", rs.getInt("cnt"));
				selectWaitReservationStatusCount.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//DB자원반납
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return selectWaitReservationStatusCount;

	}
			
			// RESERVATION 테이블의 예약 상태 중 결제완료,취소,이용완료 갯수 가져오는 메서드
			public ArrayList<HashMap<String, Object>> selectCompleteReservationStatusCount() {
				ArrayList<HashMap<String, Object>> selectCompleteReservationStatusCount = new ArrayList<HashMap<String, Object>>();
				//DB자원준비
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				
				try {
					//DB 연결
					conn = DBUtil.getConnection();
					System.out.println("[HostDao.selectCompleteReservationStatusCount()] : 드라이버 로딩 성공");
					//쿼리 작성
					String sql = "SELECT reservation_status reservationStatus"
							+ "					, COUNT(*) cnt "
							+ "		FROM reservation "
							+ "		WHERE reservation_status = '결제완료' "
							+ "		OR reservation_status = '취소' "
							+ "		OR reservation_status = '이용완료' "
							+ "GROUP BY reservationStatus";
					stmt = conn.prepareStatement(sql);
					
					rs = stmt.executeQuery();
					
					while (rs.next()) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("reservationStatus", rs.getString("reservationStatus"));
						map.put("cnt", rs.getInt("cnt"));
						selectCompleteReservationStatusCount.add(map);
					}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//DB자원 반납
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return selectCompleteReservationStatusCount;

	}

	// RESERVATION 테이블 전체 목록 가져오는 메서드
	public ArrayList<HashMap<String, Object>> selectReservationList(int rowPerPage, int beginRow,
			String reservationStatus) {
		ArrayList<HashMap<String, Object>> reservationList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		/*
		 * SELECT customer_id, pv_no, concat(reservation_begin_date,' ~
		 * ',reservation_last_date) AS reservation_date, reservation_status,
		 * create_date, update_date FROM reservation ORDER BY reservation_date DESC
		 * LIMIT ?,?;
		 */
		//쿼리 들어갈 변수 초기화
		String sql = null;

		try {
			//DB연결
			conn = DBUtil.getConnection();
			System.out.println("[HostDao.selectReservationList()] : 드라이버 로딩 성공");

			if (reservationStatus.equals("")) {
			 sql = "SELECT reservation_no reservationNo"
						+ "			,customer_id customerId"
						+ "			,pv_no pvNo"
						+ "			, concat(reservation_begin_date,' ~ ',reservation_last_date) AS reservationDate"
						+ "			, reservation_status reservationStatus, create_date createDate, update_date updateDate"
						+ "		FROM reservation"
						+ " 	ORDER BY reservationDate DESC" 
						+ "    LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
			} else {
				sql = "SELECT reservation_no reservationNo"
						+ "			, customer_id customerId"
						+ "			, pv_no pvNo"
						+ "			, concat(reservation_begin_date,' ~ ',reservation_last_date) AS reservationDate"
						+ "			, reservation_status reservationStatus"
						+ "			, create_date createDate, update_date updateDate"
						+ "		FROM reservation"
						+ " 	WHERE reservation_status = ? "
						+ "		ORDER BY update_date DESC"
						+ " 	LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, reservationStatus);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			}

			rs = stmt.executeQuery();  

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("reservationNo", rs.getString("reservationNo"));//예약정보번호
				map.put("customerId", rs.getString("customerId"));//회원아이디
				map.put("pvNo", rs.getInt("pvNo"));//상품정보번호
				map.put("reservationDate", rs.getString("reservationDate")); //체크인날짜
				map.put("reservationStatus", rs.getString("reservationStatus")); //예약상태
				map.put("createDate", rs.getString("createDate")); //생성날짜
				map.put("updateDate", rs.getString("updateDate")); //수정날짜
				reservationList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return reservationList;

	}

	// RESERVATION 테이블 전체 행 갯수 구하는 메서드
	public int selectReservationTotalRow() {
		int totalRow = 0;

		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) cnt FROM reservation";
		try {
			System.out.println("[HostDao.selectTotalRow()] : 드라이버 로딩 성공");

			System.out.println("[HostDao.selectTotalRow()] conn:" + conn);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println("[HostDao.selectTotalRow()] totalRow :" + totalRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return totalRow;
	}
	
	


}
