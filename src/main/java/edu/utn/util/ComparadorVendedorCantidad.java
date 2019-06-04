package edu.utn.util;

import java.util.Comparator;

import edu.utn.transferObjects.VendedorCantidad;

public class ComparadorVendedorCantidad  implements Comparator<VendedorCantidad> {

	@Override
	public int compare(VendedorCantidad arg0, VendedorCantidad arg1) {
		return arg1.getCantidad() - arg0.getCantidad();
	}

}

