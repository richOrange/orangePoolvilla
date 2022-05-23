package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		this.roomPhotoDao = new RoomPhotoDao();
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo")); // 사진 추가할 roomNo값 받아오기
		System.out.println("InsertRoomPhotoController.doGet() roomNo" + roomNo);
		
		List<RoomPhoto> list = roomPhotoDao.selectRoomPhoto(roomNo);
	
		request.setAttribute("roomPhotoList", list);
		
		request.getRequestDispatcher("WEB-INF/view/insertPoolvillaRoomNBed.jsp").forward(request, response);
		
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int row = -1;
		// 모델 값 호출
		this.roomPhotoDao = new RoomPhotoDao();
		// 경로 값 설정
		String path = request.getSession().getServletContext().getRealPath("/image");
		// 디버깅
		System.out.println("[/host/insertRoomPhotoController.doPost()] path :" + path);
		// 요청값 처리
		MultipartRequest multipartRequest = new MultipartRequest(request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy());
//		if (request.getParameter("pvNo") == null) {
//			response.sendRedirect(request.getContextPath() + "/all/homeController?msg=null");
//			return;
//		}
//		
//		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		
		if(request.getParameter("roomNo") == null || request.getParameter("roomNo") =="") {
			System.out.println("roomNo좀 받아라roomNo좀 받아라roomNo좀 받아라roomNo좀 받아라roomNo좀 받아라");
		}
		int roomNo = Integer.parseInt(multipartRequest.getParameter("roomNo"));
		String PhotoName = multipartRequest.getFilesystemName("roomPhoto");
		String PhotoType = multipartRequest.getContentType("roomPhoto");
		String OriginalPhotoName = multipartRequest.getParameter("roomPhoto");
		String PhotoArea = multipartRequest.getParameter("roomPhoto");
		// 디버깅
		System.out.println("[host/insertRoomPhotoController.doPost()] multipartRequest : " + multipartRequest.toString());
		
		if(PhotoType.equals("image/gif") || PhotoType.equals("image/png") || PhotoType.equals("image/jpeg")){
			System.out.println("[/host/insertRoomPhotoController.doPost()] 이미지 파일 넣기 성공 ");
			
			// 요청값 호출
			RoomPhoto roomPhoto = new RoomPhoto();
			roomPhoto.setPhotoNo(roomPhotoDao.insertRoomPhoto(roomPhoto));
			roomPhoto.setRoomNo(roomPhotoDao.insertRoomPhoto(roomPhoto));
			roomPhoto.setPhotoName(roomPhoto.getPhotoName());
			roomPhoto.setPhotoType(roomPhoto.getPhotoType());
			roomPhoto.setPhotoOriginalName(roomPhoto.getPhotoOriginalName());
			roomPhoto.setPhotoArea(roomPhoto.getPhotoArea());
			// 디버깅
			System.out.println("[/host/insertRoomPhotoController.doPost()] roomPhoto.toString() : "+ roomPhoto.toString());
			
			//Dao에 요청
			roomPhotoDao = new RoomPhotoDao();
			
			row = roomPhotoDao.insertRoomPhoto(roomPhoto);
			
			System.out.println("[InsertRoomPhotoController.doPost()] roomPhotoName :" + PhotoName);		
			System.out.println("[InsertRoomPhotoController.doPost()] roomPhotoType :" + PhotoType);		
			System.out.println("[InsertRoomPhotoController.doPost()] roomOriginalPhotoName :" + OriginalPhotoName);		
			System.out.println("[InsertRoomPhotoController.doPost()] roomPhotoArea :" + PhotoArea);		
			
			System.out.println("[/host/insertRoomPhotoController.doPost()] row : " + row);
			if(row==1) {//성공시 insertRoomPhotoController로 돌려보냄
				System.out.println("[/host/insertRoomPhotoController.doPost()] 이미지 추가 성공 ");
				response.sendRedirect(request.getContextPath()+"/host/insertRoomPhotoController");
			} else { //실패시 파일삭제 및 insertRoomNBedController로 되돌려 보냄
				File file = new File(path+"/"+PhotoName);
				file.delete();// 파일 삭제
				response.sendRedirect(request.getContextPath()+"/host/insertRoomNBedController?roomNo=" + roomNo);
			}
		}else { // 업로드 불가능한 파일을 올리면 파일삭제 및 selectHostPoolvillaOneController로 되돌려보냄
			System.out.println("[/host/insertRoomPhotoController.doPost()] 업로드 불가능한 파일 형식 : img파일만 가능");
			File file = new File(path+"/"+PhotoName);
			file.delete();// 파일 삭제
			response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
		}
	}

}
