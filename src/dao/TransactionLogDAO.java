package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import entity.Account;
import entity.TransactionLog;


@SuppressWarnings({"rawtypes","unchecked"})
@Transactional
public class TransactionLogDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TransactionLogDAO.class);
	// property constants
	public static final String TR_MONEY = "trMoney";
	public static final String DATETIME = "datetime";

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

	public void save(TransactionLog transientInstance) {
		log.debug("saving TransactionLog instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TransactionLog persistentInstance) {
		log.debug("deleting TransactionLog instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TransactionLog findById(java.lang.Integer id) {
		log.debug("getting TransactionLog instance with id: " + id);
		try {
			TransactionLog instance = (TransactionLog) getCurrentSession().get(
					"entity.TransactionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TransactionLog> findByAccount(Account account){
		final DetachedCriteria query = DetachedCriteria  
				.forClass(TransactionLog.class);  
		Criteria criteria = query.getExecutableCriteria(getCurrentSession());  
		criteria.add(Restrictions.eq("accountByAccountid", account));  
		List<TransactionLog> list = criteria.list();  
		if (list != null && !list.isEmpty()) {  
			return list; 
		}  
		return null;  
	}


	public List<TransactionLog> findByExample(TransactionLog instance) {
		log.debug("finding TransactionLog instance by example");
		try {
			List<TransactionLog> results = (List<TransactionLog>) getCurrentSession()
					.createCriteria("entity.TransactionLog")
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
		log.debug("finding TransactionLog instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TransactionLog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TransactionLog> findByTrMoney(Object trMoney) {
		return findByProperty(TR_MONEY, trMoney);
	}

	public List<TransactionLog> findByDatetime(Object datetime) {
		return findByProperty(DATETIME, datetime);
	}

	public List findAll() {
		log.debug("finding all TransactionLog instances");
		try {
			String queryString = "from TransactionLog";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TransactionLog merge(TransactionLog detachedInstance) {
		log.debug("merging TransactionLog instance");
		try {
			TransactionLog result = (TransactionLog) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TransactionLog instance) {
		log.debug("attaching dirty TransactionLog instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TransactionLog instance) {
		log.debug("attaching clean TransactionLog instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TransactionLogDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TransactionLogDAO) ctx.getBean("TransactionLogDAO");
	}
}