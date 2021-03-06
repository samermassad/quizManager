package fr.epita.quiz.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class GenericORMDao<T> {

	@Inject
	SessionFactory sf;

	private static final Logger LOGGER = LogManager.getLogger(GenericORMDao.class);
	
	public boolean beforeCreate(T entity) {
		return entity != null;
	}

	public final void create(T entity) {
		if (!beforeCreate(entity)) {
			return;
		}
		final Session session = sf.openSession();
		final Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error(e);
		}

	}

	public boolean afterCreate(T entity) {
		return true;
	}

	// Update
	public boolean beforeUpdate(T entity) {
		return entity != null;
	}

	public final void update(T entity) {
		if (!beforeUpdate(entity)) {
			return;
		}
		final Session session = sf.openSession();
		final Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error(e);
		}
	}

	public boolean afterUpdate(T entity) {
		return true;
	}

	// Delete
	public boolean beforeDelete(T entity) {
		return entity != null;
	}

	public final void delete(T entity) {
		if (!beforeDelete(entity)) {
			return;
		}
		final Session session = sf.openSession();
		final Transaction tx = session.beginTransaction();
		try {
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error(e);
		}
	}

	public boolean afterDelete(T entity) {
		return true;
	}

	// Search
	public boolean beforeSearch(T entity) {
		return entity != null;
	}

	@SuppressWarnings("unchecked")
	public final List<T> search(T entity) {

		if (!beforeSearch(entity)) {
			return new ArrayList<>();
		}
		final Session session = sf.openSession();
		final WhereClauseBuilder<T> wcb = getWhereClauseBuilder(entity);

		final Query<T> searchQuery = session.createQuery(wcb.getQueryString());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			if (parameterEntry.getValue() == null) {
				searchQuery.setParameter(parameterEntry.getKey(), null);
			}
			searchQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
		}
		return searchQuery.list();

	}

	public boolean afterSearch(T entity) {
		return true;
	}

	protected abstract String getQuery();

	protected WhereClauseBuilder<T> getWhereClauseBuilder(T entity) {

		final WhereClauseBuilder<T> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(getQuery());
		final Map<String, Object> parameters = new LinkedHashMap<>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				parameters.put(field.getName(), field.get(entity));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOGGER.error(e);
			}
		}
		wcb.setParameters(parameters);
		return wcb;

	}

}