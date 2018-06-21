package fr.epita.quiz.services;

import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.tests.QuestionsTests;


public abstract class GenericORMDao<T> {

	@Inject
	SessionFactory sf;

	// Create
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
			throw e;
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
			throw e;
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
			throw e;
		}
	}

	public boolean afterDelete(T entity) {
		return true;
	}

	// Search
	public boolean beforeSearch(T entity) {
		return entity != null;
	}

	public final List<T> search(T entity) {
		
		if (!beforeSearch(entity)) {
			System.out.println("Entity Null!");
			return null;
		}
		final Session session = sf.openSession();
		
		
		final WhereClauseBuilder<T> wcb = getWhereClauseBuilder(entity);
		final Query searchQuery = session.createQuery(wcb.getQueryString());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			if(parameterEntry.getValue() == null) {
				System.out.println("Parameter Value is null");
				searchQuery.setParameter(parameterEntry.getKey(), "");
			} else {
				System.out.println("Parameter Value: " + parameterEntry.getValue());
				searchQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
			}
		}
		return searchQuery.list();
	}

	public boolean afterSearch(T entity) {
		return true;
	}

	protected abstract WhereClauseBuilder getWhereClauseBuilder(T entity);

}