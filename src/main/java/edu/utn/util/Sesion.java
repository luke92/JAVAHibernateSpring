package edu.utn.util;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;

public class Sesion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	public Sesion(HttpSession session)
	{
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public boolean isLogged()
	{
		if(this.getSession().getAttribute("userDni") == null)
			return false;
		return true;
	}
	
	public Integer getDni()
	{
		return (Integer)this.getSession().getAttribute("userDni");
	}
	
	public void Login(Usuario u)
	{
		this.getSession().setAttribute("userDni", u.getDni());
		this.getSession().setAttribute("userName", u.getNombre());
		this.getSession().setAttribute("userLastName", u.getApellido());
		this.getSession().setAttribute("userRol", u.getTipoUsuario().toString());
		
	}
	
	public boolean isAdmin()
	{
		if(isLogged())
		{
			if(this.getSession().getAttribute("userRol").toString().compareTo(Rol.Administrador.toString()) == 0)
				return true;
		}
		return false;
	}
	
	public boolean isRRHH()
	{
		if(isLogged())
		{
			if(this.getSession().getAttribute("userRol").toString().compareTo(Rol.RRHH.toString()) == 0)
				return true;
		}
		return false;
	}
	
	public boolean isVendor()
	{
		if(isLogged())
		{
			if(this.getSession().getAttribute("userRol").toString().compareTo(Rol.Vendedor.toString()) == 0)
				return true;
		}
		return false;
	}
	
	public int getNivelRol()
	{
		if(isLogged())
		{
			if(isAdmin()) return 3;
			if(isRRHH()) return 2;
			if(isVendor()) return 1;
		}
		return 0;
	}

}
