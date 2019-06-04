package edu.utn.dataccess.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.utn.dataccess.IPremioDao;
import edu.utn.domain.Premio;

public class PremioDaoImpl implements IPremioDao {
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Premio getById(Integer id) {
		return this.hibernateTemplate.get(Premio.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Premio> getAll() {
		return (ArrayList<Premio>) this.hibernateTemplate.loadAll(Premio.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Premio premio) {
		this.hibernateTemplate.update(premio);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Premio premio) {
		this.hibernateTemplate.save(premio);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		Premio p = getById(id);
		this.hibernateTemplate.delete(p);
		
	}

}
