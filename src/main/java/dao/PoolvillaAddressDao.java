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

public class PoolvillaAddressDao {
	
	
	public List<Map<String, Object>> searchAddress(String searchAddress) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT address_no addressNo, CONCAT(province, ' ', city, ' ', town, ' ', street, ' ', building1) addr"
				+ "				 FROM address"
				+ "				 WHERE CONCAT(street, ' ', building1)"
				+ "				 LIKE ?";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchAddress+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("addressNo", rs.getInt("addressNo"));
				m.put("addr", rs.getString("addr"));
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


