package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;

public class HostCustomerDao {
	// 회원 상세보기 모델 
		public Customer selectCustomerDetail(String customerId) {
			
			Customer customer = null; 
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null; 
			
			String sql = "SELECT customer_id, customer_pw, `name`, gender, birth_date, email, phone, `level`, create_date, update_date"
					+ " FROM customer"
					+ " WHERE customer_id = ?"; 
			
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, customerId);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					customer = new Customer();
					customer.setCustomerId(rs.getString("customer_id"));
					customer.setCustomerPw(rs.getString("customer_pw"));
					customer.setName(rs.getString("name"));
					customer.setGender(rs.getString("gender"));
					customer.setBirthDate(rs.getString("birth_date"));
					customer.setEmail(rs.getString("email"));
					customer.setPhone(rs.getString("phone"));
					customer.setLevel(rs.getInt("level"));
					customer.setCreateDate(rs.getString("create_date"));
					customer.setUpdateDate(rs.getString("update_date"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			return customer;
		}
		
		// 회원 리스트보기 모델 
			public ArrayList<HashMap<String, Object>> selectCustomerList() {
				
				ArrayList<HashMap<String, Object>> customerList = new ArrayList<>();
				
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null; 
				
				String sql = "SELECT customer_id, `name`"
						+ " FROM customer"
						+ " ORDER BY update_date DESC"; 
				
				try {
					conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				
					stmt = conn.prepareStatement(sql);
					
					rs = stmt.executeQuery();
					
					while(rs.next()) {
						HashMap<String, Object> map = new HashMap<>();
						map.put("customerId", rs.getString("customer_id"));
						map.put("name", rs.getString("name"));
						customerList.add(map);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				return customerList;
			}

}
