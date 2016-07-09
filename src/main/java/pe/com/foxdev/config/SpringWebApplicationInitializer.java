package pe.com.foxdev.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Edy
 *
 */
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	private static final Logger LOG=LoggerFactory.getLogger(SpringWebApplicationInitializer.class);
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		 LOG.info("-----------------------------------onStartup");
		
		 AnnotationConfigWebApplicationContext servletContext = new AnnotationConfigWebApplicationContext();
		 servletContext.register(DispatcherConfig.class);
			 
		 FilterRegistration.Dynamic encodingFilterRegistration; 
		 FilterRegistration.Dynamic securityFilterRegistration;
		 
	
	
		 AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
		 rootContext.register(AplicationConfig.class);
		 container.addListener(new ContextLoaderListener(rootContext));

		 DispatcherServlet dispatcherServlet=new DispatcherServlet(servletContext);
		 dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		 ServletRegistration.Dynamic dispatcher=container.addServlet("DispatcherServlet",dispatcherServlet); 
		 dispatcher.setLoadOnStartup(1);
		 dispatcher.addMapping("/*");
		 
		 
		 encodingFilterRegistration = container.addFilter("encodingFilter", CharacterEncodingFilter.class);
		 encodingFilterRegistration.setInitParameter("encoding", "UTF-8");
		 encodingFilterRegistration.setInitParameter("forceEncoding", "true");
		 encodingFilterRegistration.addMappingForUrlPatterns(null, false, "/*");
		 
		 //securityFilterRegistration = servletContext.addFilter("springSecurityFilterChain",DelegatingFilterProxy.class);
		 //securityFilterRegistration.addMappingForUrlPatterns(null, false, "/*");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		LOG.info(">> getRootConfigClasses");
		return new Class<?>[] {AplicationConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		LOG.info(">>getServletConfigClasses ");
		return new Class<?>[]{DispatcherConfig.class}; 
	}

	@Override
	protected String[] getServletMappings() {

		return new String[]{"/"};
	}


	
	 private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("pe.com.foxdev.config"); // busca el archivo con la anotacion @Configuration 
        return context;
    }
	 
//	@Override
//	protected WebApplicationContext createServletApplicationContext() {
//
//		 AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
//		 rootContext.register(AplicationConfig.class);
//		 return rootContext;
//		
//		 
//	}
//	
//	
//	@Override
//	protected WebApplicationContext createRootApplicationContext() {
//	
//		AnnotationConfigWebApplicationContext dispatcherContext =new AnnotationConfigWebApplicationContext();
//		dispatcherContext.register(DispatcherConfig.class);
//
//		return dispatcherContext;
//	}
	 
	 
	 
}
 