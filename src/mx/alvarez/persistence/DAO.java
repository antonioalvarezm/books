package mx.alvarez.persistence;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.jpa.JpaEntityManager;

import mx.alvarez.commons.Constants;

abstract class DAO {
	Logger logger = Logger.getLogger(DAO.class);
	private EntityManagerFactory emf = null;
	protected EntityManager em;
	private String unitName = Constants.PERSISTENCE_UNIT_NAME;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void beginTransaccion() {
		try {
			em.getTransaction().begin();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void stopTransaccion() {
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public Connection getConnection() {
		UnitOfWorkImpl unitOfWork = (UnitOfWorkImpl) ((JpaEntityManager) getEntityManager().getDelegate())
				.getActiveSession();
		unitOfWork.beginEarlyTransaction();
		Accessor accessor = unitOfWork.getAccessor();
		accessor.incrementCallCount(unitOfWork.getParent());
		accessor.decrementCallCount();
		return accessor.getConnection();
	}

	/**
	 * Establece conexion
	 */
	public void connect() {
		try {
			emf = Persistence.createEntityManagerFactory(unitName);
			em = emf.createEntityManager();
			em.getTransaction().begin();
			logger.debug("conecto..");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Cierra conexion
	 */
	public void close() {
		try {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
			logger.debug("cerro conexion");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected <E> E create(E e) {
		try {
			if(!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.persist(e);
			em.flush();
			em.clear();
			commit();
		} catch (SecurityException e1) {
			em.getTransaction().rollback();
			logger.error(e1);
		} catch (IllegalStateException e1) {
			em.getTransaction().rollback();
			logger.error(e1);
		}
		return e;
	}

	protected void commit() {
		if(!em.getTransaction().isActive()) {
			em.getTransaction().begin();			
		}
		em.getTransaction().commit();
	}

	protected <E> void persist(E e) {
		em.persist(e);
	}

	protected <E> void create(List<E> e) {
		em.persist(e);
	}

	protected <E> void refresh(E e) {
		em.refresh(e);
	}

	protected void flush() {
		em.flush();
	}

	protected <E> E merge(E e) {
		if(!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.merge(e);
		return e;
	}

	protected <E> E update(E e) {
		try {
			if(!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.merge(e);
		} catch (SecurityException e1) {
			logger.error(e1);
		} catch (IllegalStateException e1) {
			logger.error(e1);
		}
		return e;
	}

	protected <E> void delete(Class<E> clazz, long id) {
		if(!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.remove(em.find(clazz, id));
	}

	protected <E> void delete(Class<E> clazz, String id) {
		if(!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.remove(em.find(clazz, id));
	}

	protected <E> void delete(E elemento) {
		try {
			if(!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.remove(em.merge(elemento));
			em.flush();
			em.clear();
		} catch (SecurityException e1) {
			logger.error(e1);
		} catch (IllegalStateException e1) {
			logger.error(e1);
		}
	}

	protected <E> E find(Class<E> clazz, Object id) {
		return em.find(clazz, id);
	}

	protected <E> E find(Class<E> clazz, int id) {
		return em.find(clazz, id);
	}

	protected <E> E find(Class<E> clazz, long id) {
		return em.find(clazz, id);
	}

	protected <E> E find(Class<E> clazz, String id) {
		E element = em.find(clazz, id);
		return element;
	}

	@SuppressWarnings("unchecked")
	protected <E> E namedFindSingle(Class<E> clazz, String query, Object[][] params) {
		Query q = em.createNamedQuery(query);
		if (params != null)
			for (int i = 0; i < params.length; i++) {
				q.setParameter(params[i][0].toString(), params[i][1]);
			}
		List<Object> results = q.getResultList();
		return (E) (results.isEmpty() ? null : results.get(0));
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> namedFind(Class<E> clazz, String query, Object[][] params) {
		Query q = em.createNamedQuery(query);
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null)
				q.setParameter(params[i][0].toString(), params[i][1]);
		}
		em.clear();
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected <E> E nativequery(Class<E> clazz, String query, Object[][] params) {
		Query q = em.createNativeQuery(query);
		for (Object[] objects : params) {
			q.setParameter(objects[0].toString(), objects[1]);
		}
		return (E) q.getSingleResult();
	}

	protected StoredProcedureQuery getStroedProc(String storename) {
		try {
			return em.createStoredProcedureQuery(storename);
		} catch (Exception e) {
			logger.error(storename, e);
			return null;
		}
	}

	protected Object getSingleResult(String qlString) {
		return em.createQuery(qlString).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> nativeQuery(Class<E> clazz, String query) {
		Query q = em.createQuery(query, clazz);
		em.clear();
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> createNativeQuery(Class<E> clazz, String query) {
		Query q = em.createNativeQuery(query, clazz);
		em.clear();
		return q.getResultList();
	}

	protected void queryNative(String query) {
		em.createNativeQuery(query);
		em.flush();
		em.clear();
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> namedFindAll(Class<E> clazz, String query) {
		em.clear();
		Query q = em.createNamedQuery(query, clazz);
		return q.getResultList();
	}

}
