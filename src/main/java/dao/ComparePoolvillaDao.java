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

public class ComparePoolvillaDao {
	
	// orangepoolvilla db의 poolvilla_room 테이블 room_name 가져오기
	public List<Map<String,Object>> selectPoolvillaPvName(List<Integer> sessionComparePvNoList) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		// DB 자원준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT pv_no pvNo"
					+ "			, pv_name pvName"
					+ "		FROM poolvilla "
					+ "		WHERE pv_no = ? ";
			stmt = conn.prepareStatement(sql);
			
			//stmt.setInt(1, pvNo);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Map<String,Object> m = new HashMap<>();
				m.put("pvNo", rs.getInt("pvNo")); // 방 번호
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
}
