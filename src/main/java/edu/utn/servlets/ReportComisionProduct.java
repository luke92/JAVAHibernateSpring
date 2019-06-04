package edu.utn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IProductoDao;
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Producto;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.domain.Venta;
import edu.utn.transferObjects.ComisionXVentas;

public class ReportComisionProduct  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	public IProductoDao productoDao;
	
	public ReportComisionProduct(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		this.productoDao = (IProductoDao) ctx.getBean("ProductoDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			List<Producto> productos = productoDao.getAll();
			
			
			request.setAttribute("productos", productos);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/ReportComisionProduct.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			try
			{
				
				List<Producto> productos = productoDao.getAll();
				
				List<Usuario> vendedores = usuarioDao.getVendedores();
				
				Integer id = (Integer.parseInt(request.getParameter("idProducto")));
				Producto p = productoDao.getById(id);
				List<ComisionXVentas> listCXV = getComisionVendedoresProducto(vendedores, p);
				
				request.setAttribute("producto", p);
				request.setAttribute("productos", productos);
				request.setAttribute("comisiones", listCXV);
		
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				request.getRequestDispatcher("/ReportComisionProduct.jsp").forward(request,response);
			}
		
	}
	
	private List<ComisionXVentas> getComisionVendedoresProducto(List<Usuario> vendedores, Producto p)
	{
		List<ComisionXVentas> listCXV = new ArrayList<ComisionXVentas>();
		int cantidad;
		for(Usuario x : vendedores)
		{
			cantidad = 0;
			
			for(Venta venta : x.getVentas())
			{
				if(venta.getProducto().getId() == p.getId())
				{
					cantidad += venta.getCantidad();
				}
			}
			
			if(cantidad > 0) 
				listCXV.add(new ComisionXVentas(x.getNombre() + " " + x.getApellido()
										, cantidad, cantidad * p.getAdicional())); 
			
			
			
		}
		
		return listCXV;
		
		
	}
	
}
