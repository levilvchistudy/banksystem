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
import entity.Personinfo;

@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class PersoninfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PersoninfoDAO.class);
	// property constants
	public static final String REALNAME = "realname";
	public static final String AGE = "age";
	public static final String SEX = "sex";
	public static final String CARDID = "cardid";
	public static final String ADDRESS = "address";
	public static final String TELEPHONE = "telephone";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Personinfo transientInstance) {
		log.debug("saving Personinfo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Personinfo persistentInstance) {
		log.debug("deleting Personinfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<Personinfo> findByAccount(Account account){
		final DetachedCriteria query = DetachedCriteria  
				.forClass(Personinfo.class);  
		Criteria criteria = query.getExecutableCriteria(getCurrentSession());  
		criteria.add(Restrictions.eq("account", account));  
		List<Personinfo> list = criteria.list();  
		if (list != null && !list.isEmpty()) {  
			return list; 
		}  
		return null;  
	}

	public Personinfo findById(entity.PersoninfoId id) {
		log.debug("getting Personinfo instance with id: " + id);
		try {
			Personinfo instance = (Personinfo) getCurrentSession().get(
					"entity.Personinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Personinfo> findByExample(Personinfo instance) {
		log.debug("finding Personinfo instance by example");
		try {
			List<Personinfo> results = (List<Personinfo>) getCurrentSession()
					.createCriteria("entity.Personinfo").add(create(instance))
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
		log.debug("finding Personinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Personinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Personinfo> findByRealname(Object realname) {
		return findByProperty(REALNAME, realname);
	}

	public List<Personinfo> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List<Personinfo> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List<Personinfo> findByCardid(Object cardid) {
		return findByProperty(CARDID, cardid);
	}

	public List<Personinfo> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Personinfo> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List findAll() {
		log.debug("finding all Personinfo instances");
		try {
			String queryString = "from Personinfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Personinfo merge(Personinfo detachedInstance) {
		log.debug("merging Personinfo instance");
		try {
			Personinfo result = (Personinfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Personinfo instance) {
		log.debug("attaching dirty Personinfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Personinfo instance) {
		log.debug("attaching clean Personinfo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PersoninfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PersoninfoDAO) ctx.getBean("PersoninfoDAO");
	}
}