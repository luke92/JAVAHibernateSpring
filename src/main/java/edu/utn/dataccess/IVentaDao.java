package edu.utn.dataccess;

import java.util.ArrayList;
import java.util.Date;

import edu.utn.domain.Venta;

public interface IVentaDao {
	
	public void insert(Venta venta);
	
	public Venta getById(Integer id);
	
	public ArrayList<Venta> getAll();
	
	public void delete (Integer id);
	
	public ArrayList<Venta> getVentasEntreFechas(Date fechaInicio, Date fechaFin);
}
