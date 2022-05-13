package dao;

import java.sql.Connection;
import vo.Poolvilla;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
					+ "					,pv.location_no locationNo"
					+ "					, pv.price price, pv.pv_size pvSize"
					+ "					, pv.pv_people pvPeople, pv.pv_floor pvFloor"
					+ "					, pv.pv_name pvName,COUNT(room.room_no) roomCnt"
					+ "					, AVG(review.satisfaction) reviewSatisfaction "
					+ "		FROM poolvilla pv "
					+ "		INNER JOIN poolvilla_location loc "
					+ "		ON pv.location_no = loc.location_no "
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
				m.put("locationNo", rs.getString("locationNo"));//풀빌라 지역 정보
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
	//플빌라 상세보기 기능
	public Poolvilla selectPoolvillaOne(int poolvillaNo) {
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
			stmt.setInt(1, poolvillaNo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				poolvilla.setPvNo(rs.getInt("poolvillaNo"));
				poolvilla.setHostId(rs.getString("poolvillaNo"));
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
	

}
