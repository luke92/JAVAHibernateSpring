package edu.utn.service.impl;

import java.util.ArrayList;

import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Usuario;
import edu.utn.service.IUsuarioService;

public class UsuarioService implements IUsuarioService {
	
	private IUsuarioDao dataAccess = null;

	public IUsuarioDao getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(IUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<Usuario> getAll() {
		return this.dataAccess.getAll();
	}

}
