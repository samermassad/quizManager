package fr.epita.quiz.services;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.UserLogin;

public class UserDAO extends GenericORMDao<UserLogin> {

	@Inject
	@Named("userQuery")
	String query;

	@Override
	protected String getQuery() {
		return query;
	}
}


