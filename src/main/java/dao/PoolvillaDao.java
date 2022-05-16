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

import vo.CookingTool;
import vo.Poolvilla;
import vo.PoolvillaPool;
public class PoolvillaDao {
	//지역과 날짜로만 검색 기능, homeController에서 호출
	public List<Map<String,Object>> selectPoolvillaListByDateLocation(String reservationBeginDate, String reservationLastDate,int locationNo,int beginRow,int rowPerPage){
		List<Map<String,Object>> list = new ArrayList<>();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT pv.pv_no pvNo"
					+ "					, pv.location_no locationNo"
					+ "					, loc.location_name locationName"
					+ "					, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "					, pv.price price, pv.pv_size pvSize"
					+ "					, pv.pv_people pvPeople, pv.pv_floor pvFloor"
					+ "					, pv.pv_name pvName,COUNT(room.room_no) roomCnt"
					+ "					, AVG(review.satisfaction) reviewSatisfaction "
					+ "		FROM poolvilla pv "
					+ "		INNER JOIN poolvilla_location loc "
					+ "		ON pv.location_no = loc.location_no "
					+ "		INNER JOIN address addr "
					+ "		ON addr.address_no = pv.address_no "
					+ "		LEFT JOIN poolvilla_photo photo "
					+ "		ON photo.pv_no = pv.pv_no "
					+ "		LEFT JOIN poolvilla_room room "
					+ "		ON pv.pv_no = room.pv_no "
					+ "		LEFT JOIN reservation res "
					+ "		ON res.pv_no = pv.pv_no "
					+ "		LEFT JOIN review review "
					+ "		ON res.reservation_no = review.reservation_no "
					+ "		WHERE pv.location_no = ?  "
					+ "		AND pv.pv_no NOT IN ( select res.reservation_no "
					+ "										from reservation res "
					+ "										WHERE (res.reservation_begin_date >= STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_begin_date < STR_TO_DATE(?,'%Y-%m-%d'))  "
					+ "													OR (res.reservation_last_date > STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_last_date < STR_TO_DATE(?,'%Y-%m-%d'))) "
					+ "		GROUP BY pv.pv_no "
					+ "		ORDER BY pv.update_date DESC"
					+ "		LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, locationNo);
			stmt.setString(2,reservationBeginDate);
			stmt.setString(3,reservationLastDate);
			stmt.setString(4,reservationBeginDate);
			stmt.setString(5,reservationLastDate);
			stmt.setInt(6, beginRow);
			stmt.setInt(7, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				m.put("locationNo", rs.getString("locationNo"));//풀빌라 검색 지역 정보
				m.put("locationName", rs.getString("locationName"));//풀빌라 검색 지역 이름
				m.put("address", rs.getString("address"));//풀빌라 기본주소
				m.put("pvSize", rs.getDouble("pvSize"));//풀빌라 면적
				m.put("pvPeople", rs.getInt("pvPeople"));//풀빌라 인원수
				m.put("price", rs.getInt("price"));//1박당가격
				m.put("pvFloor", rs.getString("pvFloor"));//풀빌라 층수
				m.put("pvName", rs.getString("pvName"));//풀빌라이름
				m.put("roomCnt", rs.getInt("roomCnt"));//방갯수
				m.put("reviewSatisfaction", rs.getInt("reviewSatisfaction"));//평균만족도
				list.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	};
	
	// 풀빌라 상세보기 기능
	public Poolvilla selectPoolvillaOne(int pvNo) {
		Poolvilla poolvilla = new Poolvilla();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			String sql = "SELECT pv.pv_no pvNo"
					+ "					, pv.host_id hostId"
					+ "					, loc.location_name locationName"
					+ "					, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "					, pv.pv_detailaddr pvDetailadder"
					+ "					, pv.pv_name pvName"
					+ "					, pv.price"
					+ "					, pv.pv_size pvSize"
					+ "					, pv.pv_floor pvFloor"
					+ "					, pv.pv_people pvPeople"
					+ "					, pv.create_date createDate"
					+ "					,pv.update_date updateDate "
					+ "		FROM poolvilla pv "
					+ "		INNER JOIN address addr "
					+ "		ON addr.address_no = pv.address_no "
					+ "		INNER JOIN poolvilla_location loc "
					+ "		ON loc.location_no = pv.location_no "
					+ "		WHERE pv.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				poolvilla.setPvNo(rs.getInt("pvNo"));
				poolvilla.setHostId(rs.getString("hostId"));
				poolvilla.setLocationName(rs.getString("locationName"));
				poolvilla.setAddress(rs.getString("address"));
				poolvilla.setPvDetailaddr(rs.getString("pvDetailadder"));
				poolvilla.setPvName(rs.getString("pvName"));
				poolvilla.setPrice(rs.getInt("price"));
				poolvilla.setPvSize(rs.getDouble("pvSize"));
				poolvilla.setPvFloor(rs.getInt("pvFloor"));
				poolvilla.setPvPeaple(rs.getInt("pvPeople"));
				poolvilla.setCreateDate(rs.getString("createDate"));
				poolvilla.setUpdateDate("updateDate");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return poolvilla;
	}
	
	// orangepoolvilla db의 해당 풀빌라의 cookingtool 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaCookingToolByPvNo(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[PoolvillaDao.selectPoolvillaCookingToolByPvNo()] 드라이버 로딩 성공");
			
			String sql = "SELECT pct.pv_no pvNo"
					+ "			, pct.cooking_tool_no cookingToolNo"
					+ "			, pct.cooking_tool_cnt cookingToolCnt"
					+ "			, pct.update_date updateDate"
					+ "			, ct.cooking_tool_name cookingToolName"
					+ "		FROM poolvilla_cooking_tool pct"
					+ "		INNER JOIN cooking_tool ct"
					+ "		ON pct.cooking_tool_no = ct.cooking_tool_no"
					+ "		WHERE pct.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				m.put("cookingToolNo", rs.getInt("cookingToolNo")); // 취사 시설 번호
				m.put("cookingToolCnt", rs.getInt("cookingToolCnt")); // 취사 시설 개수
				m.put("updateDate", rs.getString("updateDate")); // 취사 시설 글 수정 날짜
				m.put("cookingToolName", rs.getString("cookingToolName")); // 취사 시설 이름
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
	
	// orangepoolvilla db의 해당 풀빌라의 supplies 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaSuppliesByPvNo(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[PoolvillaDao.selectPoolvillaSuppliesByPvNo()] 드라이버 로딩 성공");
			
			String sql = "SELECT ps.pv_no pvNo"
					+ "			, ps.supplies_no suppliesNo"
					+ "			, ps.supplies_cnt suppliesCnt"
					+ "			, ps.update_date updateDate"
					+ "			, s.supplies_name suppliesName"
					+ "		FROM poolvilla_supplies ps"
					+ "		INNER JOIN supplies s"
					+ "		ON ps.supplies_no = s.supplies_no"
					+ "		WHERE ps.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				m.put("suppliesNo", rs.getInt("suppliesNo")); // 구비 물품 번호
				m.put("suppliesCnt", rs.getInt("suppliesCnt")); // 구비 물품 개수
				m.put("updateDate", rs.getString("updateDate")); // 구비 물품 글 수정 날짜
				m.put("suppliesName", rs.getString("suppliesName")); // 구비 물품 이름
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
	
	// orangepoolvilla db의 해당 풀빌라의 ott 테이블 목록 가져오기
	public List<Map<String,Object>> selectPoolvillaOttByPvNo(int pvNo) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[PoolvillaDao.selectPoolvillaOttByPvNo()] 드라이버 로딩 성공");
			
			String sql = "SELECT po.pv_no pvNo"
					+ "			, po.ott_no ottNo"
					+ "			, po.update_date updateDate"
					+ "			, o.ott_name ottName"
					+ "		FROM poolvilla_ott po"
					+ "		INNER JOIN ott o"
					+ "		ON po.ott_no = o.ott_no"
					+ "		WHERE po.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); //풀빌라 번호
				m.put("ottNo", rs.getInt("ottNo")); // ott 번호
				m.put("updateDate", rs.getString("updateDate")); // ott 글 수정 날짜
				m.put("ottName", rs.getString("ottName")); // ott 이름
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
					+ "			, rb.room_no roomNo"
					+ "			, rb.bed_size bedSize"
					+ "			, rb.bed_cnt bedCnt"
					+ "			, rb.update_date updateDateRB"
					+ "			, pr.pv_no pvNo"
					+ "			, pr.room_type roomType"
					+ "			, pr.room_name roomName"
					+ "			, pr.room_info roomInfo"
					+ "			, pr.room_size roomSize"
					+ "			, pr.update_date updateDatePR"
					+ "		FROM room_bed rb"
					+ "		INNER JOIN poolvilla_room pr"
					+ "		ON pr.room_no = rb.room_no"
					+ "		WHERE pr.pv_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("bedNo", rs.getInt("bedNo")); // 침대 번호
				m.put("roomNo", rs.getInt("roomNo")); // 방 번호
				m.put("bedSize", rs.getString("bedSize")); // 침대 사이즈
				m.put("bedCnt", rs.getInt("bedCnt")); // 침대 개수
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
}
