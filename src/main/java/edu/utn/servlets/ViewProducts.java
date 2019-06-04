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

import edu.utn.dataccess.IProductoDao;
import edu.utn.domain.Producto;

public class ViewProducts extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IProductoDao productoDao;
	
	public ViewProducts(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.productoDao = (IProductoDao) ctx.getBean("ProductoDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
			List<Producto> productos = productoDao.getAll();
			if(productos.size() > 0)
			request.setAttribute("productos", productos);
			else
				request.setAttribute("mensaje", "No hay productos");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", "Hubo un error al cargar la lista de productos");
		}
		finally
		{
			request.getRequestDispatcher("/ViewProducts.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try
		{
			String opcion = request.getParameter("opcion");
			String id = request.getParameter("rbId");
			
			if(opcion.compareTo("delete") == 0)
			{
				productoDao.delete(Integer.parseInt(id));
				List<Producto> productos = productoDao.getAll();
				if(productos.size() > 0)
				request.setAttribute("productos", productos);
				else
					request.setAttribute("mensaje", "No hay productos");
				request.setAttribute("mensaje", "Se borro con exito el producto");
				
				request.getRequestDispatcher("/ViewProducts.jsp").forward(request,response);
					
				
			}
			if(opcion.compareTo("edit") == 0)
			{
				
				response.sendRedirect("EditProduct?id=" + id);

			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", "Hubo un error al cargar la peticion solicitada");
			request.getRequestDispatcher("/ViewProducts.jsp").forward(request,response);
		}
		
		
	}
	
	
}
