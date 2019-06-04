package edu.utn.dataccess;

import java.util.ArrayList;

import edu.utn.domain.Producto;

public interface IProductoDao {
	
	public Producto getById(Integer id);
	
	public ArrayList<Producto> getAll();
	
	public void update (Producto producto);
	
	public void insert (Producto producto);
	
	public void delete (Integer id);
}
