package edu.utn.transferObjects;

import java.io.Serializable;

public class ComisionXVentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vendedor;
	private Integer cantidadVentas;
	private float comision;
	
	public ComisionXVentas(){}
	
	public ComisionXVentas(String Vendedor, Integer cantidadVentas, float comision)
	{
		setVendedor(Vendedor);
		setComision(comision);
		setCantidadVentas(cantidadVentas);
	}
	
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public Integer getCantidadVentas() {
		return cantidadVentas;
	}
	public void setCantidadVentas(Integer cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	
	
}
