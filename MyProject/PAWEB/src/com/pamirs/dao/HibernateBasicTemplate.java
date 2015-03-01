package com.pamirs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
@Repository
public class HibernateBasicTemplate extends HibernateDaoSupport   {
	
	
	
	@Autowired
	public void init(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	
	
	public Session geteSession() {
		Session session =null;
		try{
		 	session = getSessionFactory().getCurrentSession();
		//	session = getSessionFactory().openSession();
		 
		}catch (Exception e){
					session = getSessionFactory().openSession();
		 e.printStackTrace();
	    }
		return  session;
	  }
	  public void closeSession(Session session){
		try{
			 if(session != null && session.isOpen()){
				  
		//		  releaseSession(session);
				 
			 }
		}catch (Exception e){
		 e.printStackTrace();
	    }
	
	  }
 
	
}