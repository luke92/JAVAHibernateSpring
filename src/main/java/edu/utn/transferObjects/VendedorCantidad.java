package edu.utn.transferObjects;

public class VendedorCantidad {
	
	private String vendedor;
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
	public VendedorCantidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendedorCantidad(Integer dni, Integer cantidad, String vendedor) {
		super();
		this.dni = dni;
		this.cantidad = cantidad;
		this.vendedor = vendedor;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
	

}
