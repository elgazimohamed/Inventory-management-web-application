package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        /*
         * Load up the configuration using the hibernate.cfg.xml
         */
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        /*
         * Build the registry using the properties in the configuration
         */
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        
        /*
         * Now load the classes
         */
     
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    /**
     * The main utility method to be used to retreive the transaction.
     *
     * @return {@link Transaction} The transaction of the current session
     */
    public static Transaction getTransaction() throws Exception {
        Session s = getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        tx.setTimeout(10);
        return tx;
        //return getSessionFactory().getCurrentSession().beginTransaction();;
    }

}

/*public static final SessionFactory sessionFactory ;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();				
		}
		catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation Failed."+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}*/