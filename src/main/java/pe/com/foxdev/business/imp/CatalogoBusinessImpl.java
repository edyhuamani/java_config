package pe.com.foxdev.business.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.foxdev.business.CatalogoBusiness;
import pe.com.foxdev.dao.CatalogoDAO;

@Service(value="catalogoBusiness")
public class CatalogoBusinessImpl implements CatalogoBusiness{

	@Autowired
	private CatalogoDAO catalogoDAO;
	
	public void obtenerTipoCambio() {
		try{
			catalogoDAO.obtenerInformacion();
		}catch(Exception e){
			
		}
		// TODO Auto-generated method stub
		
	}

}
