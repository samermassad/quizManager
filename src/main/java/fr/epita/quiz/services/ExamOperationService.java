package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.ExamQuestionRel;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

public class ExamOperationService {

	@Inject
	private ExamQuestionRelDAO examQuestionDao;
	
	@Inject
	private ExamDAO examDao;
	
//	@Transactional
//  should be configured in spring
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
