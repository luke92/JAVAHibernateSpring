package edu.utn.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IComisionDao;
import edu.utn.domain.Comision;
import edu.utn.util.Mensaje;

public class EditComision extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IComisionDao comisionDao;
	
	public EditComision() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.comisionDao = (IComisionDao) ctx.getBean("ComisionDaoBean");

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Comision p = comisionDao.getById(id);
			
			p.setMonto(Float.parseFloat(request.getParameter("adicional")));
			comisionDao.update(p);
			request.setAttribute("mensaje", Mensaje.Exito("Se actualizo la comision con exito"));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al modificar la comision"));
		}
		finally
		{
			request.getRequestDispatcher("/EditComision.jsp").forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("rbId"));
		
		Comision p = comisionDao.getById(id);
		
		request.setAttribute("comision", p);
		request.getRequestDispatcher("/EditComision.jsp").forward(request,response);
	}

	

	
	


}
