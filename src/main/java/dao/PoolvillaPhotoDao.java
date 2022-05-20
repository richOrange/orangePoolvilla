package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.PoolvillaPhoto;

public class PoolvillaPhotoDao {
	public int insertPoolvillaPhoto(PoolvillaPhoto poolvillaPhoto) {
		System.out.println("[PoolvillaDao.insertPoolvillaPhoto]");
		int row=-1;
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="INSERT INTO pollvailla_photo(photo_no"
				+ "								,pv_no"
				+ "								,photo_name"
				+ "								,photo_original_name"
				+ "								,photo_type"
				+ "								,photo_size"
				+ "								,photo_area"
				+ "								,create_date"
				+ "								,update_date)n"
				+ "	VALUES(?,?,?,?,?,?,?,NOW(),NOW())";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shoppingmall","root","java1234");
			stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);					
			stmt.setInt(1, poolvillaPhoto.getPhotoNo());
			stmt.setInt(2, poolvillaPhoto.getPvNo());
			stmt.setString(3, poolvillaPhoto.getPhotoName());
			stmt.setString(4, poolvillaPhoto.getPhotoOriginalName());
			stmt.setString(5, poolvillaPhoto.getPhotoType());
			stmt.setDouble(6, poolvillaPhoto.getPhotoSize());
			stmt.setString(7, poolvillaPhoto.getPhotoArea());
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
