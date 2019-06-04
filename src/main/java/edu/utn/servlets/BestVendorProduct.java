package edu.utn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Sort;
import org.junit.runner.manipulation.Sortable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IPremioDao;
import edu.utn.dataccess.IProductoDao;
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Premio;
import edu.utn.domain.Producto;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.domain.Venta;
import edu.utn.transferObjects.ComisionXVentas;
import edu.utn.util.ComparadorMejorVendedor;
import edu.utn.util.ComparadorVendedor;

public class BestVendorProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	public IPremioDao premioDao;
	public IProductoDao productoDao;
	
	public BestVendorProduct(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		this.premioDao = (IPremioDao) ctx.getBean("PremioDaoBean");
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
			request.getRequestDispatcher("/BestVendorProduct.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			List<Producto> productos = productoDao.getAll();
			List<Usuario> vendedores = usuarioDao.getVendedores();
			List<Premio> premios = premioDao.getAll();
			
			Integer id = (Integer.parseInt(request.getParameter("idProducto")));
			Producto p = productoDao.getById(id);
			
			ComisionXVentas CXV = getComisionVendedoresProducto(vendedores, premios, p);
			
			request.setAttribute("productos", productos);
			request.setAttribute("comision", CXV);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/BestVendorProduct.jsp").forward(request,response);
		}
		
	}
	
	

	private ComisionXVentas getComisionVendedoresProducto(List<Usuario> vendedores, List<Premio> premios, Producto p)
	{
		List<ComisionXVentas>  listcxv = new ArrayList<ComisionXVentas>();
		
		List<Integer> cantidades = new ArrayList<Integer>();
		
		for(Premio premio : premios)
		{
			if(premio.getNombre().compareTo("Mejor Vendedor de una Campania") == 0)
			{
				for(Usuario v : vendedores)
				{
					int cantidad = 0;
					for(Venta venta : v.getVentas())
					{
						if(venta.getProducto().getId() == p.getId())
							cantidad++;
					}
					
					listcxv.add(new ComisionXVentas(v.getNombre() + " " + v.getApellido(), cantidad, premio.getMonto()));
					
				}
				
				Collections.sort(listcxv, new ComparadorMejorVendedor());
				return listcxv.get(0);	 
			}
		}
		
		return null;
	}
	
}
