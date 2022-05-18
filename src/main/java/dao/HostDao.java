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

import vo.Customer;
import vo.Host;
import vo.Reservation;

public class HostDao {
	
	// 호스트 로그인용 메서드 (3306으로 설정되어있음[나재선])
	public Map<String,Object> loginHost(Host host) {
		Map<String,Object> sessionLoginMember = new HashMap<String,Object>();
		
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      String sql = "SELECT host_id hostId, level FROM host WHERE host_id=? AND host_pw=PASSWORD(?)";
	      try {
	         
	         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, host.getHostId());
	         stmt.setString(2, host.getHostPw());
	         rs = stmt.executeQuery();
	         if(rs.next()) {
	        	 sessionLoginMember.put("memberId", rs.getString("hostId")); //memberId에 hostId저장
	        	 sessionLoginMember.put("level", rs.getInt("level"));//level저장
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


	
	// HOST 테이블 전체 컬럼 구하는 메서드 
	public ArrayList<Host> selectHostList() {
		ArrayList<Host> hostList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "SELECT host_id hostId, host_pw hostPw, level, name, email, phone"
				+ ", create_date createDate, update_date updateDate"
				+ " FROM host"
				+ " WHERE LEVEL > 4"
				+ " ORDER BY level desc";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.selectHostList()] conn:" + conn);
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Host host = new Host();
				
				host.setHostId(rs.getString("hostId"));
				host.setHostPw(rs.getString("hostPw"));
				host.setLevel(rs.getInt("level"));
				host.setName(rs.getString("name"));
				host.setEmail(rs.getString("email"));
				host.setPhone(rs.getString("phone"));
				host.setCreateDate(rs.getString("createDate"));
				host.setUpdateDate(rs.getString("updateDate"));
				
				hostList.add(host);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}

		return hostList;
	}
	
	public void insertHost(Host host) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장

		String sql = "INSERT INTO host (host_id, host_pw, level, name, email, phone, create_date, update_date)"
				+ " VALUES (?,PASSWORD(?),5,?,?,?,NOW(),NOW())";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			System.out.println("[HostDao.insertHost()] conn:" + conn);
			// 자동 커밋을 해제
			conn.setAutoCommit(false);
			
			// 
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, host.getHostId());
			stmt.setString(2, host.getHostPw());
			
			stmt.setString(3, host.getName());
			stmt.setString(4, host.getEmail());
			stmt.setString(5, host.getPhone());
			
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("[HostDao.insertHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.insertHost()] row : 입력 실패");
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public void deleteHost(String hostId, String hostPw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		// 관리자 삭제 쿼리가 적용이 되었는제 확인하기 위해 만든 정수형 변수 
		int row = 0;
		
		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장
		
		String sql = "DELETE FROM host WHERE host_id = ? AND host_pw = PASSWORD(?)";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			// 자동 커밋을 해제 
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, hostId);
			stmt.setString(2, hostPw);
			
			// 쿼리 실행 
			row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("[HostDao.deleteHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.deleteHost()] row : 입력 실패");
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		 
	}
	
	public void updateHost(Host host) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int row = 0;
		
		String dburl = "jdbc:mariadb://localhost:3306/orangepoolvilla";
		// 연결하려는 DB의 IP 주소를 문자열 변수에 저장
		String dbuser = "root";
		// 연결하려는 DB의 아이디를 문자열 변수에 저장
		String dbpw = "java1234";
		// 연결하려는 DB의 패스워드를 문자열 변수에 저장
		
		// comma 자리에 AND 사용하면 에러 발생함 
		String sql = "UPDATE host"
				+ " SET host_pw = PASSWORD(?) , `level` = ? , `name` = ?"
				+ " , email = ? , phone = ? , create_date = NOW() , update_date = NOW()"
				+ " WHERE host_id = ?";
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, host.getHostPw());
			stmt.setInt(2, host.getLevel());
			stmt.setString(3, host.getName());
			stmt.setString(4, host.getEmail());
			stmt.setString(5, host.getPhone());
			stmt.setString(6, host.getHostId());
			
			row = stmt.executeUpdate();
			if(row == 1) {
				System.out.println("[HostDao.updateHost()] row : "+row+"행 입력 성공");
			} else {
				System.out.println("[HostDao.updateHost()] row : 입력 실패");
			}
			
			conn.commit();
		} catch (SQLException e) { 
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
		// HOST 테이블 전체 행 갯수 구하는 메서드
		public int selectHostTotalRow() {
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

			String sql = "SELECT COUNT(*) cnt FROM host";
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				System.out.println("[HostDao.selectTotalRow()] : 드라이버 로딩 성공");

				conn = DriverManager.getConnection(dburl, dbuser, dbpw);
				System.out.println("[HostDao.selectTotalRow()] conn:" + conn);

				stmt = conn.prepareStatement(sql);

				rs = stmt.executeQuery();

				if (rs.next()) {
					totalRow = rs.getInt("cnt");
					System.out.println("[HostDao.selectTotalRow()] totalRow :" + totalRow);
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
		
		//아이디 중복 체크 기능
		public int checkIdInHost(String hostId) {
			int row = -1; //쿼리가 정상적으로 작동 되지 않으면 -1
			// 데이터베이스 자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				// 데이터베이스 드라이버 연결
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla", "root", "java1234");
				System.out.println("[HostDao.checkIdInHost()] 드라이버 로딩 성공");
				
				String sql = "SELECT * FROM host WHERE host_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, hostId);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					//rs.next()에 값이 있으면 row = 1
					row = 1;
					System.out.println("[HostDao.checkIdInHost()] 중복아이디가 존재합니다");
				}else {//없으면 row = 0
					row=0;
					System.out.println("[HostDao.checkIdInHost()] 가입가능한 아이디입니다");
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
		

}
