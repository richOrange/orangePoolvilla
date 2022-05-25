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
import vo.Customer;


public class CustomerDao {
	//customer_id로 회원 한명 불러오는 기능
	
	
	//회원로그인 기능
	public Map<String,Object> loginCustomer(Customer customer) {
		Map<String,Object> sessionLoginMember = new HashMap<>();
		
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT customer_id customerId, level FROM customer WHERE customer_id=? AND customer_pw=PASSWORD(?)";
		try {
         
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt1 = null; // selectCustomersql 에 사용
		PreparedStatement stmt2 = null; // deleteCustomerSql 에 사용
		ResultSet rs = null;
		
		//select customer_id
		String selectCustomerIdSql = "SELECT customer_id, name, level"
				+ "					  FROM customer WHERE customer_id= ?";
		
		
		try {
			
			conn.setAutoCommit(false); 
			//0. select customer_id
			stmt1 = conn.prepareStatement(selectCustomerIdSql);
			stmt1.setString(1, customerId);
			rs = stmt1.executeQuery();
			List<Integer> list = new ArrayList<>(); 
			while(rs.next()) {
				list.add(rs.getInt("customerId"));
				
			}
			String deleteCustomerSql = "UPDATE FROM customer WHERE customer_id=? "
					+ "					AND customer_pw=PASSWORD(?)";
			
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
		String customerId = null;
		String customerPw = null;
		
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		String sql ="INSERT INTO customer (customer_id "
				+ "									,customer_pw "
				+ "									,customer_pw_update_date"
				+ " 								,name "
				+ "									,gender "
				+ "									,birth_date "
				+ "									,email"
				+ "									,phone"
				+ "									,level"
				+ "									,create_date "
				+ "									,update_date)"
				+ "									VALUES (?,PASSWORD(?),NOW(),?,?,?,?,?,3,NOW(),NOW()) ";
		
		String sql2 = "INSERT INTO customer_pw_history(customer_id, customer_pw, customer_pw_update_date) VALUES (?, PASSWORD(?), NOW())";
		
		try {
			
			conn.setAutoCommit(false); // 자동 커밋을 해제
			
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, customer.getCustomerId());
			stmt.setString(2, customer.getCustomerPw());
			stmt.setString(3, customer.getName());
			stmt.setString(4, customer.getGender());
			stmt.setString(5, customer.getBirthDate());
			stmt.setString(6, customer.getEmail());
			stmt.setString(7, customer.getPhone());
			row = stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, customer.getCustomerId());
			stmt2.setString(2, customer.getCustomerPw());
			stmt2.executeUpdate();
			
			conn.commit();	
		} catch (Exception e) {
			try { 
				conn.rollback();
			} catch (SQLException e1) {
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
		return row;
	}
	
	public int updatePassword(Customer customer,String newMemberPw) {
		int row = -1; // 쿼리문 실패시 -1 반환
		String customerId = null;// 변경된 Customer 저장할 변수 초기화
		String updateDate = null;// 변경된 updateDate 저장할 변후 초기화
		//DB 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt0 = null; // checkPwSpl에 사용
		PreparedStatement stmt1 = null; // customer 에 사용
		PreparedStatement stmt2 = null; // select custmomer_password_update_date에 사용
		PreparedStatement stmt3 = null; // customer_password_update_date insert에 사용
		ResultSet rs0 = null; // select customer_password_update_date에 사용 
		ResultSet rs1 = null; // select customer_password_update_date에 사용 
		//1. Customer테이블의 상태를 변경
		
		try {
			
			//오토커밋해제
			conn.setAutoCommit(false);
			// 0. 기존비밀번호와 중복여부 확인
			String checkPwSpl ="SELECT * FROM customer_pw_history "
					+ "		WHERE customer_id = ? AND customer_pw =PASSWORD(?)";
			stmt0 = conn.prepareStatement(checkPwSpl);
			stmt0.setString(1, customer.getCustomerId());
			stmt0.setString(2, newMemberPw);
			rs0=stmt0.executeQuery();
			if(rs0.next()) {
				row = 2;
			}else {
				row = 0;
			}
			if(row==2) {//중복 발생 실패 row=2
				System.out.println("[CustomerDao.updatePassword] 비밀번호 중복 발생");
				
			} else if(row==0) {//중복 없음 비밀번호 변경가능 다음 작업 진행
				// 1. customer테이블의 비밀번호 변경
				String sql ="UPDATE customer SET customer_pw = PASSWORD(?)"
						+ "							,customer_pw_update_date = NOW()"
						+ "							,update_date = NOW()"
						+ "							WHERE customer_id = ?"
						+ "							AND customer_pw = PASSWORD(?)";
				stmt1 = conn.prepareStatement(sql);
				stmt1.setString(1, newMemberPw);
				stmt1.setString(2, customer.getCustomerId());
				stmt1.setString(3, customer.getCustomerPw());
				row =stmt1.executeUpdate();
				if(row==0) { // 수정실패
					System.out.println("[CustomerDao.updatePassword] customer테이블 수정실패");
					conn.rollback();//롤백
				}else if(row==1) { //수정성공 시에만 다음 진행
					System.out.println("[CustomerDao.updatePassword] customer테이블 수정성공");
				
					//2. customer테이블의 update_date값을 select
					String selecPasswordUpdateDateSpl = "SELECT update_date updateDate"
							+ "				FROM customer "
							+ "				WHERE customer_id = ? ";
					stmt2 = conn.prepareStatement(selecPasswordUpdateDateSpl);
					stmt2.setString(1, customer.getCustomerId());
					rs1 = stmt2.executeQuery();
					if(rs1.next()){ //변경된 customer테이블의 updateDate를 저장
						updateDate = rs1.getString("updateDate");
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		String sql ="UPDATE customer SET name = ?"
				+ "									,gender=? "
				+ "									,birth_date=? "
				+ "									,email=?"
				+ "									,phone=?"
				+ "									,update_date=NOW()"
				+ "									WHERE customer_id = ? ";
		
		try {
			
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
	conn = DBUtil.getConnection();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT customer_id, customer_pw, customer_pw_update_date,`name`, gender, birth_date, email, phone, `level`, create_date, update_date"
				+ " FROM customer"
				+ " WHERE customer_id = ?"; 
		
		try {
		
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT customer_id, `name`"
				+ " FROM customer"
				+ " ORDER BY update_date DESC"; 
		
		try {
			
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
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) cnt FROM customer";
		try {
			System.out.println("[customerDao.selectTotalRow()] conn:" + conn);

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
	//마지막 비밀번호 변경 날짜 요청 메서드
	public String selectLastUpdateDateCustomerPw(String customerId) {
		String date = null; // 마지막 비밀번호 변경 날짜 들어갈 변수 초기화
		//DB 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//비밀번호 변경날짜를 내림차순으로 정렬하는 쿼리, 시간 제외 날짜만 불러옴
		String sql = "SELECT STR_TO_DATE(customer_pw_update_date,'%Y-%m-%d') date "
				+ "FROM customer_pw_history "
				+ "WHERE customer_id = ? "
				+ "ORDER BY customer_pw_update_date DESC";
		try {
			System.out.println("[customerDao.selectLastUpdateDateCustomerPw()] conn:" + conn);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId); // ?에 customerId 셋팅

			rs = stmt.executeQuery();
			
			if (rs.next()) {//내림차순 중에서 한번만 rs.get --> 가장최근 날짜만 받아옴
				date = rs.getString("date");
				System.out.println("[customerDao.selectLastUpdateDateCustomerPw()] date :" + date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return date;
	}
		
}