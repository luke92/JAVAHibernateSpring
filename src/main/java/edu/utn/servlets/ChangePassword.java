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
import edu.utn.domain.Usuario;
import edu.utn.service.IUsuarioService;
import edu.utn.util.Mensaje;
import edu.utn.util.Sesion;

public class ChangePassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao dataAccess;
	
	public ChangePassword() {
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
			Sesion session = new Sesion(request.getSession());
			
			
			String passwordActual = request.getParameter("passwordActual");
			String passwordNueva = request.getParameter("passwordNueva");
			Usuario usuario = new Usuario();
			
			usuario = dataAccess.getByDni(session.getDni());
			if(usuario.getContrasenia().compareTo(passwordActual) == 0)
			{
				usuario.setContrasenia(passwordNueva);
				dataAccess.update(usuario);
				request.setAttribute("mensaje", Mensaje.Exito("Password Modificada correctamente"));
			}
			else
			{
				request.setAttribute("mensaje", Mensaje.Error("Contraseña Actual Invalida"));
			}
			
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al modificar la password"));
		}
		finally
		{
			request.getRequestDispatcher("/ChangePassword.jsp").forward(request,response);
		}
	}


	public void setDataAccess(IUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IUsuarioDao getDataAccess() {
		return dataAccess;
	}


}
