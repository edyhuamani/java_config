package pe.com.foxdev.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAplication {//implements WebApplicationInitializer{

//	@Override
//	public void onStartup(ServletContext container) throws ServletException {
//		// TODO Auto-generated method stub
//		// http://docs.spring.io/spring/docs/3.1.x/javadoc-api/org/springframework/web/WebApplicationInitializer.html
//		// al root context le estoy 
//		AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
//	    rootContext.register(AplicationConfig.class);
//	    container.addListener(new ContextLoaderListener(rootContext));
//
//	    
//	    AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//	    dispatcherContext.register(DispatcherConfig.class);
//
//		// Register and map the dispatcher servlet
//		ServletRegistration.Dynamic dispatcher =
//		container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");
		
	//}

}
