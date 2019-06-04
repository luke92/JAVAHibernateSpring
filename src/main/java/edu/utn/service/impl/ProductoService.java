package edu.utn.service.impl;

import java.util.ArrayList;

import edu.utn.dataccess.IProductoDao;
import edu.utn.domain.Producto;
import edu.utn.service.IProductoService;

public class ProductoService implements IProductoService {
	
	private IProductoDao dataAccess = null;

	@Override
	public ArrayList<Producto> getAll() {
		return this.dataAccess.getAll();
	}

	public IProductoDao getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(IProductoDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	
}
