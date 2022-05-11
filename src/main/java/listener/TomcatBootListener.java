package listener;

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
    }
}
