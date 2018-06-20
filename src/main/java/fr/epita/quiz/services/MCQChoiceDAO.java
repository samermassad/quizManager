package fr.epita.quiz.services;

import java.util.LinkedHashMap;

import fr.epita.quiz.datamodel.MCQChoice;

public class MCQChoiceDAO extends GenericORMDao<MCQChoice> {

	@Override
	public boolean beforeCreate(MCQChoice mcq) {
		// TODO
		// improve tests
		return mcq.getChoice() != null && mcq.getQuestion() != null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.epita.quiz.services.GenericORMDao#getWhereClauseBuilder(java.lang.Object)
	 */
	@Override
	protected WhereClauseBuilder<MCQChoice> getWhereClauseBuilder(MCQChoice entity) {
		final WhereClauseBuilder<MCQChoice> whereClauseBuilder = new WhereClauseBuilder<>();
		whereClauseBuilder.setParameters(new LinkedHashMap<>());

		// TODO : load from configuration
		final String query = "from MCQChoice";

		whereClauseBuilder.setQueryString(query);
		return whereClauseBuilder;

	}

}
