package edu.utn.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IPremioDao;
import edu.utn.domain.Premio;
import edu.utn.util.Mensaje;

public class NewPremio extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IPremioDao dataAccess;
	
	public void setDataAccess(IPremioDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IPremioDao getDataAccess() {
		return dataAccess;
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.dataAccess = (IPremioDao) ctx.getBean("PremioDaoBean");
		
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Premio p = new Premio();
			p.setNombre(request.getParameter("nombre"));
			p.setMonto(Integer.parseInt(request.getParameter("monto")));
			dataAccess.insert(p);
			request.setAttribute("mensaje", Mensaje.Exito("Premio Agregado"));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al agregar el Premio"));
		}
		finally
		{
			request.getRequestDispatcher("/NewPremio.jsp").forward(request,response);
		}
	}

}

