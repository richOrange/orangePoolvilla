package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PoolvillaDao;
import dao.PoolvillaPhotoDao;
import vo.Poolvilla;
import vo.PoolvillaPhoto;



@WebServlet("/host/insertPoolvillaPhotoController")
public class InsertPoolvillaPhotoController extends HttpServlet {
	private PoolvillaPhotoDao poolvillaPhotoDao;
	private PoolvillaDao poolvillaDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 요청값 호출
		int pvNo = Integer.parseInt(request.getParameter("pvNo"));
		
		// 풀빌라 정보 호출
		Poolvilla selectPoolvillaOne = poolvillaDao.selectPoolvillaOne(pvNo);
				
		// 모델값 setAttiribute
		request.setAttribute("selectPoolvillaOne", selectPoolvillaOne);//poolvillaOne 정보
		System.out.println("[/host/insertPoolvillaPhotoController.doget()] selectHostPoolvillaOne : " + selectPoolvillaOne.toString());
		
		
		
		response.sendRedirect(request.getContextPath()+"selectHostPoolvillaOne.jsp");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//모델 값 호출
		this.poolvillaPhotoDao = new PoolvillaPhotoDao();
		poolvillaDao = new PoolvillaDao();
		
		//경로 값 설정
		String path = request.getSession().getServletContext().getRealPath("/") + "image/poolvilla";
		
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] path : " + path);
		
		//요청값 처리
		int poolvillaPhotoNo = -1;
		int poolvillaPvNo = -1;
		
		poolvillaPhotoNo = Integer.parseInt(request.getParameter("poolvillaPhotoNo"));
		
		MultipartRequest multipartRequest  = new MultipartRequest(request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy()); // 여기서 이미지 들어감
		poolvillaPvNo = Integer.parseInt(multipartRequest.getParameter("pvNo"));
		String poolvillaPhotoName = multipartRequest.getFilesystemName("PhotoName");
		String poolvillaPhotoType = multipartRequest.getContentType("PhotoType");
		String poolvillaOriginalPhotoName = multipartRequest.getOriginalFileName("originalPhotoName");
		String poolvillaPhotoArea = "1024*1024*100";
		int poolvillaPhotoSize = Integer.parseInt(multipartRequest.getParameter("poolvillaPhoto"));
		
		
		
		//디버깅
		System.out.println("[/host/insertPoolvillaPhotoController.doPost()] multipartRequest : " + multipartRequest.toString());
		
		
		if(poolvillaPhotoType.equals("image/gif") || poolvillaPhotoType.equals("image/png") || poolvillaPhotoType.equals("image/jpeg")){
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 파일 넣기 성공 ");
			
			// 요청값 호출
			PoolvillaPhoto poolvillaPhoto = new PoolvillaPhoto();
			poolvillaPhoto.setPhotoNo(poolvillaPhotoNo);
			poolvillaPhoto.setPvNo(poolvillaPvNo);
			poolvillaPhoto.setPhotoName(poolvillaPhotoName);
			poolvillaPhoto.setPhotoType(poolvillaPhotoType);
			poolvillaPhoto.setPhotoOriginalName(poolvillaOriginalPhotoName);
			poolvillaPhoto.setPhotoArea(poolvillaPhotoArea);
			poolvillaPhoto.setPhotoSize(poolvillaPhotoSize);
			
			// 디버깅
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] poolvillaPhoto.toString() : "+ poolvillaPhoto.toString());
			
			//Dao에 요청
			poolvillaPhotoDao = new PoolvillaPhotoDao();
				
			
			
			int row = poolvillaPhotoDao.insertPoolvillaPhoto(poolvillaPhotoNo,poolvillaPhoto);
			System.out.println("[/host/insertPoolvillaPhotoController.doPost()] row : " + row);
			if(row==1) {//성공시 selectHostPoolvillaOneController로 돌려보냄
				System.out.println("[/host/insertPoolvillaPhotoController.doPost()] 이미지 추가 성공 ");
				response.sendRedirect(request.getContextPath()+"/host/selectHostPoolvillaOneController");
			
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
