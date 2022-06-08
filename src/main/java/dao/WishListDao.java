package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DBUtil;
import vo.WishList;

public class WishListDao {
	// 찜 목록 테이블(wish_list)에 상품 번호와 고객 아이디를 등록하는 모델 
	public void insertWishList(int pvNo, String customerId) {
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 찜 목록 테이블 생성 쿼리 
		String sql = "INSERT INTO wish_list VALUES (?,?,NOW())";
		
		try {
			conn = DBUtil.getConnection();
			System.out.println("[WishListDao.insertWishList()] conn : " + conn);
			// 찜 목록 생성 쿼리를 저장 
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pvNo);
			stmt.setString(2, customerId);
			
			// 찜 목록이 생성되면 1이라는 숫자값을 row에 저장 
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[WishListDao.insertWishList()] row : " + row);
			} else {
				System.out.println("[WishListDao.insertWishList()] row : 입력 실패");
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
	
	// 찜 목록 확인 모델 
	public ArrayList<HashMap<String,Object>> selectWishList(int beginRow, int rowPerPage, String customerId) {
		
		// 반환값으로 사용할 변수 선언 
		ArrayList<HashMap<String, Object>> wishListList = new ArrayList<>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT pv.pv_no pvNo, pv.location_no locationNo"
				+ ", pv.price price, pv.pv_size pvSize, pv.pv_people pvPeople"
				+ ", pv.pv_floor pvFloor, pv.pv_name pvName"
				+ ", COUNT(room.room_no) roomCnt, AVG(review.satisfaction) reviewSatisfaction"
				+ ", pv_photo.photo_name photoName"
				+ ", CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
				+ " FROM wish_list wish"
				+ " INNER JOIN poolvilla pv"
				+ " ON wish.pv_no = pv.pv_no"
				+ " INNER JOIN poolvilla_location loc"
				+ " ON pv.location_no = loc.location_no"
				+ " LEFT JOIN poolvilla_photo photo"
				+ " ON photo.pv_no = pv.pv_no"
				+ " LEFT JOIN poolvilla_room room"
				+ " ON pv.pv_no = room.pv_no"
				+ " LEFT JOIN reservation res"
				+ " ON res.pv_no = pv.pv_no"
				+ " LEFT JOIN review review"
				+ " ON res.reservation_no = review.reservation_no"
				+ " LEFT JOIN poolvilla_photo pv_photo"
				+ " ON photo.pv_no = pv.pv_no"
				+ " LEFT JOIN address addr"
				+ "	ON addr.address_no = pv.address_no"
				+ " WHERE wish.customer_id = ?"
				+ " GROUP BY pv.pv_no"
				+ " ORDER BY wish.update_date DESC"
				+ " LIMIT ?,?";
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[WishListDao.selectWishList()] conn : " + conn);
			
			// 찜 목록 테이블+x의 데이터를 가져오는 쿼리를 저장한다 
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
				map.put("pvNo", rs.getInt("pvNo"));
				map.put("locationNo", rs.getInt("locationNo"));
				map.put("price", rs.getInt("price"));
				map.put("pvSize", rs.getDouble("pvSize"));
				map.put("pvPeople", rs.getInt("pvPeople"));
				map.put("pvFloor", rs.getInt("pvFloor"));
				map.put("pvName", rs.getString("pvName"));
				map.put("roomCnt", rs.getInt("roomCnt"));
				map.put("reviewSatisfaction", rs.getInt("reviewSatisfaction"));
				map.put("photoName", rs.getString("photoName"));
				map.put("address", rs.getString("address"));
				map.put("photoName", rs.getString("photoName"));
				
				// 동적 배열에 map 데이터 저장 
				wishListList.add(map);
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
		return wishListList;
	}
	
	// WISH_LIST 테이블 전체 행 갯수 구하는 메서드
		public int selectWishListTotalRow(String customerId) {
			//결과 값 받을 변수 초기화
			int totalRow = 0;
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) cnt FROM wish_list wishList WHERE customer_id = ?";
			try {
				conn = DBUtil.getConnection();
				System.out.println("[WishListDao.selectWishListTotalRow()] conn:" + conn);

				stmt = conn.prepareStatement(sql);
				stmt.setString(1, customerId);

				rs = stmt.executeQuery();

				if (rs.next()) {
					totalRow = rs.getInt("cnt");
					System.out.println("[WishListDao.selectWishListTotalRow()] totalRow :" + totalRow);
				}
			} catch (Exception e) {
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
		
		// 찜 목록 테이블(wish_list)에 있던 상품을 삭제하는 메서드 
		public void deleteWishList(int pvNo, String customerId) {
			// DB 자원 준비 
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				//DB 연결
				conn = DBUtil.getConnection();
				System.out.println("[WishListDao.deleteWishList()] conn : " + conn);
				
				// 찜 목록 삭제 쿼리를 저장 
				String sql = "DELETE FROM wish_list WHERE customer_id = ? AND pv_no = ?";
				
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, customerId);
				stmt.setInt(2, pvNo);
				
				// 찜 목록이 삭제되면 1이라는 숫자값을 row에 저장 
				int row = stmt.executeUpdate();
				
				if(row == 1) {
					System.out.println("[WishListDao.deleteWishList()] row : " + row);
				} else {
					System.out.println("[WishListDao.deleteWishList()] row : 입력 실패");
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
}
