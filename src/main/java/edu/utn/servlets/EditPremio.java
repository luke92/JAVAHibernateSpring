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

public class EditPremio extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IPremioDao premioDao;
	
	public EditPremio() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.premioDao = (IPremioDao) ctx.getBean("PremioDaoBean");

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Premio p = premioDao.getById(id);
			
			p.setMonto(Float.parseFloat(request.getParameter("monto")));
			premioDao.update(p);
			request.setAttribute("mensaje", Mensaje.Exito("Se actualizo el premio con exito"));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al modificar el premio"));
		}
		finally
		{
			request.getRequestDispatcher("/EditPremio.jsp").forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("rbId"));
		
		Premio p = premioDao.getById(id);
		
		request.setAttribute("premio", p);
		request.getRequestDispatcher("/EditPremio.jsp").forward(request,response);
	}

	

	
	


}
