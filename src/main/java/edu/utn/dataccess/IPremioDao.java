package edu.utn.dataccess;

import java.util.ArrayList;

import edu.utn.domain.Premio;

public interface IPremioDao {
	
	public Premio getById(Integer id);
	
	public ArrayList<Premio> getAll();
	
	public void update (Premio premio);
	
	public void insert (Premio premio);
	
	public void delete (Integer id);

}
