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


public class NewSale extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IProductoDao productoDao;
	public IVentaDao ventaDao;
	public IUsuarioDao usuarioDao;
	
	public NewSale() {
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.productoDao = (IProductoDao) ctx.getBean("ProductoDaoBean");
		this.ventaDao = (IVentaDao) ctx.getBean("VentaDaoBean");
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			request.setAttribute("productos", productoDao.getAll());
			Integer nroFactura = Integer.parseInt(request.getParameter("nroFactura"));
			Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			
			Venta venta = new Venta();
			Sesion sesion = new Sesion(request.getSession());
			
			Producto p = productoDao.getById(idProducto);
			Usuario u = usuarioDao.getByDni(sesion.getDni());
			
			if(ventaDao.getById(nroFactura) == null)
			{
				venta.setId(nroFactura);
				venta.setFecha(Fecha.String_a_Fecha(request.getParameter("fecha")));
				venta.setProducto(p);
				venta.setVendedor(u);
				venta.setCantidad(cantidad);
				this.ventaDao.insert(venta);
				request.setAttribute("mensaje", Mensaje.Exito("Se ha registrado la venta correctamente"));
			}
			else
			{
				request.setAttribute("mensaje", Mensaje.Error("La venta con factura nº " + nroFactura + " ya fue cargada Anteriormente"));
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			request.setAttribute("mensaje", Mensaje.Error("Hubo un error al agregar la venta"));
		}
		finally
		{
			request.getRequestDispatcher("/NewSale.jsp").forward(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("productos", productoDao.getAll());
		request.getRequestDispatcher("/NewSale.jsp").forward(request,response);
	}

	public IProductoDao getProductoDao() {
		return productoDao;
	}

	public void setProductoDao(IProductoDao productoDao) {
		this.productoDao = productoDao;
	}

	public IVentaDao getVentaDao() {
		return ventaDao;
	}

	public void setVentaDao(IVentaDao ventaDao) {
		this.ventaDao = ventaDao;
	}

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	


}
