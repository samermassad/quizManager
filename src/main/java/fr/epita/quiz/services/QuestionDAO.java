package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Question;

public class QuestionDAO extends GenericORMDao<Question> {

	@Inject
	@Named("questionQuery")
	String query;

	@Override
	protected WhereClauseBuilder<Question> getWhereClauseBuilder(Question entity) {
		final WhereClauseBuilder<Question> wcb = new WhereClauseBuilder<>();
		wcb.setQueryString(query);

		// TODO as bonus : let the whereclausebuilder generate this map thanks to introspection
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("type", entity.getType());
		parameters.put("question", entity.getQuestion());
		wcb.setParameters(parameters);
		return wcb;

	}

}
