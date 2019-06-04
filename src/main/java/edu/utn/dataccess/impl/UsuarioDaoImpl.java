package edu.utn.dataccess.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.utn.dataccess.IUsuarioDao;
import edu.utn.domain.Rol;
import edu.utn.domain.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Usuario usuario) {
		this.hibernateTemplate.save(usuario);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Usuario getByDni(Integer dni) {
		return this.hibernateTemplate.get(Usuario.class, dni);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Usuario> getAll() {
		return (ArrayList<Usuario>) this.hibernateTemplate.loadAll(Usuario.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(Integer dni) {
		Usuario usuario = new Usuario();
		usuario.setDni(dni);
		this.hibernateTemplate.delete(usuario);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Usuario usuario) {
		this.hibernateTemplate.update(usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getVendedores()
	{
		return (List<Usuario>) this.hibernateTemplate.find("from edu.utn.domain.Usuario p where p.tipoUsuario = '" 
				+ Rol.Vendedor + "'");
	}

}
