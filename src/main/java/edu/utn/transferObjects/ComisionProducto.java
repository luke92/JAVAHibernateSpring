package edu.utn.transferObjects;

import java.io.Serializable;

public class ComisionProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vendedor;
	private String producto;
	private float adicional;
	private Integer cantidadVentas;
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
	public float getAdicional() {
		return adicional;
	}
	public void setAdicional(float adicional) {
		this.adicional = adicional;
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
	public ComisionProducto(String vendedor, String producto, float adicional,
			Integer cantidadVentas, float comision) {
		super();
		this.vendedor = vendedor;
		this.producto = producto;
		this.adicional = adicional;
		this.cantidadVentas = cantidadVentas;
		this.comision = comision;
	}
	public ComisionProducto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
