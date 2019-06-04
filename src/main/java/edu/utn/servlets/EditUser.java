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
import edu.utn.domain.Producto;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.util.Mensaje;

public class EditUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao dataAccess;
	
	public EditUser() {
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
			usuario = dataAccess.getByDni(Integer.parseInt(request.getParameter("dni")));
	
			usuario.setApellido(request.getParameter("apellido"));
			usuario.setNombre(request.getParameter("nombre"));
			usuario.setContrasenia(request.getParameter("password"));
			int rol = Integer.parseInt(request.getParameter("tipoUsuario"));
			if(rol == 1) usuario.setTipoUsuario(Rol.Vendedor);
			if(rol == 2) usuario.setTipoUsuario(Rol.RRHH);
			if(rol == 3) usuario.setTipoUsuario(Rol.Administrador);
			
			dataAccess.update(usuario);
			request.setAttribute("mensaje", Mensaje.Exito("Usuario modificado"));
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al modificar el usuario"));
		}
		finally
		{
			request.getRequestDispatcher("/EditUser.jsp").forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("rbDni"));
		
		Usuario p = dataAccess.getByDni(id);
		
		request.setAttribute("usuario", p);
		request.getRequestDispatcher("/EditUser.jsp").forward(request,response);
	}


	public void setDataAccess(IUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IUsuarioDao getDataAccess() {
		return dataAccess;
	}


}
