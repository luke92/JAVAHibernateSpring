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

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao dataAccess;
	public IUsuarioService service;
	
	public Login() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.dataAccess = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		//this.service = (IUsuarioService) ctx.getBean("UsuarioServiceBean");
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Sesion session = new Sesion(request.getSession());
			
			int dni = Integer.parseInt(request.getParameter("dni"));
			String password = request.getParameter("password");
			Usuario usuario = new Usuario();
			
			usuario = dataAccess.getByDni(dni);
			if(usuario.getContrasenia().compareTo(password) == 0)
			{
				session.Login(usuario);
				request.setAttribute("mensaje", Mensaje.Exito("Logueo correcto"));
			}
			else
			{
				request.setAttribute("mensaje", Mensaje.Error("Datos invalidos"));
			}
			
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al iniciar sesion"));
		}
		finally
		{
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
	}


	public void setDataAccess(IUsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IUsuarioDao getDataAccess() {
		return dataAccess;
	}


}
