package edu.utn.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IProductoDao;
import edu.utn.domain.Producto;
import edu.utn.util.Mensaje;

public class NewProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IProductoDao dataAccess;
	
	public void setDataAccess(IProductoDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	
	public IProductoDao getDataAccess() {
		return dataAccess;
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.dataAccess = (IProductoDao) ctx.getBean("ProductoDaoBean");
		
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Producto p = new Producto();
			p.setDescripcion(request.getParameter("descripcion"));
			p.setAdicional(Float.parseFloat(request.getParameter("adicional")));
			dataAccess.insert(p);
			request.setAttribute("mensaje", Mensaje.Exito("Producto Agregado"));
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al agregar el producto"));
		}
		finally
		{
			request.getRequestDispatcher("/NewProduct.jsp").forward(request,response);
		}
	}

}
