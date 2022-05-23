package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.PoolvillaPhoto;

public class PoolvillaPhotoDao {
	public int insertPoolvillaPhoto( PoolvillaPhoto poolvillaPhoto ) {
		System.out.println("[PoolvillaDao.insertPoolvillaPhoto]");
		int row=-1; //쿼리가 정상적으로 작동 되지 않으면 -1
		// 데이터베이스 자원 준비
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			// 풀빌라 포토에 포토정보 저장
			String sql="INSERT INTO poolvilla_photo(pv_no"
					+ "								,photo_name"
					+ "								,photo_original_name"
					+ "								,photo_type"
					+ "								,photo_area"
					+ "								,create_date"
					+ "								,update_date)"
					+ "	VALUES(?,?,?,?,?,NOW(),NOW())";
			
			
			stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);					
			
			stmt.setInt(1, poolvillaPhoto.getPvNo());
			stmt.setString(2, poolvillaPhoto.getPhotoName());
			stmt.setString(3, poolvillaPhoto.getPhotoOriginalName());
			stmt.setString(4, poolvillaPhoto.getPhotoType());
			stmt.setString(5, poolvillaPhoto.getPhotoArea());
			stmt.executeUpdate();
			rs=stmt.getGeneratedKeys();
			row=stmt.executeUpdate();
			System.out.println("[PoolvillaDao.PoolvillaPhoto] row " + row);
			
			if(rs.next()) {
				row = 1;
				System.out.println("[PoolvillaPhotoDao.updatePassword] PoolvillaPhoto 테이블 수정 성공");
			} else {
				System.out.println("[PoolvillaPhotoDao.updatePassword] PoolvillaPhoto 테이블 수정 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {										
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return row;
	}
}
