package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PoolvillaPhoto;
import vo.RoomPhoto;

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
					+ "								,create_date"
					+ "								,update_date)"
					+ "	VALUES(?,?,?,?,NOW(),NOW())";
			
			
			stmt = conn.prepareStatement(sql);					
			
			stmt.setInt(1, poolvillaPhoto.getPvNo());
			stmt.setString(2, poolvillaPhoto.getPhotoName());
			stmt.setString(3, poolvillaPhoto.getPhotoOriginalName());
			stmt.setString(4, poolvillaPhoto.getPhotoType());
			row=stmt.executeUpdate();
			System.out.println("[PoolvillaDao.PoolvillaPhoto] row " + row);
			
			if(row==1) {
				System.out.println("[PoolvillaPhotoDao.updatePassword] PoolvillaPhoto 테이블 수정 성공");
			} else {
				System.out.println("[PoolvillaPhotoDao.updatePassword] PoolvillaPhoto 테이블 수정 실패");
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
	
	public List<PoolvillaPhoto> selectPoolvillaPhoto(int pvNo) {
		List<PoolvillaPhoto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT photo_no"
				+ "			,pv_no"
				+ "			,photo_name"
				+ "			,photo_original_name"
				+ "			,photo_type"
				+ "			,create_date"
				+ "			,update_date"
				+ "	FROM poolvilla_photo"
				+ "	WHERE pv_no = ?;";
		try {
			   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/oragepoolvilla","root","java1234");
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, pvNo);
         rs = stmt.executeQuery();
         while (rs.next()) {
         	PoolvillaPhoto poolvillaPhoto = new PoolvillaPhoto();
         	poolvillaPhoto.setPhotoNo(rs.getInt("photoNo"));
         	poolvillaPhoto.setPvNo(rs.getInt("pvNo"));
         	poolvillaPhoto.setPhotoName(rs.getString("photoName"));
         	poolvillaPhoto.setPhotoOriginalName(rs.getString("photoOriginalName"));
         	poolvillaPhoto.setPhotoType(rs.getString("photoType"));
         	poolvillaPhoto.setCreateDate(rs.getString("createDate"));
         	poolvillaPhoto.setUpdateDate(rs.getString("updateDate"));
				list.add(poolvillaPhoto);
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
