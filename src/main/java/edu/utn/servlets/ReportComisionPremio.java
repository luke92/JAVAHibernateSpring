package edu.utn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IComisionDao;
import edu.utn.dataccess.IPremioDao;
import edu.utn.dataccess.IProductoDao;
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.dataccess.IVentaDao;
import edu.utn.dataccess.impl.ProductoDaoImpl;
import edu.utn.domain.Comision;
import edu.utn.domain.Premio;
import edu.utn.domain.Producto;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.domain.Venta;
import edu.utn.transferObjects.ComisionCampania;
import edu.utn.transferObjects.ComisionProducto;
import edu.utn.transferObjects.ComisionXVentas;
import edu.utn.transferObjects.VendedorCantidad;
import edu.utn.util.ComparadorMejorVendedor;
import edu.utn.util.ComparadorVendedor;
import edu.utn.util.ComparadorVendedorCantidad;
import edu.utn.util.Fecha;

public class ReportComisionPremio extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	public IComisionDao comisionDao;
	public IPremioDao premioDao;
	public IProductoDao productoDao;
	private IVentaDao ventaDao;
	
	public ReportComisionPremio(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		this.comisionDao = (IComisionDao) ctx.getBean("ComisionDaoBean");
		this.premioDao = (IPremioDao) ctx.getBean("PremioDaoBean");
		this.productoDao = (IProductoDao) ctx.getBean("ProductoDaoBean");
		this.ventaDao = (IVentaDao) ctx.getBean("VentaDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{	
			List<Usuario> vendedores = usuarioDao.getVendedores();
			
			request.setAttribute("vendedores", vendedores);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/ReportComisionPremio.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			Date fechaInicio = Fecha.String_a_Fecha(request.getParameter("fechaInicio"));
			Date fechaFin = Fecha.String_a_Fecha(request.getParameter("fechaFin"));
			
			List<Venta> ventas = ventaDao.getVentasEntreFechas(fechaInicio, fechaFin);
			List<Producto> productos = productoDao.getAll();
			List<Comision> comisiones = comisionDao.getAll();
			List<Premio> premios = premioDao.getAll();
			
			List<Usuario> vendedores = usuarioDao.getVendedores();
			request.setAttribute("vendedores", vendedores);
			
			ComisionXVentas CXV = getVendedorMes(vendedores, premios, ventas);
			request.setAttribute("mejorVendedorMes", CXV);
			
			List<ComisionCampania> campanias = getMejorVendedorCampanias(vendedores, premios, productos);
			request.setAttribute("mejorVendedorCampanias", campanias);
			
			if(request.getParameterValues("vendedores") != null)
			{
				List<Usuario> vendedoresSeleccionados = getVendedoresSeleccionados(vendedores, request.getParameterValues("vendedores"));
				List<ComisionXVentas> comisionesxVtas = getComisionVendedores(vendedoresSeleccionados, comisiones);
				request.setAttribute("comisionesPorVentas", comisionesxVtas);
				
				List<ComisionProducto> comisionesPorProductos = getComisionesPorProductos(vendedoresSeleccionados, productos);
				request.setAttribute("comisionesPorProductos", comisionesPorProductos);
			}
			
			
			
			
			
		}
		catch(Exception ex)
		{
			request.setAttribute("mensaje", ex.getMessage());
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/ReportComisionPremio.jsp").forward(request,response);
		}
	}
	
	
	
	//OK
	private ComisionXVentas getVendedorMes(List<Usuario> vendedores, List<Premio> premios, List<Venta> ventas)
	{
		
		Collections.sort(vendedores, new ComparadorVendedor());
		
		for(Premio p : premios)
		{
			if(p.getNombre().compareTo("Mejor Vendedor del Mes") == 0)
			{
				
				ComisionXVentas cxv = new ComisionXVentas();
				cxv.setVendedor(vendedores.get(0).getNombre() + " " + vendedores.get(0).getApellido());
				cxv.setCantidadVentas(vendedores.get(0).getVentas().size());
				cxv.setComision(p.getMonto());
				return cxv;
			}
		}
		return null;
	
	}
	
	//OK
	private List<ComisionXVentas> getComisionVendedores(List<Usuario> vendedores, List<Comision> comisiones)
	{
		List<ComisionXVentas> listCXV = new ArrayList<ComisionXVentas>();
		for(Usuario x : vendedores)
		{
			for(Comision c : comisiones)
			{
				if(x.getVentas().size() >= c.getMinimo() && x.getVentas().size() <= c.getMaximo())
				{
					listCXV.add(new ComisionXVentas(x.getNombre() + " " + x.getApellido(),
							x.getVentas().size(), c.getMonto()));
					break;
				}
			}
			
		}
		
		return listCXV;
		
		
	}
	
	
	//OK
	private List<ComisionCampania> getMejorVendedorCampanias(List<Usuario> vendedores, List<Premio> premios, List<Producto> productos)
	{
		float premio = 0;
		for(Premio x : premios)
		{
			System.out.println("paso-2");
			if(x.getNombre().compareTo("Mejor Vendedor de una Campania") == 0)
			{
				premio = x.getMonto();
			}
		}
			
		List<ComisionCampania> listcpc = new ArrayList<ComisionCampania>();
		
		List<VendedorCantidad> vendedoresDNI = new ArrayList<VendedorCantidad>();
		
		System.out.println("paso-1");
		
		for(Usuario x : vendedores)
		{
			vendedoresDNI.add(new VendedorCantidad(x.getDni(),0, x.getNombre() + " " + x.getApellido()));
			System.out.println("paso0");
		}
		
		for(Producto producto : productos)
		{
			System.out.println("paso1");
			for(Venta x: producto.getVentas())
			{
				System.out.println("paso2");
				for(int i = 0; i < vendedoresDNI.size(); i++)
				{
					if(vendedoresDNI.get(i).getDni() == x.getVendedor().getDni())
					{
						System.out.println("paso4");
						vendedoresDNI.get(i).setCantidad(vendedoresDNI.get(i).getCantidad() + x.getCantidad());
						break;
					}
				}
			}
			
			Collections.sort(vendedoresDNI, new ComparadorVendedorCantidad());
			listcpc.add(new ComisionCampania(vendedoresDNI.get(0).getVendedor(),producto.getDescripcion(),premio));
			
			for(VendedorCantidad v : vendedoresDNI)
			v.setCantidad(0);
			
		}
		
		return listcpc;
	}
	
	
	private List<ComisionProducto> getComisionesPorProductos(List<Usuario> vendedores, List<Producto> productos)
	{
		List<ComisionProducto> comisiones = new ArrayList<ComisionProducto>();
		int cantidad = 0;
		float comision = 0;
			
		for(Usuario x : vendedores)
		{
			for(Producto p : productos)
			{
				if(p.getAdicional() > 0)
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
					comisiones.add(new ComisionProducto(x.getNombre() + " " + x.getApellido(),
								p.getDescripcion(), p.getAdicional(), cantidad, cantidad * p.getAdicional())); 	
				}
			}
		}
		
		
		
		return comisiones;		
	}
	

	//LISTO
	private List<Usuario> getVendedoresSeleccionados(List<Usuario> vendedores, String[] vendedoresInput)
	{
		List<Usuario> vendedoresSeleccionados = new ArrayList<Usuario>();
		for(Usuario x : vendedores)
		{
			for(String y : vendedoresInput)
			{
				if(y.compareTo(x.getDni().toString()) == 0)
				{
					vendedoresSeleccionados.add(x);
					break;
				}
					
			}
		}
		return vendedoresSeleccionados;
	}
	

	
}
