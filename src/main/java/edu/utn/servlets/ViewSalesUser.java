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

import edu.utn.util.Sesion;

public class ViewSalesUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	
	public ViewSalesUser(){}
	
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
			Sesion sesion = new Sesion(request.getSession());
			
			Usuario u = usuarioDao.getByDni(sesion.getDni());
			
			System.out.println(u.getVentas().size());
			
			request.setAttribute("ventas", u.getVentas());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/ViewSalesUser.jsp").forward(request,response);
		}
		
	}
	
}
