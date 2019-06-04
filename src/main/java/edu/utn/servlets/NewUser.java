package edu.utn.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.service.IUsuarioService;
import edu.utn.util.Mensaje;

public class NewUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao dataAccess;
	public IUsuarioService service;
	
	public NewUser() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.dataAccess = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Usuario usuario = new Usuario();
			usuario.setDni(Integer.parseInt(request.getParameter("dni")));
			usuario.setApellido(request.getParameter("apellido"));
			usuario.setNombre(request.getParameter("nombre"));
			usuario.setContrasenia(request.getParameter("password"));
			int rol = Integer.parseInt(request.getParameter("tipoUsuario"));
			if(rol == 1) usuario.setTipoUsuario(Rol.Vendedor);
			if(rol == 2) usuario.setTipoUsuario(Rol.RRHH);
			if(rol == 3) usuario.setTipoUsuario(Rol.Administrador);
			
			dataAccess.insert(usuario);
			request.setAttribute("mensaje", Mensaje.Exito("Usuario Creado"));
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al crear el usuario"));
		}
		finally
		{
			request.getRequestDispatcher("/NewUser.jsp").forward(request,response);
		}
	}


	public void setDataAccess(IUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IUsuarioDao getDataAccess() {
		return dataAccess;
	}


}
