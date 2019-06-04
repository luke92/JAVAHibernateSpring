package edu.utn.dataccess;

import java.util.ArrayList;

import edu.utn.domain.Comision;

public interface IComisionDao {
	
public Comision getById(Integer id);
	
	public ArrayList<Comision> getAll();
	
	public void update (Comision comision);
	
	public void insert (Comision comision);
	
	public void delete (Integer id);
}
