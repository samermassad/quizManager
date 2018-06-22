package fr.epita.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.apache.derby.impl.sql.execute.CreateConstraintConstantAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.GenericORMDao;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuestionOperationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class QuestionsTests {

	private static final Logger LOGGER = LogManager.getLogger(QuestionsTests.class);

	@Inject
	QuestionDAO questionDao;

	@Inject
	QuestionOperationService questionOps;
	
	@Inject
	MCQChoiceDAO mcqDao;

	@Inject
	SessionFactory factory;

	@Test
	public void create() {
		// given
		Assert.assertNotNull(questionDao);
		
		Question question = new Question();
		question.setQuestion("Create?");
		question.setType(QuestionType.MCQ);
		
		// when
		questionDao.create(question);
		
		// then
		Assert.assertNotNull(question.getId());
	}
	
	@Test
	public void searchAndUpdate() {
		// given
		Assert.assertNotNull(questionDao);
		
		Question question = new Question();
		question.setQuestion("Search?");
		question.setType(QuestionType.MCQ);

		// when
		questionDao.create(question);
		
		// update
		question.setQuestion("Updated question!");
		questionDao.update(question);

		// then
		List<Question> result1 = questionDao.search(question);
		Assert.assertEquals("Updated question!", result1.get(0).getQuestion());
		
	}
	
	@Test
	public void getAllQuestions() {
		// given
		Question question = new Question();
		question.setQuestion("Save Question?");
		question.setType(QuestionType.MCQ);
		
		Question question2 = new Question();
		question2.setQuestion("Save Question?");
		question2.setType(QuestionType.MCQ);
		
		questionDao.create(question);
		questionDao.create(question2);

		// when
		List<Question> result = questionDao.search(new Question());
		
		// then
		Assert.assertNotEquals(0, result.size());
		
	}
	
//	@Test
//	public void delete() {
//		// given
//		Assert.assertNotNull(questionDao);
//		
//		Question question = new Question();
//		question.setQuestion("Save Question?");
//		question.setType(QuestionType.MCQ);
//		
//		Question question2 = new Question();
//		question2.setQuestion("Save Question?");
//		question2.setType(QuestionType.MCQ);
//		
//		MCQChoice choice = new MCQChoice();
//		choice.setChoice("choice");
//		choice.setValid(true);
//		choice.setQuestion(question);
//		
//		MCQChoice choice2 = new MCQChoice();
//		choice2.setChoice("choice2");
//		choice2.setValid(false);
//		choice2.setQuestion(question);
//		
//		MCQChoice choice3 = new MCQChoice();
//		choice3.setChoice("choice2");
//		choice3.setValid(false);
//		choice3.setQuestion(question2);
//		
//		MCQChoice choice4 = new MCQChoice();
//		choice4.setChoice("choice2");
//		choice4.setValid(false);
//		choice4.setQuestion(question2);
//		
//		// when
//		questionDao.create(question);
//		questionDao.create(question2);
//		mcqDao.create(choice);
//		mcqDao.create(choice2);
//		mcqDao.create(choice3);
//		mcqDao.create(choice4);
//		
//		// then
//		MCQChoice criteria = new MCQChoice();
//		criteria.setQuestion(question);
//		
//		//questionOps.deleteQuestion(question);
//		
//		List<MCQChoice> result = mcqDao.search(criteria);
//		System.out.println(result.size());
//		Assert.assertEquals(0, result.size());
		
		
		
		
		// then
//				MCQChoice criteria = new MCQChoice();
//				List<MCQChoice> result = mcqDao.search(criteria);
//				Assert.assertEquals(4, result.size());
//				
//				criteria.setQuestion(question);
//				result = mcqDao.search(criteria);
//				Assert.assertEquals(2, result.size());
//				
//				questionOps.deleteQuestion(question);
//				
//				result = mcqDao.search(criteria);
//				Assert.assertEquals(0, result.size());
//	}

}