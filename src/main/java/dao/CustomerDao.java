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
import vo.Customer;


public class CustomerDao {
	//customer_id로 회원 한명 불러오는 기능
	
	
	//회원로그인 기능
	public Map<String,Object> loginCustomer(Customer customer) {
		Map<String,Object> sessionLoginMember = new HashMap<>();
		
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
	        	 sessionLoginMember.put("memberId", rs.getString("customerId"));
	        	 sessionLoginMember.put("level", rs.getInt("level"));
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
	      return sessionLoginMember;
	   }
	
	
	public void deleteCustomer(String customerId, String customerPw) {
		int row = -1;
		
		Connection conn = null;
		PreparedStatement stmt1 = null; // selectCustomersql 에 사용
		PreparedStatement stmt2 = null; // deleteCustomerSql 에 사용
		ResultSet rs = null;
		
		//select customer_id
		String selectCustomerIdSql = "SELECT customer_id, name, level"
				+ "					  FROM Customer WHERE Customer_id= ?";
		
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			conn.setAutoCommit(false); 
			//0. select customer_id
			stmt1 = conn.prepareStatement(selectCustomerIdSql);
			stmt1.setString(1, customerId);
			rs = stmt1.executeQuery();
			List<Integer> list = new ArrayList<>(); 
			while(rs.next()) {
				list.add(rs.getInt("CustomerId"));
				
			}
			String deleteCustomerSql = "UPDATE FROM Customer WHERE Customer_id=? "
					+ "					AND Customer_pw=PASSWORD(?)";
			
			stmt2 = conn.prepareStatement(deleteCustomerSql);
			stmt2.setString(1, customerId);
			stmt2.setString(2, customerPw);
			row = stmt2.executeUpdate();
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
				
				stmt1.close();
				stmt2.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public int insertCustomer(Customer customer) {
		int row = -1; 
		String CustomerId =null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql ="INSERT INTO Customer (Customer_id "
				+ "									,Customer_pw "
				+ "									,customer_pw_update_date"
				+ " 								,name "
				+ "									,gender "
				+ "									,Birth_date "
				+ "									,email"
				+ "									,phone"
				+ "									,level"
				+ "									,create_date "
				+ "									,update_date)"
				+ "									VALUES (?,PASSWORD(?),NOW(),?,?,?,?,?,3,NOW(),NOW()) ";
		
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
			row = stmt.executeUpdate();
			
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
	
	public int updatePassword(Customer customer,String newMemberPw) {
		int row = -1; // 쿼리문 실패시 -1 반환
		String CustomerId =null;// 변경된 Customer 저장할 변수 초기화
		String updateDate =null;// 변경된 updateDate 저장할 변후 초기화
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt0 = null;
		PreparedStatement stmt1 = null; // customer 에 사용
		PreparedStatement stmt2 = null; // select custmomer_password_update_date에 사용
		PreparedStatement stmt3 = null; // customer_password_update_date insert에 사용
		ResultSet rs0 = null; // select customer_password_update_date에 사용 
		ResultSet rs = null; // select customer_password_update_date에 사용 
		//1. Customer테이블의 상태를 변경
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			//오토커밋해제
			conn.setAutoCommit(false);
			// 0. 기존비밀번호와 중복여부 확인
			String checkPwSpl ="SELECT * FROM customer_pw_history "
					+ "WHERE customer_id = ? AND customer_pw =PASSWORD(?)";
			stmt0 = conn.prepareStatement(checkPwSpl);
			stmt1.setString(2, customer.getCustomerId());
			stmt0.setString(1, newMemberPw);
			rs0=stmt0.executeQuery();
			if(rs0.next()) {
				row = 2;
			}else {
				row=0;
			}
			if(row==2) {//중복 발생 실패 row=2
				System.out.println("[CustomerDao.updatePassword] 비밀번호 중복 발생");
			} else if(row==0) {//중복 없음 비밀번호 변경가능 다음 작업 진행
				// 1. customer테이블의 비밀번호 변경
				String sql ="UPDATE customer SET Customer_pw = PASSWORD(?)"
						+ "							,customer_pw_update_date = NOW()"
						+ "							,update_date = NOW()"
						+ "							WHERE Customer_id = ?"
						+ "							AND Customer_pw = PASSWORD(?)";
				stmt1 = conn.prepareStatement(sql);
				stmt1.setString(1, newMemberPw);
				stmt1.setString(2, customer.getCustomerId());
				stmt1.setString(3, customer.getCustomerPw());
				row =stmt1.executeUpdate();
				if(row==0) { // 수정실패
					System.out.println("[CustomerDao.updatePassword] Customer테이블 수정실패");
				}else if(row==1) { //수정성공 시에만 다음 진행
					System.out.println("[CustomerDao.updatePassword] Customer테이블 수정성공");
				
					//2. customer테이블의 update_date값을 select
					String selecPasswordUpdateDateSpl = "SELECT update_date updateDate"
							+ "				FROM customer "
							+ "				WHERE customer_id = ? ";
					stmt2 = conn.prepareStatement(selecPasswordUpdateDateSpl);
					stmt2.setString(1, customer.getCustomerId());
					rs = stmt2.executeQuery();
					if(rs.next()){ //변경된 customer테이블의 updateDate를 저장
						updateDate = rs.getString("updateDate");
					}
					
					//3. customer_pw_history에 insert
					String insertCustomerPwUpdateDateSql ="INSERT INTO customer_pw_history(customer_id"
							+ "								,customer_pw"
							+ "								,customer_pw_update_date)"
							+ "								VALUES (?,PASSWORD(?),?)";
					stmt3 = conn.prepareStatement(insertCustomerPwUpdateDateSql);
					stmt3.setString(1, customer.getCustomerId());
					stmt3.setString(2, newMemberPw);
					stmt3.setString(3, updateDate);
					row = stmt3.executeUpdate(); // insert의 결과 물을 row에 저장
					if(row==0) {//row가 0일시, insert실패 롤백
						System.out.println("[CustomerDao.updatePassword] customer_pw_history테이블 입력실패");
						conn.rollback();//롤백
					} else if(row==1) {//row가 1이면, insert 성공, 최종 커밋
						System.out.println("[CustomerDao.updatePassword] customer_pw_history테이블 입력성공");
						conn.commit(); //최종 커밋
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt1.close();
				
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	public int updateCustomer(Customer customer) {
		int row = -1;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql ="UPDATE Customer SET name = ?"
				+ "									,gender=? "
				+ "									,birth_date=? "
				+ "									,email=?"
				+ "									,phone=?"
				+ "									,update_date=NOW()"
				+ "									WHERE Customer_id = ? ";
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getGender());
			stmt.setString(3, customer.getBirthDate());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getPhone());
			stmt.setString(6, customer.getCustomerId());
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
//아이디 중복 체크 기능
public int checkIdInCustomer(String customerId) {
	int row = -1; //쿼리가 정상적으로 작동 되지 않으면 -1
	// 데이터베이스 자원 준비
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
		// 데이터베이스 드라이버 연결
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
		System.out.println("[CustomerDao.checkIdInCustomer()] 드라이버 로딩 성공");
		
		String sql = "SELECT * FROM customer WHERE customer_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			//rs.next()에 값이 있으면 row = 1
			row = 1;
			System.out.println("[CustomerDao.checkIdInCustomer()] 중복아이디가 존재합니다");
		}else {//없으면 row = 0
			row=0;
			System.out.println("[CustomerDao.checkIdInCustomer()] 가입가능한 아이디입니다");
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
	return row;
}
	// customerId로 회원 1명 정보 받아 오는 메세드
	public Customer selectCustomerOneByCustomerId(String customerId) {
		
		Customer customer = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT customer_id, customer_pw, customer_pw_update_date,`name`, gender, birth_date, email, phone, `level`, create_date, update_date"
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
				customer.setCustomerPwUpdateDate(rs.getString("customer_pw_update_date"));
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
	// Customer 테이블 전체 행 갯수 구하는 메서드 : 회원 전체 수
	public int selectCustomerTotalRow() {
		int totalRow = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "SELECT COUNT(*) cnt FROM customer";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("[HostDao.selectTotalRow()] : 드라이버 로딩 성공");

			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectTotalRow()] conn:" + conn);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRow = rs.getInt("cnt");
				System.out.println("[customerDao.selectTotalRow()] totalRow :" + totalRow);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
}