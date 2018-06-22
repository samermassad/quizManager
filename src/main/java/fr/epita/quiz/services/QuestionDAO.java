package fr.epita.quiz.services;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Question;

public class QuestionDAO extends GenericORMDao<Question> {

	@Inject
	@Named("questionQuery")
	String query;

	@Override
	protected String getQuery() {
		return query;
	}

	
}
