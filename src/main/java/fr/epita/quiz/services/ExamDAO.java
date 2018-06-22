package fr.epita.quiz.services;

import javax.inject.Inject;
import javax.inject.Named;

import fr.epita.quiz.datamodel.Exam;

public class ExamDAO extends GenericORMDao<Exam> {

	@Inject
	@Named("examQuery")
	String query;

	@Override
	protected String getQuery() {
		return query;
	}
}
