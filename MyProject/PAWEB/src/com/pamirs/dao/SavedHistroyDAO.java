package com.pamirs.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pamirs.model.SavedHistroy;

/**
 * A data access object (DAO) providing persistence and search support for
 * SavedHistroy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.pamirs.dao.SavedHistroy
 * @author MyEclipse Persistence Tools
 */

@Repository
public class SavedHistroyDAO extends HibernateBasicTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(SavedHistroyDAO.class);
	// property constants
	public static final String DATE_TIME = "dateTime";
	public static final String SAVED_NAME = "savedName";
	public static final String DOCS_HISTORY = "docsHistory";

	public Integer save(SavedHistroy transientInstance) {
		log.debug("saving SavedHistroy instance");
		try {
			Integer in = (Integer)geteSession().save(transientInstance);
			log.debug("save successful");
			return in;
		} catch (Exception re) {
			log.error("save failed", re);
		return 0;
		}
	}

	public void delete(SavedHistroy persistentInstance) {
		log.debug("deleting SavedHistroy instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SavedHistroy findById(java.lang.Integer id) {
		log.debug("getting SavedHistroy instance with id: " + id);
		try {
			SavedHistroy instance = (SavedHistroy) getSession().get(
					"com.pamirs.dao.SavedHistroy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SavedHistroy> findByExample(SavedHistroy instance) {
		log.debug("finding SavedHistroy instance by example");
		try {
			List<SavedHistroy> results = (List<SavedHistroy>) getSession()
					.createCriteria("com.pamirs.dao.SavedHistroy")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	
	
	
	
	public List getRecordsByName(String propertyName, String value , Integer userId) {
		log.debug("finding SavedHistroy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SavedHistroy as model where model."
					+ propertyName + "= ? and users.userId=?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SavedHistroy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SavedHistroy as model where model."
					+ propertyName + "= ?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SavedHistroy> findByDateTime(Object dateTime) {
		return findByProperty(DATE_TIME, dateTime);
	}

	public List<SavedHistroy> findBySavedName(Object savedName) {
		return findByProperty(SAVED_NAME, savedName);
	}

	public List<SavedHistroy> findByDocsHistory(Object docsHistory) {
		return findByProperty(DOCS_HISTORY, docsHistory);
	}

	public List findAll() {
		log.debug("finding all SavedHistroy instances");
		try {
			String queryString = "from SavedHistroy";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SavedHistroy merge(SavedHistroy detachedInstance) {
		log.debug("merging SavedHistroy instance");
		try {
			SavedHistroy result = (SavedHistroy) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SavedHistroy instance) {
		log.debug("attaching dirty SavedHistroy instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SavedHistroy instance) {
		log.debug("attaching clean SavedHistroy instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}