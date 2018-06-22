package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

public class QuestionOperationService {

	@Inject
	private MCQChoiceDAO mcqChoiceDAO;
	
	@Inject
	private QuestionDAO questionDAO;
	

	/**
	 * Safely deletes a question
	 * @param exam
	 * 		 the question to delete
	 */
	public void deleteQuestion(Question question) {
		
		MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(question);
		
		List<MCQChoice> choicesList = mcqChoiceDAO.search(criteria);
		
		for(MCQChoice choice : choicesList) {
			mcqChoiceDAO.delete(choice);			
		}
		questionDAO.delete(question);
	}
}
