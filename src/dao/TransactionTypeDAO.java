package dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import entity.TransactionType;

@SuppressWarnings({"rawtypes","unchecked"})
@Transactional
public class TransactionTypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TransactionTypeDAO.class);
	// property constants
	public static final String NAME = "name";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(TransactionType transientInstance) {
		log.debug("saving TransactionType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TransactionType persistentInstance) {
		log.debug("deleting TransactionType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TransactionType findById(java.lang.Integer id) {
		log.debug("getting TransactionType instance with id: " + id);
		try {
			TransactionType instance = (TransactionType) getCurrentSession()
					.get("entity.TransactionType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TransactionType> findByExample(TransactionType instance) {
		log.debug("finding TransactionType instance by example");
		try {
			List<TransactionType> results = (List<TransactionType>) getCurrentSession()
					.createCriteria("entity.TransactionType")
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
		log.debug("finding TransactionType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TransactionType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TransactionType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all TransactionType instances");
		try {
			String queryString = "from TransactionType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TransactionType merge(TransactionType detachedInstance) {
		log.debug("merging TransactionType instance");
		try {
			TransactionType result = (TransactionType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TransactionType instance) {
		log.debug("attaching dirty TransactionType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TransactionType instance) {
		log.debug("attaching clean TransactionType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TransactionTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TransactionTypeDAO) ctx.getBean("TransactionTypeDAO");
	}
}