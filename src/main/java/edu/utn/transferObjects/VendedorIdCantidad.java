package edu.utn.transferObjects;

public class VendedorIdCantidad {
	
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
	public VendedorIdCantidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendedorIdCantidad(Integer dni, Integer cantidad) {
		super();
		this.dni = dni;
		this.cantidad = cantidad;
	}
}
