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

import dao.PoolvillaPhotoDao;
import vo.PoolvillaPhoto;


/**
 * Servlet implementation class InsertPoolvillaPhoto
 */
@WebServlet("/host/insertPoolvillaPhotoController")
public class InsertPoolvillaPhotoController extends HttpServlet {
	private PoolvillaPhotoDao poolvillaPhotoDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모델 값 호출
		this.poolvillaPhotoDao = new PoolvillaPhotoDao();
		//경로 값 설정
		String path = request.getSession().getServletContext().getRealPath("/") + "image";
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] path : " + path);
		//요청값 처리
		MultipartRequest multipartRequest  = new MultipartRequest(request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy());
		String poolvillaPhotoName = multipartRequest .getFilesystemName("poolvillaPhoto");
		String poolvillaPhotoType = multipartRequest .getContentType("poolvillaPhoto");
		String poolvillaOriginalPhotoName = multipartRequest.getParameter("originalPhotoName");
		String poolvillaPhotoArea = multipartRequest.getParameter("PhotoArea");
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] multipartRequest : " + multipartRequest.toString());
		
		
		if(poolvillaPhotoType.equals("image/gif") || poolvillaPhotoType.equals("image/png") || poolvillaPhotoType.equals("image/jpeg")){
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 파일 넣기 성공 ");
			
			// 요청값 호출
			PoolvillaPhoto poolvillaPhoto = new PoolvillaPhoto();
			poolvillaPhoto.setPhotoNo(poolvillaPhotoDao.insertPoolvillaPhoto(poolvillaPhoto));
			poolvillaPhoto.setPvNo(poolvillaPhotoDao.insertPoolvillaPhoto(poolvillaPhoto));
			poolvillaPhoto.setPhotoName(poolvillaPhotoName);
			poolvillaPhoto.setPhotoType(poolvillaPhotoType);
			poolvillaPhoto.setPhotoOriginalName(poolvillaOriginalPhotoName);
			poolvillaPhoto.setPhotoArea(poolvillaPhotoArea);
			// 디버깅
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaPhoto.toString() : "+ poolvillaPhoto.toString());
			
			//Dao에 요청
			poolvillaPhotoDao = new PoolvillaPhotoDao();
			
			
			int row = poolvillaPhotoDao.insertPoolvillaPhoto(poolvillaPhoto);
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] row : " + row);
			if(row==1) {//성공시 insertPoolvillaPhotoController로 돌려보냄
				System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 추가 성공 ");
				response.sendRedirect(request.getContextPath()+"/host/insertPoolvillaPhotoController");
			} else { //실패시 파일삭제 및 selectHostPoolvillaOneController로 되돌려 보냄
				File file = new File(path+"/"+poolvillaPhotoName);
				file.delete();// 파일 삭제
				response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
			}
		}else { // 업로드 불가능한 파일을 올리면 파일삭제 및 selectHostPoolvillaOneController로 되돌려보냄
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 업로드 불가능한 파일 형식 : img파일만 가능");
			File file = new File(path+"/"+poolvillaPhotoName);
			file.delete();// 파일 삭제
			response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
		}
		
		
	
	}

}
