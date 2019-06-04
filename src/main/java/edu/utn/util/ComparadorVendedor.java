package edu.utn.util;

import java.util.Comparator;

import edu.utn.domain.Usuario;

public class ComparadorVendedor implements Comparator<Usuario> {

	@Override
	public int compare(Usuario arg0, Usuario arg1) {
		return arg1.getVentas().size() - arg0.getVentas().size();
	}

}
