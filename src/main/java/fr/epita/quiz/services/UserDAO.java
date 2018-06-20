package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.UserLogin;

public class UserDAO extends GenericORMDao<UserLogin> {

	@Inject
	@Named("userQuery")
	String query;
	
	@Override
	protected WhereClauseBuilder<UserLogin> getWhereClauseBuilder(UserLogin entity) {
		final WhereClauseBuilder<UserLogin> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(query);

		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("userName", entity.getUserName());
		parameters.put("hashedPassword", entity.getHashedPassword());
		wcb.setParameters(parameters);
		return wcb;
	}

}


