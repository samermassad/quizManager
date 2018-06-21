package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Exam;


public class ExamDAO extends GenericORMDao<Exam> {

	@Inject
	@Named("examQuery")
	String query;
	
	@Override
	protected WhereClauseBuilder<Exam> getWhereClauseBuilder(Exam entity) {
		
		final WhereClauseBuilder<Exam> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(query);

		// TODO as bonus : let the whereclausebuilder generate this map thanks to introspection
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("name", entity.getName());
		parameters.put("name", "%" + entity.getName() + "%");
		wcb.setParameters(parameters);
		return wcb;
	}

}
