package listener;

import java.util.Calendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class TomcatBootListener implements ServletContextListener {

    
    public TomcatBootListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  {
    }
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	//DB 드라이버 로딩
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("db드라이버로딩");//디버깅
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	//application에 오늘날짜 저장
    	Calendar now = Calendar.getInstance();
    	String today ="";//오늘 날짜가 들어갈 today 변수 초기화
    	today = today + now.get(Calendar.YEAR);//연도 입력
    	//월 입력
    	if(now.get(Calendar.MONTH)+1<10) { //Calendar는 현재 월 -1, 10보다 작으면 0x이 아닌 x 이 들어감으로
    		today = today + "-0"+(now.get(Calendar.MONTH)+1);
    	}else if(now.get(Calendar.MONTH)+1>=10) {//10보다크면 2자리임으로 바로 입력
    			today = today + "-"+(now.get(Calendar.MONTH)+1);
    		}
    	//날짜 입력
    	if(now.get(Calendar.DATE)<10) { //10보다 작으면 0x이 아닌 x 이 들어감으로
    		today = today + "-0"+now.get(Calendar.DATE);
    	}else if(now.get(Calendar.DATE)>=10) {//10보다크면 2자리임으로 바로 입력
    		today = today + "-"+now.get(Calendar.DATE);
    	}
    	System.out.println("[TomcatBootListener] 오늘 날짜 : "+today);
    	sce.getServletContext().setAttribute("applicationToday", today);
    }
}
