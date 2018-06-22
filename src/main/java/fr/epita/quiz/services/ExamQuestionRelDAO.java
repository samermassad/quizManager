package fr.epita.quiz.services;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.ExamQuestionRel;


public class ExamQuestionRelDAO extends GenericORMDao<ExamQuestionRel> {

	@Inject
	@Named("examQuestionRelQuery")
	String query;
	
	@Override
	protected String getQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
