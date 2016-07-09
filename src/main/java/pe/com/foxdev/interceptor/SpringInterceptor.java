package pe.com.foxdev.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SpringInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger=LoggerFactory.getLogger(SpringInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("post peticion");
		String modificador=request.getParameter("modificador");
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("pre-peticion");
		Enumeration<String> parametros=request.getParameterNames();
		
		while (parametros.hasMoreElements()){
			String cadena=parametros.nextElement();
			logger.info("atributo"+cadena);
		}
		String country=request.getParameter("country");
		//return super.preHandle(request, response, handler);
		if (StringUtils.equals(country, "1")){
			return true;
		}
		return false;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
