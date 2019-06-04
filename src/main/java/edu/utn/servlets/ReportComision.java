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

import edu.utn.dataccess.IComisionDao;
import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Comision;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.transferObjects.ComisionXVentas;

public class ReportComision  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public IUsuarioDao usuarioDao;
	public IComisionDao comisionDao;
	
	public ReportComision(){}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.usuarioDao = (IUsuarioDao) ctx.getBean("UsuarioDaoBean");
		this.comisionDao = (IComisionDao) ctx.getBean("ComisionDaoBean");
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{	
			List<Usuario> vendedores = usuarioDao.getVendedores();
			List<Comision> comisiones = comisionDao.getAll();
			
			List<ComisionXVentas> listCXV = getComisionVendedores(vendedores, comisiones);
			
			request.setAttribute("comisiones", listCXV);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			request.getRequestDispatcher("/ReportComision.jsp").forward(request,response);
		}
		
	}
	
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
	
}
