package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.RoomPhoto;

public class RoomPhotoDao {
	public int insertRoomPhoto(RoomPhoto roomPhoto) {
		System.out.println("[PoolvillaDao.insertPoolvillaPhoto]");
		int row=-1;
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="INSERT INTO room_photo("
				+ "							room_no"
				+ "							,photo_name"
				+ "							,photo_original_name"
				+ "							,photo_type"
				+ "							,create_date"
				+ "							,update_date)"
				+ "	VALUES(?,?,?,?,NOW(),NOW())";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/orangepoolvilla","root","java1234");
			stmt = conn.prepareStatement(sql);					
			stmt.setInt(1, roomPhoto.getRoomNo());
			stmt.setString(2, roomPhoto.getPhotoName());
			stmt.setString(3, roomPhoto.getPhotoOriginalName());
			stmt.setString(4, roomPhoto.getPhotoType());
			row=stmt.executeUpdate();
			System.out.println("[RoomPhotoDao.RoomPhoto] row " + row);
			if(row ==1 ) {
				System.out.println("[RoomPhotoDao.updatePassword] RoomPhoto 추가 성공");
			} else {
				System.out.println("[RoomPhotoDao.updatePassword] RoomPhoto 추가 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {										
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return row;
	}
	
	public List<RoomPhoto> selectRoomPhoto(int roomNo){
		List<RoomPhoto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT photo_no"
				+ "			,room_no"
				+ "			,photo_name"
				+ "			,photo_original_name"
				+ "			,photo_type"
				+ "			,photo_area"
				+ "			,create_date"
				+ "			,update_date"
				+ "	FROM room_photo"
				+ "	WHERE room_no = ?;";
		try {
			   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/oragepoolvilla","root","java1234");
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roomNo);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	RoomPhoto roomPhoto = new RoomPhoto();
            	roomPhoto.setPhotoNo(rs.getInt("photoNo"));
            	roomPhoto.setRoomNo(rs.getInt("roomNo"));
            	roomPhoto.setPhotoName(rs.getString("photoName"));
            	roomPhoto.setPhotoOriginalName(rs.getString("photoOriginalName"));
            	roomPhoto.setPhotoType(rs.getString("photoType"));
            	roomPhoto.setPhotoArea(rs.getString("photoArea"));
            	roomPhoto.setCreateDate(rs.getString("createDate"));
            	roomPhoto.setUpdateDate(rs.getString("updateDate"));
				list.add(roomPhoto);
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
		
		
		return list;
	}

}
