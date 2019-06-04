package edu.utn.util;

import java.util.Comparator;

import edu.utn.transferObjects.ComisionXVentas;

public class ComparadorMejorVendedor implements Comparator<ComisionXVentas> {

	@Override
	public int compare(ComisionXVentas arg0, ComisionXVentas arg1) {
		return arg1.getCantidadVentas() - arg0.getCantidadVentas();
	}

}
