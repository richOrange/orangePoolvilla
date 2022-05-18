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

public class PoolvillaCookingToolDao {
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

}
