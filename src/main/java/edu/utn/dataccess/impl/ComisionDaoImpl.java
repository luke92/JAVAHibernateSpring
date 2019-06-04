package edu.utn.dataccess.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.utn.dataccess.IComisionDao;
import edu.utn.domain.Comision;

public class ComisionDaoImpl implements IComisionDao {
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Comision getById(Integer id) {
		return this.hibernateTemplate.get(Comision.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Comision> getAll() {
		return (ArrayList<Comision>) this.hibernateTemplate.loadAll(Comision.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Comision comision) {
		this.hibernateTemplate.update(comision);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(Comision comision) {
		this.hibernateTemplate.save(comision);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		Comision c = getById(id);
		this.hibernateTemplate.delete(c);
		
	}

}
