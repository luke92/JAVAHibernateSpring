package edu.utn.transferObjects;

import java.io.Serializable;

public class ComisionCampania implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vendedor;
	private String producto;
	private float comision;
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public ComisionCampania(String vendedor, String producto, float comision) {
		super();
		this.vendedor = vendedor;
		this.producto = producto;
		this.comision = comision;
	}
	public ComisionCampania() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
