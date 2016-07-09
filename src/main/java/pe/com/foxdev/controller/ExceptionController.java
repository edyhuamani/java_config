package pe.com.foxdev.controller;

import java.net.MalformedURLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.foxdev.beans.BaseResponseBean;

/**
 * @author Edy
 *
 */
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value=RuntimeException.class)
	public String getRuntimeException(RuntimeException runtimeException ){
		return "runtime exception";
	}
	
 	
 	

 	@ExceptionHandler(MalformedURLException.class)
	public String getPageNotFound(MalformedURLException handlerException){
		return "url exception ";
	}

 	
 	@ExceptionHandler(NoHandlerFoundException.class)
 	@ResponseStatus(value=HttpStatus.NOT_FOUND)
 	@ResponseBody
 	public ResponseEntity<String> requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
 		CustomizeException exception=null;
 		BaseResponseBean<String> response=new BaseResponseBean<>();
 		HttpHeaders headers = new HttpHeaders();
 		String json=null;
 		try{
 		Locale locale = LocaleContextHolder.getLocale();
 	    //String errorMessage = messageSource.getMessage("error.bad.url", null, locale);
 		response.setObject(ex.getMessage());
 		response.setCodigoRespuesta("105");
 		response.setMensajeRespuesta("Mensaje de respuesta");
 		
 	    headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
 		
 	    String errorURL = req.getRequestURL().toString();
 	     exception=new CustomizeException(ex.getMessage());
 	   ObjectMapper mapper=new ObjectMapper();
 	   json=mapper.writeValueAsString(response);
 	  
 	    //ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
 	    }catch(Exception e){
 	    	
 	    }
 	   return new ResponseEntity<String>(json,HttpStatus.BAD_REQUEST);
 	}
}
