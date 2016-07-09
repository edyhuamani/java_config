package pe.com.foxdev.controller;

import pe.com.foxdev.beans.BaseResponseBean;

public class CustomizeException extends Exception{
	public CustomizeException() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomizeException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
		
	}
	public CustomizeException(String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
		
		
	}
	
	public CustomizeException(Throwable cause){
		super(cause);
	}
	
	
	
}
