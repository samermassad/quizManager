package fr.epita.quiz.services;

import java.util.LinkedHashMap;

import fr.epita.quiz.datamodel.Exam;


public class ExamDAO extends GenericORMDao<Exam> {

	@Override
	protected WhereClauseBuilder<Exam> getWhereClauseBuilder(Exam entity) {
		final WhereClauseBuilder<Exam> wcb = new WhereClauseBuilder<>();
		
		wcb.setQueryString("from Exam");
		wcb.setParameters(new LinkedHashMap<>());
		return wcb;
	}

}
