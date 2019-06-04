package edu.utn.dataccess.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.utn.dataccess.IVentaDao;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;
import edu.utn.domain.Venta;
import edu.utn.transferObjects.VendedorCantidad;
import edu.utn.transferObjects.VendedorCantidadProducto;
import edu.utn.transferObjects.VendedorIdCantidad;
import edu.utn.util.Fecha;

public class VentaDaoImpl implements IVentaDao {
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Venta venta) {
		this.hibernateTemplate.save(venta);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Venta getById(Integer id) {
		return this.hibernateTemplate.get(Venta.class,id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Venta> getAll() {
		return (ArrayList<Venta>) this.hibernateTemplate.loadAll(Venta.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		Venta v = getById(id);
		this.hibernateTemplate.delete(v);
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Venta> getVentasEntreFechas(Date fechaInicio, Date fechaFin)
	{
		String fechaI = Fecha.Fecha_a_String(fechaInicio);
		String fechaF = Fecha.Fecha_a_String(fechaFin);
		return (ArrayList<Venta>) this.hibernateTemplate.find("from edu.utn.domain.Venta v where v.fecha BETWEEN '" 
						+ fechaI + "' AND '" + fechaF + "'"); 
				
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<VendedorCantidad> getVentaTotalVendedores()
	{
		return (ArrayList<VendedorCantidad>) this.hibernateTemplate.find("select vendedor, COUNT(*) from edu.utn.domain.Venta v group by vendedor"); 
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<VendedorIdCantidad> getVentaTotalVendedoresEntreFechas(Date fechaInicio, Date fechaFin)
	{
		String fechaI = Fecha.Fecha_a_String(fechaInicio);
		String fechaF = Fecha.Fecha_a_String(fechaFin);
		return (ArrayList<VendedorIdCantidad>) this.hibernateTemplate.find
				("select vendedor, COUNT(*) from edu.utn.domain.Venta v " +
						"where (v.fecha between " + "'" + fechaI + "'" + " AND" + "'" + fechaF + "')" +
						" group by vendedor"); 
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<VendedorCantidadProducto> getVentaVendedoresPorProductoEntreFechas(Date fechaInicio, Date fechaFin)
	{
		String fechaI = Fecha.Fecha_a_String(fechaInicio);
		String fechaF = Fecha.Fecha_a_String(fechaFin);
		return (ArrayList<VendedorCantidadProducto>) this.hibernateTemplate.find
				("select vendedor, producto, SUM(cantidad) from edu.utn.domain.Venta v " +
						"where (v.fecha between " + "'" + fechaI + "'" + " AND" + "'" + fechaF + "')" +
						" group by vendedor, producto"); 
	}
	
	
	
	

}
