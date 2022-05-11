package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vo.Customer;




public class CustomerDao {
	
	public int deleteCustomer(Customer Customer) {
		int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//쿼리 작성
		//select customer_id
		String selectCustomerNoSql = "SELECT customer_id CustomerNo FROM Customer WHERE Customer_id=?";
		
		//Customer 테이블 데이터 삭제
		String deleteCustomerSql = "DELETE FROM Customer WHERE Customer_id=? AND Customer_pw=PASSWORD(?)";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			conn.setAutoCommit(false); // 자동 커밋 해제
			//0. select customer_id
			stmt = conn.prepareStatement(selectCustomerNoSql);
			stmt.setString(1, Customer.getCustomerId());
			rs = stmt.executeQuery();
			List<Integer> list = new ArrayList<>(); //CustomerId 담을 리스트 
			while(rs.next()) {//CustomerId에 따른 모든 CustomerId 정보 저장
				list.add(rs.getInt("CustomerId"));
				
			}
			//stmt 해제 후 다음 쿼리 요청

			//3. Customer 테이블 데이터 삭제
			stmt = conn.prepareStatement(deleteCustomerSql);
			stmt.setString(1, Customer.getCustomerId());
			stmt.setString(2, Customer.getCustomerPw());
			row = stmt.executeUpdate();
			if (row == 1) { // 삭제 성공시 commit
				conn.commit();
			} else { // 성공이외의 결과시 rollback
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
				//DB자원 반납
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//회원정보
		public Customer selectCustomerOne(String CustomerId) {
			Customer m = new Customer();
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//쿼리 작성
			String sql ="SELECT Customer_id CustomerId"
					+ "					,name"
					+ "					,gender"
					+ "					,birthDate"
					+ "					,create_date createDate "
					+ "		 FROM Customer "
					+ "		WHERE Customer_id=? ";
			//DB에 값 요청
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, CustomerId);
				rs =stmt.executeQuery();
				if(rs.next()) {
					m.setCustomerId(rs.getString("CustomerID"));
					m.setName(rs.getString("name"));
					m.setGender(rs.getString("gender"));
					m.setBirthDate(rs.getString("birthDate"));
					m.setCreateDate(rs.getString("createDate"));
				}
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							//DB자원 반납
							rs.close();
							stmt.close();
							conn.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}		
			return m;
		}
	//Customer 테이블 입력
		public int insertCustomer(Customer customer) {
			int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
			String CustomerId =null; //로그인 실패시 null이 리턴
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			//쿼리 작성
			String sql ="INSERT INTO Customer (Customer_id "
					+ "									,Customer_pw "
					+ " 								,name "
					+ "									, gender "
					+ "									,age "
					+ "									,create_date) "
					+ "			VALUES (?,PASSWORD(?),?,?,?,NOW()) ";
			//DB에 값 요청
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, customer.getCustomerId());
				stmt.setString(2, customer.getCustomerPw());
				stmt.setString(3, customer.getName());
				stmt.setString(4, customer.getGender());
				stmt.setString(5, customer.getBirthDate());
				row =stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					//DB자원 반납
					stmt.close();
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		
		//Customer 테이블 수정
		public int updateCustomerByIdPw(Customer customer,String newCustomerPw) {
			int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
			String CustomerId =null; //로그인 실패시 null이 리턴
			if(newCustomerPw.equals("")) {//새로운 비밀번호 현수가 "" 이라면 현재비밀번호로 채움
				newCustomerPw=customer.getCustomerPw();
			}
			//DB 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			//쿼리 작성
			String sql ="UPDATE Customer SET name = ? "
					+ "									, gender=? "
					+ "									,age=? "
					+ "									,Customer_pw = PASSWORD(?) "
					+ "			WHERE Customer_id = ? "
					+ "			AND Customer_pw =PASSWORD(?)";
			//DB에 값 요청
			try {
				Class.forName("org.mariadb.jdbc.Driver");
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
					//DB자원 반납
					stmt.close();
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
}