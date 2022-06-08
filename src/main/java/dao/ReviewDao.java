package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DBUtil;
import vo.Review;

public class ReviewDao {
	
public Review selectReviewOnePerCustomer(int reservationNo) {
		
		// 반환값으로 사용할 변수 선언 
		Review reviewOne = new Review();
		
		// DB 자원 준비 
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cleanliness"
				+ "		, revisit"
				+ "		, satisfaction"
				+ "		, opinion"
				+ "		, review_contents"
				+ "		, update_date"
				+ "		FROM review"
				+ "		WHERE reservation_no = ?";
				
		
		try {
			// 디버깅 
			System.out.println("[ReviewDao.selectReviewOnePerCustomer()] conn : " + conn);
			
			// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1, reservationNo);
			
			
			// 테이블에 쿼리 내용들을 저장한다 
			rs = stmt.executeQuery();
			
			while(rs.next()) {		
				
				reviewOne.setCleanliness(rs.getInt("cleanliness"));
				reviewOne.setRevisit(rs.getString("revisit"));
				reviewOne.setSatisfaction(rs.getInt("satisfaction"));
				reviewOne.setOpinion(rs.getString("opinion"));
				reviewOne.setReviewContents(rs.getString("review_contents"));
				reviewOne.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료 
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
		
		// 반환값 
		return reviewOne;
	}
	
	// 풀빌라 구매는 했지만 리뷰는 작성하지 않는 경우를 확인하는 메서드 
	public ArrayList<HashMap<String, Object>> selectReviewList(String customerId, int beginRow, int rowPerPage) {
		
		// 반환값으로 사용할 변수 선언 
		ArrayList<HashMap<String, Object>> reviewList = new ArrayList<>();
		
		// DB 자원 준비 
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT pv.pv_name pvName, loc.location_name locationName, rv.reservation_begin_date reservationBeginDate, rv.reservation_last_date reservationLastDate"
				+ " , pv.pv_no pvNo, rv.reservation_no reservationNo"
				+ " FROM reservation rv"
				+ " INNER JOIN poolvilla pv"
				+ " ON rv.pv_no = pv.pv_no"
				+ " INNER JOIN poolvilla_location loc"
				+ " ON pv.location_no = loc.location_no"
				+ " WHERE rv.customer_id = ? AND rv.reservation_status = '이용완료'"
				+ " AND rv.reservation_no NOT IN (SELECT reservation_no FROM review)"
				+ " LIMIT ?,?";
		
		try {
			// 디버깅 
			System.out.println("[ReviewDao.selectReviewList()] conn : " + conn);
			
			// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			
			// 테이블에 쿼리 내용들을 저장한다 
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 1회용 VO HashMap 인스턴스 생성 
				HashMap<String, Object> map = new HashMap<>();
				
				// map 인스턴스에 데이터 저장 
				map.put("pvName", rs.getString("pvName"));
				map.put("locationName", rs.getString("locationName"));
				map.put("reservationBeginDate", rs.getString("reservationBeginDate"));
				map.put("reservationLastDate", rs.getString("reservationLastDate"));
				// map.put("updateDate", rs.getString("updateDate"));
				// 리뷰 목록에서 풀빌라 상세 보기 페이지로 넘어가려면 pvNo가 필요해서 같이 가져옴 
				map.put("pvNo", rs.getInt("pvNo"));
				// 리뷰 작성이 없는 사용자가 리뷰를 작성하기 위해서는 reservationNo 필요 
				map.put("reservationNo", rs.getString("reservationNo"));
				// 동적 배열에 map 데이터 저장 
				reviewList.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료 
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
		
		// 반환값 
		return reviewList;
	}
	
	// 풀빌라 구매는 했지만 리뷰 작성한 경우를 확인하는 메서드 
		public ArrayList<HashMap<String, Object>> selectReviewListWroteReview(String customerId, int beginRow, int rowPerPage) {
			
			// 반환값으로 사용할 변수 선언 
			ArrayList<HashMap<String, Object>> reviewListWroteReview = new ArrayList<>();
			
			// DB 자원 준비 
			Connection conn = null;
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT pv.pv_name pvName, loc.location_name locationName, rv.reservation_begin_date reservationBeginDate, rv.reservation_last_date reservationLastDate"
					+ " , pv.pv_no pvNo, rv.reservation_no reservationNo"
					+ " FROM reservation rv"
					+ " INNER JOIN poolvilla pv"
					+ " ON rv.pv_no = pv.pv_no"
					+ " INNER JOIN poolvilla_location loc"
					+ " ON pv.location_no = loc.location_no"
					+ " WHERE rv.customer_id = ? AND rv.reservation_status = '이용완료'"
					+ " AND rv.reservation_no IN (SELECT reservation_no FROM review)";
			
			try {
				// 디버깅 
				System.out.println("[ReviewDao.selectReviewListWroteReview()] conn : " + conn);
				
				// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, customerId);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
				// 테이블에 쿼리 내용들을 저장한다 
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					// 1회용 VO HashMap 인스턴스 생성 
					HashMap<String, Object> map = new HashMap<>();
					
					// map 인스턴스에 데이터 저장 
					map.put("pvName", rs.getString("pvName"));
					map.put("locationName", rs.getString("locationName"));
					map.put("reservationBeginDate", rs.getString("reservationBeginDate"));
					map.put("reservationLastDate", rs.getString("reservationLastDate"));
					// map.put("updateDate", rs.getString("updateDate"));
					// 리뷰 목록에서 풀빌라 상세 보기 페이지로 넘어가려면 pvNo가 필요해서 같이 가져옴 
					map.put("pvNo", rs.getInt("pvNo"));
					// 리뷰 작성이 없는 사용자가 리뷰를 작성하기 위해서는 reservationNo 필요 
					map.put("reservationNo", rs.getString("reservationNo"));
					// 동적 배열에 map 데이터 저장 
					reviewListWroteReview.add(map);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// DB 연결 종료 
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
			
			// 반환값 
			return reviewListWroteReview;
		}
	
	// 리뷰 목록 전체 행의 수 구하는 메서드 
	public int selectReviewListTotalRow(String customerId) {
		int totalRow = 0; 
		
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) cnt"
				+ " FROM review r"
				+ " INNER JOIN reservation rv"
				+ " ON r.reservation_no = rv.reservation_no"
				+ " INNER JOIN customer c"
				+ " ON rv.customer_id = c.customer_id"
				+ " WHERE c.customer_id = ? AND rv.reservation_status = '이용완료'";
		
		try {
			System.out.println("[ReviewDao.selectReviewListTotalRow()] conn:" + conn);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println("[ReviewDao.selectReviewListTotalRow()] totalRow :" + totalRow);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
		
		return totalRow; 
	}
	
	
	
	public void insertReview(Review review) {
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 찜 목록 테이블 생성 쿼리 
		String sql = "INSERT INTO review"
				+ " (cleanliness, revisit, satisfaction, opinion, review_contents"
				+ " , review_active, create_date, update_date, reservation_no)"
				+ " VALUES (?, ?, ?, ?, ?,'Y', now(), now(), ?)";
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[ReviewDao.insertReview()] conn : " + conn);
			// 찜 목록 생성 쿼리를 저장 
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, review.getCleanliness());
			stmt.setString(2, review.getRevisit());
			stmt.setInt(3, review.getSatisfaction());
			stmt.setString(4, review.getOpinion());
			stmt.setString(5, review.getReviewContents());
			stmt.setInt(6, review.getReservationNo());
			
			// 찜 목록이 생성되면 1이라는 숫자값을 row에 저장 
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[ReviewDao.insertReview()] row : " + row);
			} else {
				System.out.println("[ReviewDao.insertReview()] row : 입력 실패");
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			try {
				// DB 연결을 종료 
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
				
				
	}
	
	// 리뷰 테이블(review)에 있던 리뷰를 삭제하는 메서드 
	public void deleteReview(int reservationNo) {
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		// 리뷰 테이블 삭제 쿼리 
		String sql = "DELETE FROM review WHERE reservation_no = ?";
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[ReviewDao.deleteReview()] conn : " + conn);
			
			// 찜 목록 삭제 쿼리를 저장 
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, reservationNo);
			System.out.println("[ReviewDao.deleteReview()] stmt : " + stmt);
			
			// 리뷰 삭제되면 1이라는 숫자값을 row에 저장 
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[ReviewDao.deleteReview()] row : " + row);
			} else {
				System.out.println("[ReviewDao.deleteReview()] row : 입력 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결을 종료 
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 관리자가 확인하는 고객 리뷰 목록 
	public ArrayList<HashMap<String, Object>> selectCustomerReviewList(int beginRow, int rowPerPage) {
			
	// 반환값으로 사용할 변수 선언 
	ArrayList<HashMap<String, Object>> customerReviewList = new ArrayList<>();
	
	// DB 자원 준비 
	Connection conn = null;
	conn = DBUtil.getConnection();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	String sql = "SELECT r.reservation_no reservationNo"
			+ "					, rv.customer_id customerId"
			+ "					, pv.pv_name pvName"
			+ "					, r.satisfaction satisfaction"
			+ "					, r.cleanliness cleanliness"
			+ "					, r.revisit revisit"
			+ " 				, r.opinion opinion"
			+ "					, r.review_contents reviewContents "
			+ "					, r.update_date updateDate"
			+ "					, r.review_active reviewActive"
			+ " 	FROM review r"
			+ " 	INNER JOIN reservation rv"
			+ " 	ON r.reservation_no = rv.reservation_no"
			+ " 	INNER JOIN poolvilla pv"
			+ " 	ON rv.pv_no = pv.pv_no"
			+ " 	LIMIT ?,?";
	
	try {
		// 디버깅 
		System.out.println("[ReviewDao.selectCustomerReviewList()] conn : " + conn);
		
		// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
		stmt= conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		
		// 테이블에 쿼리 내용들을 저장한다 
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			// 1회용 VO HashMap 인스턴스 생성 
			HashMap<String, Object> map = new HashMap<>();
			
			// map 인스턴스에 데이터 저장 
			map.put("reservationNo", rs.getInt("reservationNo"));
			map.put("pvName", rs.getString("pvName"));
			map.put("customerId", rs.getString("customerId"));
			map.put("satisfaction", rs.getInt("satisfaction"));
			map.put("cleanliness", rs.getInt("cleanliness"));
			map.put("revisit", rs.getString("revisit"));
			map.put("opinion", rs.getString("opinion"));
			map.put("reviewContents", rs.getString("reviewContents"));
			map.put("updateDate", rs.getString("updateDate"));
			map.put("reviewActive", rs.getString("reviewActive"));
			
			// 동적 배열에 map 데이터 저장 
			customerReviewList.add(map);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		try {
			// DB 연결 종료 
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	// 반환값 
	return customerReviewList;
}
		
		
		// 검색 기능 : 고객별, 풀빌라 이름별 모아보기 메서드 
		public ArrayList<HashMap<String, Object>> searchCustomerReviewList(String search, String keyword, int beginRow, int rowPerPage) {
			
			// 반환값으로 사용할 변수 선언 
			ArrayList<HashMap<String, Object>> customerReviewList = new ArrayList<>();
			
			// DB 자원 준비 
			Connection conn = null;
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT r.reservation_no reservationNo"
					+ "		, rv.customer_id customerId"
					+ "		, pv.pv_name pvName"
					+ "		, r.satisfaction satisfaction"
					+ "		, r.cleanliness cleanliness"
					+ "		, r.revisit revisit"
					+ "		, r.opinion opinion"
					+ "		, r.review_contents reviewContents "
					+ "		, r.update_date updateDate"
					+ "		, r.review_active reviewActive"
					+ "	FROM review r"
					+ "	INNER JOIN reservation rv"
					+ "	ON r.reservation_no = rv.reservation_no"
					+ "	INNER JOIN poolvilla pv"
					+ "	ON rv.pv_no = pv.pv_no"
					+ "	WHERE "+search+" "
					+ "	LIKE ?"
					+ "	ORDER BY r.update_date DESC "
					+ " LIMIT ?,?";
			
			try {
				// 디버깅 
				System.out.println("[ReviewDao.searchCustomerReviewList()] conn : " + conn);
				
				// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+keyword+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
				// 테이블에 쿼리 내용들을 저장한다 
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					// 1회용 VO HashMap 인스턴스 생성 
					HashMap<String, Object> map = new HashMap<>();
					
					// map 인스턴스에 데이터 저장 
					map.put("reservationNo", rs.getInt("reservationNo"));
					map.put("pvName", rs.getString("pvName"));
					map.put("customerId", rs.getString("customerId"));
					map.put("satisfaction", rs.getInt("satisfaction"));
					map.put("cleanliness", rs.getInt("cleanliness"));
					map.put("revisit", rs.getString("revisit"));
					map.put("opinion", rs.getString("opinion"));
					map.put("reviewContents", rs.getString("reviewContents"));
					map.put("updateDate", rs.getString("updateDate"));
					map.put("reviewActive", rs.getString("reviewActive"));
					System.out.println(map);
					// 동적 배열에 map 데이터 저장 
					customerReviewList.add(map);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// DB 연결 종료 
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
			
			// 반환값 
			return customerReviewList;
		}
		
		//  풀빌라 상세보기 페이지 리뷰 목록 전체 행의 수 구하는 메서드 
		public int selectReviewListPerPoolvillaTotalRow(int pvNo) {
			int totalRow = 0; 
			
			Connection conn = null;
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;

			String sql = "SELECT COUNT(*) cnt"
					+ "	  FROM review r"
					+ "	  INNER JOIN reservation rv"
					+ "   ON r.reservation_no = rv.reservation_no"
					+ "   INNER JOIN poolvilla pv"
					+ "   ON rv.pv_no = pv.pv_no"
					+ "   WHERE pv.pv_no = ?"
					+ "   AND r.review_active = 'Y'";
			
			try {
				System.out.println("[ReviewDao.selectReviewListPerPoolvillaTotalRow()] conn:" + conn);

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pvNo);

				rs = stmt.executeQuery();

				if (rs.next()) {
					totalRow = rs.getInt("cnt");
					System.out.println("[ReviewDao.selectReviewListPerPoolvillaTotalRow()] totalRow :" + totalRow);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
			
			return totalRow; 
		}
		
		// 풀빌라 상세보기에 리뷰 모아서 보는 기능 
				public ArrayList<HashMap<String, Object>> selectReviewListPerPoolvilla(int pvNo,int beginRow, int rowPerPage) {
					
					// 반환값으로 사용할 변수 선언 
					ArrayList<HashMap<String, Object>> poolvillaReviewList = new ArrayList<>();
					
					// DB 자원 준비 
					Connection conn = null;
					conn = DBUtil.getConnection();
					PreparedStatement stmt = null;
					ResultSet rs = null;
					
					String sql = "SELECT pv.pv_name pvName"
							+ "	, rv.customer_id customerId"
							+ "	, r.satisfaction satisfaction"
							+ "	, r.cleanliness cleanliness"
							+ " , r.revisit revisit"
							+ "	, r.review_contents reviewContents"
							+ "	, r.update_date updateDate"
							+ " , pv.pv_no pvNo"
							+ " , rv.reservation_no reservationNo"
							+ "   FROM review r"
							+ "   INNER JOIN reservation rv"
							+ "   ON r.reservation_no = rv.reservation_no"
							+ "   INNER JOIN poolvilla pv"
							+ "   ON rv.pv_no = pv.pv_no"
							+ "   WHERE pv.pv_no = ?"
							+ "   AND r.review_active = 'Y'"
							+ "   LIMIT ?,?";
					
					try {
						// 디버깅 
						System.out.println("[ReviewDao.selectReviewListPerPoolvilla()] conn : " + conn);
						
						// 리뷰 목록에 사용할 데이터를 가져오는 쿼리를 저장한다 
						stmt= conn.prepareStatement(sql);
						stmt.setInt(1, pvNo);
						stmt.setInt(2, beginRow);
						stmt.setInt(3, rowPerPage);
						
						// 테이블에 쿼리 내용들을 저장한다 
						rs = stmt.executeQuery();
						
						while(rs.next()) {
							// 1회용 VO HashMap 인스턴스 생성 
							HashMap<String, Object> map = new HashMap<>();
							
							// map 인스턴스에 데이터 저장 
							map.put("pvName", rs.getString("pvName"));
							map.put("customerId", rs.getString("customerId"));
							map.put("satisfaction", rs.getInt("satisfaction"));
							map.put("cleanliness", rs.getInt("cleanliness"));
							map.put("revisit", rs.getString("revisit"));
							map.put("reviewContents", rs.getString("reviewContents"));
							map.put("updateDate", rs.getString("updateDate"));
							
							map.put("pvNo", rs.getInt("pvNo"));
							map.put("reservationNo", rs.getInt("reservationNo"));
							// 동적 배열에 map 데이터 저장 
							poolvillaReviewList.add(map);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							// DB 연결 종료 
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					}
					
					// 반환값 
					return poolvillaReviewList;
				}
}
