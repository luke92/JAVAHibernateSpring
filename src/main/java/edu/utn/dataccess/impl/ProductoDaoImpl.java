package edu.utn.dataccess.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.utn.dataccess.IProductoDao;
import edu.utn.domain.Producto;

public class ProductoDaoImpl implements IProductoDao {
	
	private HibernateTemplate hibernateTemplate = null;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Producto getById(Integer id) {
		return this.hibernateTemplate.get(Producto.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Producto> getAll() {
		return (ArrayList<Producto>) this.hibernateTemplate.loadAll(Producto.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Producto producto) {
		this.hibernateTemplate.update(producto);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Producto producto) {
		this.hibernateTemplate.save(producto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		Producto producto = getById(id);
		this.hibernateTemplate.delete(producto);
		
	}
	
	
	
}
