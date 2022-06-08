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
import vo.CookingTool;
import vo.Poolvilla;
import vo.PoolvillaPool;
public class PoolvillaDao {
	//예약가능한 상품만 검색, 지역,날짜 검색 필수 homeController에서 호출
	public List<Map<String,Object>> selectPoolvillaListByDateLocation(String reservationBeginDate, String reservationLastDate,int locationNo,String orderValue,int beginRow,int rowPerPage,List<String> checkedFacilityList){
		List<Map<String,Object>> list = new ArrayList<>();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			// select 컬럼, 테이블, 조인 쿼리 입력
			String sql = "SELECT pv.pv_no pvNo"
					+ "					, pv.location_no locationNo"
					+ "					, loc.location_name locationName"
					+ "					, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "					, pv.price price"
					+ "					, pv.pv_size pvSize"
					+ "					, pv.pv_people pvPeople"
					+ "					, pv.pv_floor pvFloor"
					+ "					, pv.pv_name pvName"
					+ "					,COUNT(room.room_no) roomCnt"
					+ "					, AVG(review.satisfaction) reviewSatisfaction "
					+ "					, photo.photo_name photoName"
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
					+ "		ON res.reservation_no = review.reservation_no ";
			//WHERE 쿼리 입력
			List<Object> setObject = new ArrayList<>(); // ? 값 넣을 ArrayList<String>
			//1. location 검색 
			if(locationNo != -1) {
			sql= sql + " WHERE pv.location_no = ? ";
			setObject.add(locationNo); //?에 들어갈 값 , locationNo
			}
			
			//2. 설정한 체크인,체크아웃 기간내에 예약가능한지 확인 <- 기간내에 검색되는 예약이 있는지 없는지
			if(reservationBeginDate != null && reservationLastDate != null) {
				
			sql = sql + "		AND pv.pv_no NOT IN ( select res.reservation_no "
					+ "										from reservation res "
					+ "										WHERE ((res.reservation_begin_date >= STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_begin_date < STR_TO_DATE(?,'%Y-%m-%d'))  "
					+ "													OR (res.reservation_last_date > STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_last_date < STR_TO_DATE(?,'%Y-%m-%d')))) ";
			setObject.add(reservationBeginDate); // ?에 들어갈 값 , 체크인 날짜
			setObject.add(reservationLastDate); // ?에 들어갈 값 ,체크아웃 날짜
			setObject.add(reservationBeginDate); // ?에 들어갈 값,체크인 날짜
			setObject.add(reservationLastDate); // ?에 들어갈 값,체크아웃 날짜
			}
			
			//3.부대시설 검색 조건에 넣은 부대시설 모두가 있어야만 출력하는 쿼리 입력
			if(checkedFacilityList.size()!=0){
				for (String s : checkedFacilityList) {
					sql = sql + "AND pv.pv_no IN (SELECT pv_no FROM poolvilla_facility WHERE facility_no ="+s+") ";
				}
			}
			//group by 추가
			sql = sql + "		GROUP BY pv.pv_no ";
			//order by 추가
			sql = sql + "		ORDER BY  ? ";
			setObject.add(orderValue); // ?에 들어갈 값, 정렬할 컬럼 + 내림차순,오름차순
			//LIMIT 추가
			sql = sql+ "		LIMIT ?, ?";
			setObject.add(beginRow); // ?에 들어갈 값, 시작 행수
			setObject.add(rowPerPage); // ?에 들어갈 값, 페이지당 출력할 행수
			
			//최종쿼리 디버깅, 너무 긴 빈칸은 제거
			System.out.println("[PoolvillaDao.selectPoolvillaListByDateLocation] 쿼리 확인 :"+sql.replace("				", ""));
			stmt = conn.prepareStatement(sql);
			for(int i =0;i<setObject.size();i=i+1) {// stmt에 ? 값 셋팅
				stmt.setObject(i+1, setObject.get(i));
			}
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
				m.put("photoName",rs.getString("photoName"));//등록된 사진이름
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
	//위의 검색쿼리에서 totalRow을 구하기 위한 메서드
	public int selectPoolvillaTotalRowByDateLocation(String reservationBeginDate, String reservationLastDate,int locationNo,String orderValue,int beginRow,int rowPerPage,List<String> checkedFacilityList){
		int totalRow = 0;
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			// select 컬럼, 테이블, 조인 쿼리 입력
			String sql = "SELECT pv.pv_no pvNo"
					+ "					, pv.location_no locationNo"
					+ "					, loc.location_name locationName"
					+ "					, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "					, pv.price price"
					+ "					, pv.pv_size pvSize"
					+ "					, pv.pv_people pvPeople"
					+ "					, pv.pv_floor pvFloor"
					+ "					, pv.pv_name pvName"
					+ "					,COUNT(room.room_no) roomCnt"
					+ "					, AVG(review.satisfaction) reviewSatisfaction "
					+ "					, photo.photo_name photoName"
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
					+ "		ON res.reservation_no = review.reservation_no ";
			//WHERE 쿼리 입력
			List<Object> setObject = new ArrayList<>(); // ? 값 넣을 ArrayList<String>
			//1. location 검색 
			if(locationNo != -1) {
			sql= sql + " WHERE pv.location_no = ? ";
			setObject.add(locationNo); //?에 들어갈 값 , locationNo
			}
			
			//2. 설정한 체크인,체크아웃 기간내에 예약가능한지 확인 <- 기간내에 검색되는 예약이 있는지 없는지
			if(reservationBeginDate != null && reservationLastDate != null) {
				
			sql = sql + "		AND pv.pv_no NOT IN ( select res.reservation_no "
					+ "										from reservation res "
					+ "										WHERE ((res.reservation_begin_date >= STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_begin_date < STR_TO_DATE(?,'%Y-%m-%d'))  "
					+ "													OR (res.reservation_last_date > STR_TO_DATE(?,'%Y-%m-%d') AND res.reservation_last_date < STR_TO_DATE(?,'%Y-%m-%d')))) ";
			setObject.add(reservationBeginDate); // ?에 들어갈 값 , 체크인 날짜
			setObject.add(reservationLastDate); // ?에 들어갈 값 ,체크아웃 날짜
			setObject.add(reservationBeginDate); // ?에 들어갈 값,체크인 날짜
			setObject.add(reservationLastDate); // ?에 들어갈 값,체크아웃 날짜
			}
			
			//3.부대시설 검색 조건에 넣은 부대시설 모두가 있어야만 출력하는 쿼리 입력
			if(checkedFacilityList.size()!=0){
				for (String s : checkedFacilityList) {
					sql = sql + "AND pv.pv_no IN (SELECT pv_no FROM poolvilla_facility WHERE facility_no ="+s+") ";
				}
			}
			//group by 추가
			sql = sql + "		GROUP BY pv.pv_no ";
			//order by 추가
			sql = sql + "		ORDER BY  ? ";
			setObject.add(orderValue); // ?에 들어갈 값, 정렬할 컬럼 + 내림차순,오름차순
			
			//최종쿼리 디버깅 너무 긴 빈칸은 제거
			System.out.println("[PoolvillaDao.selectPoolvillaListByDateLocation] 쿼리 :"+sql.replace("				",""));
			stmt = conn.prepareStatement(sql);
			for(int i =0;i<setObject.size();i=i+1) {// stmt에 ? 값 셋팅
				stmt.setObject(i+1, setObject.get(i));
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				totalRow = rs.getInt("cnt");
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
		return totalRow;
	};
	//전체 상품 수 구하는 메서드
	public int selectPoolvillatotalRow(int beginRow,int rowPerPage) {
		//리턴 변수 초기화
		int totalRow = 0;
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리 작성
		String sql = "SELECT COUNT(*) cnt FROM poolvilla"; 
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
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
		return totalRow;
	}
	
	
	// 풀빌라 상세보기 기능
	public Poolvilla selectPoolvillaOne(int pvNo) {
		Poolvilla poolvilla = new Poolvilla();
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			String sql = "SELECT pv.pv_no pvNo"
					+ "					, pv.host_id hostId"
					+ "					, loc.location_name locationName"
					+ "					, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "					, pv.pv_detailaddr pvDetailaddr"
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
				poolvilla.setPvDetailaddr(rs.getString("pvDetailaddr"));
				poolvilla.setPvName(rs.getString("pvName"));
				poolvilla.setPrice(rs.getInt("price"));
				poolvilla.setPvSize(rs.getDouble("pvSize"));
				poolvilla.setPvFloor(rs.getInt("pvFloor"));
				poolvilla.setPvPeople(rs.getInt("pvPeople"));
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
	

	
	// orangepoolvilla db의 poolvilla 테이블 데이터 입력
	public int insertPoolvilla(Poolvilla p) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//결과행 받을 변수 초기화
		int row = 0;
		//풀빌라 번호 받을 변수 초기화, 등록 후 등록한 상품 페이지로 이동에 필요
		int pvNo =0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaDao.insertPoolvilla()] 드라이버 로딩 성공");
			
			String sql = "INSERT INTO poolvilla( host_id"
					+ "							, location_no"
					+ "							, address_no"
					+ "							, pv_detailaddr"
					+ "							, pv_name"
					+ "							, price"
					+ "							, pv_size"
					+ "							, pv_floor"
					+ "							, pv_people"
					+ "							, create_date"
					+ "							, update_date)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());";
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, p.getHostId()); // 상품 등록한 관리자 아이디
			stmt.setInt(2, p.getLocationNo()); // 상품 대분류 지역 정보 번호
			stmt.setInt(3, p.getAddressNo()); // 주소 지역 정보 번호
			stmt.setString(4, p.getPvDetailaddr()); //주소 상세정보
			stmt.setString(5, p.getPvName()); //풀빌라 이름
			stmt.setInt(6, p.getPrice()); // 1박당가격
			stmt.setDouble(7, p.getPvSize()); // 풀빌라 크기
			stmt.setInt(8, p.getPvFloor()); //풀빌라 층수
			stmt.setInt(9, p.getPvPeople()); //최대 가능 인원
			row = stmt.executeUpdate(); //결과 행의 수 출력
			
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				pvNo = rs.getInt(1); //풀빌라 번호 저장
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[PoolvillaDao.insertPoolvilla()] insertPoolvilla 입력 성공");
				} else {
					System.out.println("[PoolvillaDao.insertPoolvilla()] insertPoolvilla 입력 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pvNo;
	}
	
	// orangepoolvilla db의 poolvilla 테이블 데이터 수정
	public void updatePoolvilla(Poolvilla p, int pvNo) {
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//결과 행 받을 변수 초기화
		int row = 0;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaDao.updatePoolvilla()] 드라이버 로딩 성공");
			
			String sql = "UPDATE poolvilla SET host_id = ?"
					+ "							, location_no = ?"
					+ "							, address_no = ?"
					+ "							, pv_detailaddr = ?"
					+ "							, pv_name = ?"
					+ "							, price = ?"
					+ "							, pv_size = ?"
					+ "							, pv_floor = ?"
					+ "							, pv_people = ?"
					+ "							, update_date = NOW()"
					+ "	  WHERE pv_no = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, p.getHostId());
			stmt.setInt(2, p.getLocationNo());
			stmt.setInt(3, p.getAddressNo());
			stmt.setString(4, p.getPvDetailaddr());
			stmt.setString(5, p.getPvName());
			stmt.setInt(6, p.getPrice());
			stmt.setDouble(7, p.getPvSize());
			stmt.setInt(8, p.getPvFloor());
			stmt.setInt(9, p.getPvPeople());
			stmt.setInt(10, pvNo);
			
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				conn.close();
				
				// -디버깅 코드
				if(row == 1) {
					System.out.println("[PoolvillaDao.updatetPoolvilla()] updatePoolvilla 수정 성공");
				} else {
					System.out.println("[PoolvillaDao.updatePoolvilla()] updatePoolvilla 수정 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

		
	// 관리자 풀빌라 상세보기 기능
	public List<Map<String,Object>> selectPoolvillaList(int beginRow, int rowPerPage) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			//쿼리 작성
			String sql = "SELECT p.pv_no pvNo"
					+ "			, p.location_no locationNo"
					+ "			, p.address_no"
					+ "			, p.pv_detailaddr"
					+ "			, p.pv_name pvName"
					+ "			, p.price price"
					+ "			, p.pv_size pvSize"
					+ "			, p.pv_floor pvFloor"
					+ "			, p.pv_people pvPeople"
					+ "			, p.create_date"
					+ "			, p.update_date"
					+ "			, loc.location_name locationName"
					+ "			, CONCAT(addr.province,' ', addr.city,' ',addr.town,' ',addr.street,' ',addr.building1) address"
					+ "			, COUNT(room.room_no) roomCnt"
					+ "			, AVG(review.satisfaction) reviewSatisfaction"
					+ "			, photo.photo_name photoName "
					+ " FROM poolvilla p "
					+ " INNER JOIN poolvilla_location loc "
					+ " ON p.location_no = loc.location_no "
					+ " INNER JOIN address addr "
					+ " ON addr.address_no = p.address_no "
					+ " LEFT JOIN poolvilla_photo photo "
					+ " ON photo.pv_no = p.pv_no "
					+ " LEFT JOIN poolvilla_room room "
					+ " ON p.pv_no = room.pv_no "
					+ " LEFT JOIN reservation res "
					+ " ON res.pv_no = p.pv_no "
					+ " LEFT JOIN review review "
					+ " ON res.reservation_no = review.reservation_no "
					+ " GROUP BY p.pv_no "
					+ "	ORDER BY p.update_date DESC"
					+ " LIMIT ?, ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); // 풀빌라 번호
				m.put("locationNo", rs.getString("locationNo")); // 풀빌라 검색 지역 정보
				m.put("locationName", rs.getString("locationName")); // 풀빌라 검색 지역 이름
				m.put("address", rs.getString("address")); // 풀빌라 기본주소
				m.put("pvSize", rs.getDouble("pvSize")); // 풀빌라 면적
				m.put("pvPeople", rs.getInt("pvPeople")); // 풀빌라 인원수
				m.put("price", rs.getInt("price")); // 1박당 가격
				m.put("pvFloor", rs.getString("pvFloor")); // 풀빌라 층수
				m.put("pvName", rs.getString("pvName")); // 풀빌라 이름
				m.put("roomCnt", rs.getInt("roomCnt")); // 방 개수
				m.put("reviewSatisfaction", rs.getInt("reviewSatisfaction")); // 평균 만족도
				m.put("photoName", rs.getString("photoName"));//사진 정보
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
	}
}
