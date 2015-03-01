package com.pamirs.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pamirs.model.RoleMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * RoleMaster entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.pamirs.dao.RoleMaster
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RoleMasterDAO extends HibernateBasicTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(RoleMasterDAO.class);
	// property constants
	public static final String ROLE_NAME = "roleName";
	public static final String ROLE_DES = "roleDes";

	public void save(RoleMaster transientInstance) {
		log.debug("saving RoleMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RoleMaster persistentInstance) {
		log.debug("deleting RoleMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RoleMaster findById(java.lang.Integer id) {
		log.debug("getting RoleMaster instance with id: " + id);
		try {
			RoleMaster instance = (RoleMaster) getSession().get(
					"com.pamirs.dao.RoleMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RoleMaster> findByExample(RoleMaster instance) {
		log.debug("finding RoleMaster instance by example");
		try {
			List<RoleMaster> results = (List<RoleMaster>) getSession()
					.createCriteria("com.pamirs.dao.RoleMaster")
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
		log.debug("finding RoleMaster instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RoleMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RoleMaster> findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

	public List<RoleMaster> findByRoleDes(Object roleDes) {
		return findByProperty(ROLE_DES, roleDes);
	}

	public List findAll() {
		log.debug("finding all RoleMaster instances");
		try {
			String queryString = "from RoleMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RoleMaster merge(RoleMaster detachedInstance) {
		log.debug("merging RoleMaster instance");
		try {
			RoleMaster result = (RoleMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RoleMaster instance) {
		log.debug("attaching dirty RoleMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RoleMaster instance) {
		log.debug("attaching clean RoleMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}