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
import vo.RoomPhoto;

public class RoomPhotoDao {
	public int insertRoomPhoto(RoomPhoto roomPhoto) {
		//결과행수 받을 변수 초기화
		int row=-1;
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaDao.insertPoolvillaPhoto] DB 연결");
			//쿼리 작성
			String sql="INSERT INTO room_photo("
					+ "							room_no"
					+ "							,photo_name"
					+ "							,photo_original_name"
					+ "							,photo_type"
					+ "							,create_date"
					+ "							,update_date)"
					+ "	VALUES(?,?,?,?,NOW(),NOW())";
			stmt = conn.prepareStatement(sql);					
			stmt.setInt(1, roomPhoto.getRoomNo());
			stmt.setString(2, roomPhoto.getPhotoName());
			stmt.setString(3, roomPhoto.getPhotoOriginalName());
			stmt.setString(4, roomPhoto.getPhotoType());
			row=stmt.executeUpdate();
			System.out.println("[RoomPhotoDao.RoomPhoto] row " + row);
			//디버깅
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
		//결과 값 받을 변수 초기화
		List<RoomPhoto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//DB연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaDao.selectRoomPhoto] DB 연결");
			//쿼리 작성
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
				   //DB 자원 반납
				   conn.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
		   }
		
		
		return list;
	}
	//룸 사진 삭제 기능
	public void deletePoolvillaRoomPhoto(int roomNo) {
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//DB 연결
			conn = DBUtil.getConnection();
			System.out.println("[PoolvillaDao.deletePoolvillaRoomPhoto] DB 연결");
			//쿼리 작성
			String sql = "DELETE FROM room_photo WHERE room_no = ?  ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, roomNo);
			stmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
