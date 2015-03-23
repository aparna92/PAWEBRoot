package com.pamirs.dao;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pamirs.model.DocumentsInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentsInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.pamirs.dao.DocumentsInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DocumentsInfoDAO extends HibernateBasicTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(DocumentsInfoDAO.class);
	// property constants
	public static final String ARTICLE_NUMBER = "articleNumber";
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String RANK = "rank";
	public static final String SEARCH_KEY = "searchKey";
	public static final String TITLE_HREF = "titleHref";
	public static final String PHOTO_PATH = "photoPath";
	public static final String BOOK_NAME = "bookName";
	public static final String BOOK_LANGUAGE = "bookLanguage";

	public void save(DocumentsInfo transientInstance) {
		log.debug("saving DocumentsInfo instance");
		try {
			geteSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentsInfo persistentInstance) {
		log.debug("deleting DocumentsInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentsInfo findById(java.lang.Integer id) {
		log.debug("getting DocumentsInfo instance with id: " + id);
		try {
			DocumentsInfo instance = (DocumentsInfo) getSession().get(
					"com.pamirs.dao.DocumentsInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DocumentsInfo> findByExample(DocumentsInfo instance) {
		log.debug("finding DocumentsInfo instance by example");
		try {
			List<DocumentsInfo> results = (List<DocumentsInfo>) getSession()
					.createCriteria("com.pamirs.dao.DocumentsInfo")
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
		log.debug("finding DocumentsInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocumentsInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = geteSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	

	public List getResults(String propertyName, String value) {
		log.debug("finding DocumentsInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			// author,COUNT(*) as count FROM documents_info where document_id in(1,2,3) GROUP BY author ;
			if(value!=null && value.trim().length()==0)
				value = "0";
			if(value.endsWith(","))
				value = value.substring(0,value.indexOf(","));
			
			String queryString = "select "+ propertyName +",COUNT(*) as count from DocumentsInfo  where documentId in ("+value+") GROUP BY " + propertyName;
			Query queryObject = geteSession().createQuery(queryString);
					return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List searchKey(String propertyName, Object value,String orderBy) {
		log.debug("finding DocumentsInfo instance with property: "
				+ propertyName + ", value: " + value);
		Session session=null;
		List ls = new ArrayList();
		try {
			String queryString = "from DocumentsInfo as model where model."
					+ propertyName + " like '%"+value+"%'";
			if(orderBy!=null){
				queryString = "from DocumentsInfo as model where model."
						+ propertyName + " like '%"+value+"%' order by "+orderBy;
			}
			
			System.out.println("queryString "+queryString);
			session= geteSession();
			System.out.println("session "+session);
			Query queryObject =session.createQuery(queryString);
		//	queryObject.setParameter(0, value);
			ls = queryObject.list();
			
			System.out.println("List Return From Database "+ls);
		} catch (Exception re) {
			re.printStackTrace();
			log.error("find by property name failed", re);
			if(session!=null){
				closeSession(session);
			}
			
			 
		}finally{
			if(session!=null){
				closeSession(session);
			}
			
			return ls;
			
		}
	}
	
	
	public List searchKey(String propertyName,String value) {
		Session session=null;
		List ls = new ArrayList();
		try {
			String queryString = "from DocumentsInfo as model where model."
					+ propertyName + " in ("+value+")";
		 
			
			System.out.println("queryString "+queryString);
			session= geteSession();
			System.out.println("session "+session);
			Query queryObject =session.createQuery(queryString);
		//	queryObject.setParameter(0, value);
			ls = queryObject.list();
			
			System.out.println("List Return From Database "+ls);
		} catch (Exception re) {
			re.printStackTrace();
			log.error("find by property name failed", re);
			if(session!=null){
				closeSession(session);
			}
			
			 
		}finally{
			if(session!=null){
				closeSession(session);
			}
			
			return ls;
			
		}
	}

	public List<DocumentsInfo> findByArticleNumber(Object articleNumber) {
		return findByProperty(ARTICLE_NUMBER, articleNumber);
	}

	public List<DocumentsInfo> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<DocumentsInfo> findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List<DocumentsInfo> findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}

	public List<DocumentsInfo> findBySearchKey(Object searchKey) {
		return findByProperty(SEARCH_KEY, searchKey);
	}

	public List<DocumentsInfo> findByTitleHref(Object titleHref) {
		return findByProperty(TITLE_HREF, titleHref);
	}

	public List<DocumentsInfo> findByPhotoPath(Object photoPath) {
		return findByProperty(PHOTO_PATH, photoPath);
	}

	public List<DocumentsInfo> findByBookName(Object bookName) {
		return findByProperty(BOOK_NAME, bookName);
	}

	public List<DocumentsInfo> findByBookLanguage(Object bookLanguage) {
		return findByProperty(BOOK_LANGUAGE, bookLanguage);
	}

	public List findAll() {
		log.debug("finding all DocumentsInfo instances");
		try {
			String queryString = "from DocumentsInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentsInfo merge(DocumentsInfo detachedInstance) {
		log.debug("merging DocumentsInfo instance");
		try {
			DocumentsInfo result = (DocumentsInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentsInfo instance) {
		log.debug("attaching dirty DocumentsInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentsInfo instance) {
		log.debug("attaching clean DocumentsInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}