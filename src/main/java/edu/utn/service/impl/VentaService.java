package edu.utn.service.impl;

import java.util.ArrayList;

import edu.utn.dataccess.IVentaDao;
import edu.utn.domain.Venta;
import edu.utn.service.IVentaService;

public class VentaService implements IVentaService {
	
	private IVentaDao dataAccess = null;
	
	@Override
	public ArrayList<Venta> getAll() {
		return this.dataAccess.getAll();
	}

	public IVentaDao getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(IVentaDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	

}
