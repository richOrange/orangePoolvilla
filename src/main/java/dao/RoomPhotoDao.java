package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.RoomPhoto;

public class RoomPhotoDao {
	public int insertRoomPhoto(RoomPhoto roomPhoto) {
		System.out.println("[PoolvillaDao.insertPoolvillaPhoto]");
		int row=-1;
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="INSERT INTO room_photo(photo_no"
				+ "							,room_no"
				+ "							,photo_name"
				+ "							,photo_original_name"
				+ "							,photo_type"
				+ "							,photo_size"
				+ "							,photo_area"
				+ "							,create_date"
				+ "							,update_date)"
				+ "	VALUES(?,?,?,?,?,?,?,NOW(),NOW())";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shoppingmall","root","java1234");
			stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);					
			stmt.setInt(1, roomPhoto.getPhotoNo());
			stmt.setInt(2, roomPhoto.getRoomNo());
			stmt.setString(3, roomPhoto.getPhotoName());
			stmt.setString(4, roomPhoto.getPhotoOriginalName());
			stmt.setString(5, roomPhoto.getPhotoType());
			stmt.setDouble(6, roomPhoto.getPhotoSize());
			stmt.setString(7, roomPhoto.getPhotoArea());
			stmt.executeUpdate();
			rs=stmt.getGeneratedKeys();
			row=stmt.executeUpdate();
			System.out.println("[RoomPhotoDao.RoomPhoto] row " + row);
			if(rs.next()) {
				row = 1;
				System.out.println("[RoomPhotoDao.updatePassword] RoomPhoto 테이블 수정 성공");
			} else {
				System.out.println("[RoomPhotoDao.updatePassword] RoomPhoto 테이블 수정 실패");
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
