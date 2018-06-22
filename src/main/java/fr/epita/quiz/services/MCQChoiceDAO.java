package fr.epita.quiz.services;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.MCQChoice;

public class MCQChoiceDAO extends GenericORMDao<MCQChoice> {

	@Inject
	@Named("mcqQuery")
	String query;
	
	@Override
	public boolean beforeCreate(MCQChoice mcq) {
		return mcq.getChoice() != null && mcq.getQuestion() != null;
	}

	@Override
	protected String getQuery() {
		return query;
	}


}
