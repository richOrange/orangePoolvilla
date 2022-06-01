package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.PoolvillaPhoto;
import vo.RoomPhoto;

public class PoolvillaPhotoDao {
	public int insertPoolvillaPhoto( PoolvillaPhoto poolvillaPhoto ) {
		System.out.println("[PoolvillaDao.insertPoolvillaPhoto]");
		int row=-1; //쿼리가 정상적으로 작동 되지 않으면 -1
		// 데이터베이스 자원 준비
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
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
	
	public PoolvillaPhoto selectPoolvillaPhoto(int pvNo) {
		PoolvillaPhoto poolvillaPhoto = new PoolvillaPhoto();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT photo_no photoNo"
				+ "			,pv_no pvNo"
				+ "			,photo_name photoName"
				+ "			,photo_original_name photoOriginalName"
				+ "			,photo_type photoType"
				+ "			,create_date createDate"
				+ "			,update_date updateDate"
				+ "	FROM poolvilla_photo"
				+ "	WHERE pv_no = ?;";
		try {
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, pvNo);
         rs = stmt.executeQuery();
         if (rs.next()) {
         	poolvillaPhoto.setPhotoNo(rs.getInt("photoNo"));
         	poolvillaPhoto.setPvNo(rs.getInt("pvNo"));
         	poolvillaPhoto.setPhotoName(rs.getString("photoName"));
         	poolvillaPhoto.setPhotoOriginalName(rs.getString("photoOriginalName"));
         	poolvillaPhoto.setPhotoType(rs.getString("photoType"));
         	poolvillaPhoto.setCreateDate(rs.getString("createDate"));
         	poolvillaPhoto.setUpdateDate(rs.getString("updateDate"));
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
		
		
		return poolvillaPhoto;
	
	}
	
	// orangepoolvilla db의 poolvilla_photo 테이블 데이터 삭제
		public int deletePoolvillaPhoto(String photoName) {
			System.out.println("[PoolvillaPhotoDao.deletePoolvillaPhoto] 진행  시작");
			// DB 자원 준비
			Connection conn = null;
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			int row = 0;

			try {
				String sql = "DELETE FROM poolvilla_photo WHERE photo_name = ?";
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, photoName);
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// DB 자원 반환
					conn.close();

					// 디버깅 코드
					if (row == 1) {
						System.out.println("[PoolvillaPhotoDao.deletePoolvillaPhoto] 삭제 성공");
					} else {
						System.out.println("[PoolvillaPhotoDao.deletePoolvillaPhoto] 삭제 실패");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
}
