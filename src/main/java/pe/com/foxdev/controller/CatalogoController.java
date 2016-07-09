package pe.com.foxdev.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.foxdev.beans.BaseResponseBean;
import pe.com.foxdev.business.CatalogoBusiness;
import pe.com.foxdev.utils.PropertiesAplication;

/**
 * @author Edy
 *
 */

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
	
	private static final Logger logger=LoggerFactory.getLogger(CatalogoController.class);
	
	@Autowired
	private PropertiesAplication propertiesAplication;
	
	@Autowired
	@Qualifier(value="catalogoBusiness")
	private CatalogoBusiness catalogoBusiness;
	
	/**@
	 * @param fecha
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tipoCambio",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseBean<String>> obtenerTipoCambio(
		@RequestParam(name="country") String country,
			HttpServletRequest request
			)  throws CustomizeException {
		logger.info("obtenerTipoCambio"+country);
		BaseResponseBean<String> response=new BaseResponseBean<>();
		//ResponseEntity<BaseResponseBean<String>> response=new ResponseEntity<BaseResponseBean<String>>();
		try{
			catalogoBusiness.obtenerTipoCambio();
			response.setCodigoRespuesta(propertiesAplication.getCodigo());
			response.setMensajeRespuesta(propertiesAplication.getMensaje());
			response.setObject("mensaje");
		}catch(Exception e){
			response.setCodigoRespuesta(propertiesAplication .getCodigoError());
			response.setMensajeRespuesta(propertiesAplication.getMensajeError());
			response.setObject("mensaje error");
			logger.error(e.getMessage(),e);
		}
		return new ResponseEntity<BaseResponseBean<String>>(response,HttpStatus.OK);
	}
}
