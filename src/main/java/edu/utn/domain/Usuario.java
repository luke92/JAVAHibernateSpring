package edu.utn.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuarios" )
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column (name="nombre")
	private String nombre;
	@Column (name="apellido")
	private String apellido;
	@Id
	@Column (name="dni")
	private Integer dni;
	
	@Column (name="contrasenia")
	private String contrasenia;
	
	@Column (name="tipoUsuario")
	@Enumerated(EnumType.STRING)
	private Rol tipoUsuario;
	
	@OneToMany (cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="vendedor")
	@ElementCollection
	private List<Venta> ventas;
	
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Rol getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Rol tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public Usuario(){}
	
}
