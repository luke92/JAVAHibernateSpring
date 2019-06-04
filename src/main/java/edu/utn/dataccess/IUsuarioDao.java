package edu.utn.dataccess;

import java.util.ArrayList;
import java.util.List;

import edu.utn.domain.Usuario;

public interface IUsuarioDao {
	
	//Alta de persona
		public void insert(Usuario usuario);

		//Obtiene una persona por dni
		public Usuario getByDni(Integer dni);

		//Obtiene todas las presonas
		public ArrayList<Usuario> getAll();

		//Elimina una presona a aprtir del dni
		public void delete(Integer dni);

		//Actualiza los datos de una persona
		public void update(Usuario usuario);
		
		public List<Usuario> getVendedores();

}
