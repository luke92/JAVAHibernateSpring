package edu.utn.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Usuario;

public class ViewUsers extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	
	public ViewUsers(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
			List<Usuario> usuarios = usuarioDao.getAll();
			if(usuarios.size() > 0)
			request.setAttribute("usuarios", usuarios);
			else
				request.setAttribute("mensaje", "No hay usuarios");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", "Hubo un error al cargar la lista de usuarios");
		}
		finally
		{
			request.getRequestDispatcher("/ViewUsers.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
