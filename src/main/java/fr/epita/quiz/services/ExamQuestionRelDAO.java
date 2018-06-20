package fr.epita.quiz.services;

import java.util.LinkedHashMap;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.ExamQuestionRel;


public class ExamQuestionRelDAO extends GenericORMDao<ExamQuestionRel> {

	@Override
	protected WhereClauseBuilder<ExamQuestionRel> getWhereClauseBuilder(ExamQuestionRel entity) {
		final WhereClauseBuilder<ExamQuestionRel> wcb = new WhereClauseBuilder<>();
		
		wcb.setQueryString("from ExamQuestionRel");
		wcb.setParameters(new LinkedHashMap<>());
		return wcb;
	}

}
