/**
 * 
 */
package edu.utn.dataccess.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


@SuppressWarnings("deprecation")
public class HIbernateUtil {

	private static final SessionFactory sessionFactory;
    
	static {
        try {
            sessionFactory = new AnnotationConfiguration()
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
    }
	
	public static void shutdown() {
		sessionFactory.close();
	}
	
}
