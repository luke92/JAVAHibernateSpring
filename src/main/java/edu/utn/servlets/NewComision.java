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

public class NewComision extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IComisionDao dataAccess;
	
	public void setDataAccess(IComisionDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IComisionDao getDataAccess() {
		return dataAccess;
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.dataAccess = (IComisionDao) ctx.getBean("ComisionDaoBean");
		
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Comision p = new Comision();
			p.setMinimo(Integer.parseInt(request.getParameter("minimo")));
			p.setMaximo(Integer.parseInt(request.getParameter("maximo")));
			p.setMonto(Integer.parseInt(request.getParameter("adicional")));
			dataAccess.insert(p);
			request.setAttribute("mensaje", Mensaje.Exito("Comision Agregada"));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al agregar la comision"));
		}
		finally
		{
			request.getRequestDispatcher("/NewComision.jsp").forward(request,response);
		}
	}

}
