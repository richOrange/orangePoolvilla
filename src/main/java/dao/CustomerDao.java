package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CookingTool;
import vo.Customer;


public class CustomerDao {
	
	public ArrayList<Customer> myPageCustomer() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터베이스 드라이버 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
			System.out.println("[CustomerDao.myPageCustomer()] 드라이버 로딩 성공");
			
			String sql = "SELECT customer_id customerId"
					+ ", 			name"
					+ ", 			gender"
					+ ", 			birth_date birthDate"
					+ ", 			email"
					+ ", 			phone"
					+ ", 			create_date createDate"
					+ ", 			update_date updateDate FROM customer";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Customer co = new Customer();
				co.setCustomerId(rs.getString("CustomerId"));
				co.setName(rs.getString("Name"));
				co.setGender(rs.getString("Gender"));
				co.setBirthDate(rs.getString("BirthDate"));
				co.setEmail(rs.getString("Email"));
				co.setPhone(rs.getString("Phone"));
				co.setCreateDate(rs.getString("CreateDate"));
				co.setUpdateDate(rs.getString("updateDate"));
				list.add(co);
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
	
	
	public Customer loginCustomer(Customer customer) {
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      String sql = "SELECT customer_id customerId, level FROM customer WHERE customer_id=? AND customer_pw=PASSWORD(?)";
	      try {
	         
	         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, customer.getCustomerId());
	         stmt.setString(2, customer.getCustomerPw());
	         rs = stmt.executeQuery();
	         if(rs.next()) {
	            customer.setCustomerId( rs.getString("customerId"));
	            customer.setLevel(rs.getInt("level"));
	            
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
	      return customer;
	   }
	
	
	public void deleteCustomer(String customerId, String customerPw) {
		int row = -1;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//select customer_id
		String selectCustomerNoSql = "SELECT customer_id CustomerNo FROM Customer WHERE Customer_id=?";
		
		String deleteCustomerSql = "DELETE FROM Customer WHERE Customer_id=? AND Customer_pw=PASSWORD(?)";
		try {
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			conn.setAutoCommit(false); 
			//0. select customer_id
			stmt = conn.prepareStatement(selectCustomerNoSql);
			stmt.setString(1, customerId);
			rs = stmt.executeQuery();
			List<Integer> list = new ArrayList<>(); 
			while(rs.next()) {
				list.add(rs.getInt("CustomerId"));
				
			}
			

			
			stmt = conn.prepareStatement(deleteCustomerSql);
			stmt.setString(1, customerId);
			stmt.setString(2, customerPw);
			row = stmt.executeUpdate();
			if (row == 1) {
				conn.commit();
			} else { 
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
		public Customer selectMyPage(String CustomerId) {
			Customer m = new Customer();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql ="SELECT Customer_id CustomerId"
					+ "					,name"
					+ "					,gender"
					+ "					,birth_date"
					+ "					,email"
					+ "					,phone"
					+ "					,create_date createDate "
					+ "					,update_date updateDate"
					+ "					FROM Customer "
					+ "					WHERE Customer_id=? ";
			
			try {
				
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, CustomerId);
				rs =stmt.executeQuery();
				if(rs.next()) {
					m.setCustomerId(rs.getString("CustomerID"));
					m.setName(rs.getString("name"));
					m.setGender(rs.getString("gender"));
					m.setBirthDate(rs.getString("birthDate"));
					m.setEmail(rs.getString("email"));
					m.setPhone(rs.getString("phone"));
					m.setUpdateDate(rs.getString("updatDate"));
					m.setCreateDate(rs.getString("createDate"));
				}
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							
							rs.close();
							stmt.close();
							conn.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}		
			return m;
		}
	
		public int insertCustomer(Customer customer) {
			int row = -1; 
			String CustomerId =null;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			String sql ="INSERT INTO Customer (Customer_id "
					+ "									,Customer_pw "
					+ " 								,name "
					+ "									,gender "
					+ "									,Birth_date "
					+ "									,email"
					+ "									,phone"
					+ "									,level"
					+ "									,create_date "
					+ "									,update_date)"
					+ "									VALUES (?,PASSWORD(?),?,?,?,?,?,3,NOW(),NOW()) ";
			
			try {
				
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, customer.getCustomerId());
				stmt.setString(2, customer.getCustomerPw());
				stmt.setString(3, customer.getName());
				stmt.setString(4, customer.getGender());
				stmt.setString(5, customer.getBirthDate());
				stmt.setString(6, customer.getEmail());
				stmt.setString(7, customer.getPhone());
				row =stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					stmt.close();
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		
		
		public int updateCustomer(Customer customer,String newCustomerPw) {
			int row = -1; 
			String CustomerId =null; //
			if(newCustomerPw.equals("")) {
				newCustomerPw=customer.getCustomerPw();
			}
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			String sql ="UPDATE Customer SET name = ? "
					+ "									, gender=? "
					+ "									,Birth_date=? "
					+ "									,email=?"
					+ "									,phone=?"
					+ "									,update_date=NOW(?)"
					+ "									,Customer_pw = PASSWORD(?) "
					+ "									WHERE Customer_id = ? "
					+ "									AND Customer_pw =PASSWORD(?)";
			
			try {
				
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, customer.getName());
				stmt.setString(2, customer.getGender());
				stmt.setString(3, customer.getBirthDate());
				stmt.setString(4, newCustomerPw);
				stmt.setString(5, customer.getCustomerId());
				stmt.setString(6, customer.getCustomerPw());
				row =stmt.executeUpdate();
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					stmt.close();
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
}