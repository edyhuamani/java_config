package pe.com.foxdev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLogger {
	
	private static final Logger LOG=LoggerFactory.getLogger(AspectLogger.class);
	
	@Before("execution(* pe.com.foxdev.business.CatalogoBusiness.obtenerTipoCambio(..))")
	public void logBefore(JoinPoint joinPoint) {
		LOG.info("aspect logBefore");
		LOG.info("logBefore() is running!");
		LOG.info("hijacked : " + joinPoint.getSignature().getName());
		LOG.info("******");
	}
	
	@After("execution(* pe.com.foxdev.business.CatalogoBusiness.obtenerTipoCambio(..))")
	public void logAfter(JoinPoint joinPoint) {

		LOG.info("logAfter() is running!");
		LOG.info("hijacked : " + joinPoint.getSignature().getName());
		LOG.info("******");

	}
	
	
}
