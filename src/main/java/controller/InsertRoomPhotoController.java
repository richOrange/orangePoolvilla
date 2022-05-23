package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.RoomPhotoDao;
import vo.RoomPhoto;


@WebServlet("/host/insertRoomPhotoController")
public class InsertRoomPhotoController extends HttpServlet {
	
	private RoomPhotoDao roomPhotoDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모델 값 호출
		this.roomPhotoDao = new RoomPhotoDao();
		// 경로 값 설정
		String path = request.getSession().getServletContext().getRealPath("/") + "image";
		// 디버깅
		System.out.println("[/host/insertRoomPhotoController.doPost()] path :" + path);
		// 요청값 처리
		MultipartRequest multipartRequest = new MultipartRequest(request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy());
		String roomPhotoName = multipartRequest.getFilesystemName("roomPhoto");
		String roomPhotoType = multipartRequest.getContentType("roomPhoto");
		String roomOriginalPhotoName = multipartRequest.getParameter("originalPhotoName");
		String roomPhotoArea = multipartRequest.getParameter("PhotoArea");
		// 디버깅
		System.out.println("[host/insertRoomPhotoController.doPost()] multipartRequest : " + multipartRequest.toString());
		
		if(roomPhotoType.equals("image/gif") || roomPhotoType.equals("image/png") || roomPhotoType.equals("image/jpeg")){
			System.out.println("[/host/insertRoomPhotoController.doPost()] 이미지 파일 넣기 성공 ");
			
			// 요청값 호출
			RoomPhoto roomPhoto = new RoomPhoto();
			roomPhoto.setPhotoNo(roomPhotoDao.insertRoomPhoto(roomPhoto));
			roomPhoto.setRoomNo(roomPhotoDao.insertRoomPhoto(roomPhoto));
			roomPhoto.setPhotoName(roomPhotoName);
			roomPhoto.setPhotoType(roomPhotoType);
			roomPhoto.setPhotoOriginalName(roomOriginalPhotoName);
			roomPhoto.setPhotoArea(roomPhotoArea);
			// 디버깅
			System.out.println("[/host/insertRoomPhotoController.doPost()] roomPhoto.toString() : "+ roomPhoto.toString());
			
			//Dao에 요청
			roomPhotoDao = new RoomPhotoDao();
			
			
			int row = roomPhotoDao.insertRoomPhoto(roomPhoto);
			System.out.println("[/host/insertRoomPhotoController.doPost()] row : " + row);
			if(row==1) {//성공시 insertRoomPhotoController로 돌려보냄
				System.out.println("[/host/insertRoomPhotoController.doPost()] 이미지 추가 성공 ");
				response.sendRedirect(request.getContextPath()+"/host/insertRoomPhotoController");
			} else { //실패시 파일삭제 및 selectHostPoolvillaOneController로 되돌려 보냄
				File file = new File(path+"/"+roomPhotoName);
				file.delete();// 파일 삭제
				response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
			}
		}else { // 업로드 불가능한 파일을 올리면 파일삭제 및 selectHostPoolvillaOneController로 되돌려보냄
			System.out.println("[/host/insertRoomPhotoController.doPost()] 업로드 불가능한 파일 형식 : img파일만 가능");
			File file = new File(path+"/"+roomPhotoName);
			file.delete();// 파일 삭제
			response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
		}
	}

}
