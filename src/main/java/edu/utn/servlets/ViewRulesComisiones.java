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

import edu.utn.dataccess.IComisionDao;
import edu.utn.domain.Comision;


public class ViewRulesComisiones extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IComisionDao comisionDao;
	
	public ViewRulesComisiones(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.comisionDao = (IComisionDao) ctx.getBean("ComisionDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
			List<Comision> comisiones = comisionDao.getAll();
			if(comisiones.size() > 0)
			request.setAttribute("comisiones", comisiones);
			else
				request.setAttribute("mensaje", "No hay comisiones cargadas");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", "Hubo un error al cargar la lista de comisiones");
		}
		finally
		{
			request.getRequestDispatcher("/ViewRulesComisiones.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}
