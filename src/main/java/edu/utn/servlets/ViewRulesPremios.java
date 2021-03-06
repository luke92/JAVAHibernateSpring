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

import edu.utn.dataccess.IPremioDao;
import edu.utn.domain.Premio;

public class ViewRulesPremios extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IPremioDao premioDao;
	
	public ViewRulesPremios(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.premioDao = (IPremioDao) ctx.getBean("PremioDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
			List<Premio> premios = premioDao.getAll();
			if(premios.size() > 0)
			request.setAttribute("premios", premios);
			else
				request.setAttribute("mensaje", "No hay premios cargados");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", "Hubo un error al cargar la lista de premios");
		}
		finally
		{
			request.getRequestDispatcher("/ViewRulesPremios.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
