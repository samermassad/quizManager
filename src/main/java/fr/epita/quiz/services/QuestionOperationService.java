package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

public class QuestionOperationService {

	@Inject
	private MCQChoiceDAO mcqChoiceDAO;
	
	@Inject
	private QuestionDAO questionDAO;
	
	//@Inject
	//private SessionFactory factory;
	
//	@Transactional
//  should be configured in spring
	public void deleteQuestion(Question question) {
		
		//Transaction transaction = factory.openSession().beginTransaction();
		
		MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(question);
		
		List<MCQChoice> choicesList = mcqChoiceDAO.search(criteria);
		
		for(MCQChoice choice : choicesList) {
			mcqChoiceDAO.delete(choice);			
		}
		questionDAO.delete(question);
		
		//transaction.commit();
	}
}
