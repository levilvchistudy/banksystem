package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import entity.Account;

@SuppressWarnings({"rawtypes","unchecked"})
@Transactional
public class AccountDAO {
	private static final Logger log = LoggerFactory.getLogger(AccountDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String BALANCE = "balance";

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

	public void save(Account transientInstance) {
		log.debug("saving Account instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public int count(){
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
        criteria.setProjection(Projections.rowCount());
        long count =  (Long) criteria.uniqueResult();
        return (int) count;
	}

	public int count(String property,Object o){
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
        criteria.setProjection(Projections.rowCount());     
        criteria.add(Restrictions.eq(property, o));
        long count =  (Long) criteria.uniqueResult();
        return (int) count;
	}
	
	public void delete(Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Account findById(java.lang.Integer id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = (Account) getCurrentSession().get(
					"entity.Account", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Account> findByExample(Account instance) {
		log.debug("finding Account instance by example");
		try {
			List<Account> results = (List<Account>) getCurrentSession()
					.createCriteria("entity.Account").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Account instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Account as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Account> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Account> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Account> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	public List findAll() {
		log.debug("finding all Account instances");
		try {
			String queryString = "from Account";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Account instance) {
		log.debug("attaching clean Account instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AccountDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AccountDAO) ctx.getBean("AccountDAO");
	}

	public List<Account> findWithSection(int left, int right) {
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
		criteria.setFirstResult(left);
		criteria.setMaxResults(right-left);
		criteria.addOrder(Order.asc("accountid"));
		return criteria.list();
	}

	public List<Account> findWithSection(int left, int right, String property,Object o) {
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
		criteria.setFirstResult(left);
		criteria.setMaxResults(right-left);
		criteria.addOrder(Order.asc("accountid"));
		criteria.add(Restrictions.eq(property, o));
		return criteria.list();
	}

}