package controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PoolvillaDao;
import dao.PoolvillaPhotoDao;
import vo.PoolvillaPhoto;



@WebServlet("/host/insertPoolvillaPhotoController")
public class InsertPoolvillaPhotoController extends HttpServlet {
	private PoolvillaPhotoDao poolvillaPhotoDao;
	private PoolvillaDao poolvillaDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//모델 값 호출
		poolvillaPhotoDao = new PoolvillaPhotoDao();
		poolvillaDao = new PoolvillaDao();
		
		//경로 값 설정
		String path = request.getSession().getServletContext().getRealPath("/") + "image/poolvilla";
		
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] path : " + path);
		
		//요청값 처리
		
		
		
		
		
		MultipartRequest multipartRequest  = new MultipartRequest(request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy()); // 여기서 이미지 들어감
		int pvNo = Integer.parseInt(multipartRequest.getParameter("pvNo"));
		String poolvillaPhotoName = multipartRequest.getFilesystemName("poolvillaPhoto");
		String poolvillaPhotoType = multipartRequest.getContentType("poolvillaPhoto");
		String poolvillaOriginalPhotoName = multipartRequest.getOriginalFileName("poolvillaPhoto");
		
		
		
		
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaPhotoName : "+ poolvillaPhotoName );
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaPhotoType : "+ poolvillaPhotoType );
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaOriginalPhotoName : "+ poolvillaOriginalPhotoName );
		
		if(poolvillaPhotoType.equals("image/gif") || poolvillaPhotoType.equals("image/png") || poolvillaPhotoType.equals("image/jpeg")){
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 파일 넣기 성공 ");
			
			// 요청값 호출
			PoolvillaPhoto pp = new PoolvillaPhoto();
			
			pp.setPvNo(pvNo);
			pp.setPhotoName(poolvillaPhotoName);
			pp.setPhotoType(poolvillaPhotoType);
			pp.setPhotoOriginalName(poolvillaOriginalPhotoName);
			
			// 디버깅
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaPhoto.toString() : "+ pp.toString());
			
			//Dao에 요청
			poolvillaPhotoDao = new PoolvillaPhotoDao();
				
			
			
			int row = poolvillaPhotoDao.insertPoolvillaPhoto(pp);
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] row : " + row);
			if(row==1) {//성공시 selectHostPoolvillaOneController로 돌려보냄
				System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 추가 성공 ");
				response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController?pvNo=" + pvNo +"&Photo");
			
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
