package edu.utn.transferObjects;

public class VendedorCantidadProducto {
	
	private Integer producto;
	private Integer dni;
	private Integer cantidad;
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public VendedorCantidadProducto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendedorCantidadProducto(Integer dni, Integer producto, Integer cantidad) {
		super();
		this.dni = dni;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
}
