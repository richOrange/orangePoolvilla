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

public class PoolvillaAddressDao {
	
	//주소 추가 및 수정시,  주소 검색 기능 메서드
	public List<Map<String, Object>> searchAddress(String searchAddress) {
		//리턴 할 변수 초기화
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리작성
		String sql = "SELECT address_no addressNo, CONCAT(province, ' ', city, ' ', town, ' ', street, ' ', building1) address"
				+ "				 FROM address"
				+ "				 WHERE CONCAT(street, ' ', building1)"
				+ "				 LIKE ?";
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchAddress+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("addressNo", rs.getInt("addressNo")); // 주소 정보 번호
				m.put("addr", rs.getString("address")); //주소
				list.add(m);
			}
		} catch (SQLException e) {
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