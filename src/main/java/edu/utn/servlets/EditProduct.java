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
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.dataccess.IVentaDao;
import edu.utn.domain.Producto;
import edu.utn.domain.Usuario;
import edu.utn.domain.Venta;
import edu.utn.util.Fecha;
import edu.utn.util.Mensaje;
import edu.utn.util.Sesion;

public class EditProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IProductoDao productoDao;
	
	public EditProduct() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.productoDao = (IProductoDao) ctx.getBean("ProductoDaoBean");

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Producto p = productoDao.getById(id);
			
			p.setAdicional(Float.parseFloat(request.getParameter("adicional")));
			productoDao.update(p);
			request.setAttribute("mensaje", Mensaje.Exito("Se actualizo el producto con exito"));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al modificar el producto"));
		}
		finally
		{
			request.getRequestDispatcher("/EditProduct.jsp").forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Producto p = productoDao.getById(id);
		
		request.setAttribute("producto", p);
		request.getRequestDispatcher("/EditProduct.jsp").forward(request,response);
	}

	

	
	


}
