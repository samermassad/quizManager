package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.ExamQuestionRel;

public class ExamOperationService {

	@Inject
	private ExamQuestionRelDAO examQuestionDao;
	
	@Inject
	private ExamDAO examDao;
	
	/**
	 * Safely deletes an exam
	 * @param exam
	 * 		 the exam to delete
	 */
	public void deleteExam(Exam exam) {
		
		ExamQuestionRel criteria = new ExamQuestionRel();
		criteria.setExam(exam);
		
		List<ExamQuestionRel> relList = examQuestionDao.search(criteria);
		
		for(ExamQuestionRel rel : relList) {
			examQuestionDao.delete(rel);			
		}
		examDao.delete(exam);
	}
}
