package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import models.ArticlesStock;
import util.HibernateUtil;

public class ArticleDAO {
	
	public void addArticle(ArticlesStock a) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		try {
			session.save(a);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}
	
	
	public List<ArticlesStock> ListArticle() {

		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		Query req = session.createQuery("SELECT a FROM ArticlesStock a");
		List<ArticlesStock> article = req.list();
		
		session.getTransaction().commit();
		return article;
	}

	public void updateArticle(ArticlesStock p) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		

		session.beginTransaction();
		
		session.update(p);
		session.getTransaction().commit();
		
	}
	
	public void deleteArticle(int codeArt) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		Object a = session.get(ArticlesStock.class, codeArt);
		if(a==null) throw new RuntimeException("Produit introuvable");
		session.delete(a);
		session.getTransaction().commit();
	}
	public void updateQteArticle(ArticlesStock a,int code,int b) {
		
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(srb.build());
		
		Session session = sf.openSession();

		session.beginTransaction();

		int nouveau_qte = a.getQteArt() - b;
		Query q = session.createSQLQuery("UPDATE articles_stock SET qteArt = '"+nouveau_qte+"' WHERE codeArt= '"+code+"' ");
        q.executeUpdate();

		session.getTransaction().commit();
	
	}



}
