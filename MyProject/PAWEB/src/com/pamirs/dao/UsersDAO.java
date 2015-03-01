package com.pamirs.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pamirs.model.Users;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.pamirs.dao.Users
 * @author MyEclipse Persistence Tools
 */

@Repository
public class UsersDAO extends HibernateBasicTemplate {
	private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL_ID = "emailId";
	public static final String SECURITY_QUESTION = "securityQuestion";
	public static final String SECURITY_ANSWER = "securityAnswer";

	public Integer save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
		Integer in =(Integer)geteSession().save(transientInstance);
			log.debug("save successful");
			return in;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			return 0;
			 
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.Integer id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) geteSession().get("com.pamirs.model.Users",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List<Users> results = (List<Users>) getSession()
					.createCriteria("com.pamirs.dao.Users")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Integer updatePassword(String pwd ,String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "update Users as model set password=? where model."
					+ propertyName + "= ?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, pwd);
			queryObject.setParameter(1, value);
			return queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Integer updateProfile(String fname ,String lname,String email, String propertyName , Object value) {
		log.debug("finding Users instance with property: " + lname
				+ ", value: " + value);
		try {
			String queryString = "update Users as model set firstName=?, lastName=?, emailId=? where model."
					+ propertyName + "= ?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, fname);
			queryObject.setParameter(1, lname);
			queryObject.setParameter(2, email);
			queryObject.setParameter(3, value);
			return queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Users> findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List<Users> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Users> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List<Users> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List<Users> findByEmailId(Object emailId) {
		return findByProperty(EMAIL_ID, emailId);
	}

	public List<Users> findBySecurityQuestion(Object securityQuestion) {
		return findByProperty(SECURITY_QUESTION, securityQuestion);
	}

	public List<Users> findBySecurityAnswer(Object securityAnswer) {
		return findByProperty(SECURITY_ANSWER, securityAnswer);
	}

	public List findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Users doLogin(String user,String pwd) {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users where userName=? and password=? ";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, user);
			queryObject.setParameter(1, pwd);
			
			List ls = queryObject.list();
			if(ls.size()>0){
				Users use = (Users)ls.get(0);
				return use;
			}else{
				return  null;
			}
			
		} catch (Exception re) {
			re.printStackTrace();
			log.error("find all failed", re);
			return null;
		 
		}
	}

	
	public Users getUser(String user) {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users where userName=? ";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, user);
		 
			
			List ls = queryObject.list();
			if(ls.size()>0){
				Users use = (Users)ls.get(0);
				return use;
			}else{
				return  null;
			}
			
		} catch (Exception re) {
			re.printStackTrace();
			log.error("find all failed", re);
			return null;
		 
		}
	}
	
	
	public Users checkSecurity(String user,String pwd) {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users where userName=? and securityAnswer=? ";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, user);
			queryObject.setParameter(1, pwd);
			
			List ls = queryObject.list();
			if(ls.size()>0){
				Users use = (Users)ls.get(0);
				return use;
			}else{
				return  null;
			}
			
		} catch (Exception re) {
			re.printStackTrace();
			log.error("find all failed", re);
			return null;
		 
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) geteSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}