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

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.utn.dataccess.IPremioDao;
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Premio;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.transferObjects.ComisionXVentas;
import edu.utn.util.ComparadorVendedor;

public class BestVendor  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	public IPremioDao premioDao;
	
	public BestVendor(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		this.premioDao = (IPremioDao) ctx.getBean("PremioDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{	
			List<Usuario> vendedores = usuarioDao.getVendedores();
			List<Premio> premios = premioDao.getAll();
			
			ComisionXVentas CXV = getComisionVendedores(vendedores, premios);
			
			request.setAttribute("comision", CXV);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/BestVendor.jsp").forward(request,response);
		}
		
	}

	private ComisionXVentas getComisionVendedores(List<Usuario> vendedores, List<Premio> premios)
	{
		List<ComisionXVentas> listCXV = new ArrayList<ComisionXVentas>();
		
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
	
}
