package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

import models.ArticlesApprovisionnement;
import util.HibernateUtil;

public class ApprovisionementDAO {

	public void addArticleApprovisionment(ArticlesApprovisionnement apro) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		try {
			session.save(apro);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();	
	}

	public List<ArticlesApprovisionnement> ListApprovisionnement() {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();

		session.beginTransaction();
		
		Query req = session.createQuery("SELECT a FROM ArticlesApprovisionnement a");
		List<ArticlesApprovisionnement> articleApro = req.list();
		
		session.getTransaction().commit();
		return articleApro;
	}

	public ArticlesApprovisionnement getArticleArticlesApprovisionnement(Long codeArt) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
			
		session.beginTransaction();
		Object apro = session.get(ArticlesApprovisionnement.class,codeArt);
		if(apro==null) throw new RuntimeException("Article introvable");
		session.getTransaction().commit();
		return (ArticlesApprovisionnement) apro;
	}

	
	public void deleteArticle(ArticlesApprovisionnement apro) {
	
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
	
		session.delete(apro);
		session.getTransaction().commit();
	}


	public void updateArticle(ArticlesApprovisionnement apro) {

		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		session.update(apro);
		session.getTransaction().commit();
		
	}
}

