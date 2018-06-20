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
	public void delete() {
		// given
		Assert.assertNotNull(questionDao);
		
		Question question = new Question();
		question.setQuestion("Save Question?");
		question.setType(QuestionType.MCQ);
		
		MCQChoice choice = new MCQChoice();
		choice.setChoice("choice");
		choice.setValid(true);
		choice.setQuestion(question);
		
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoice("choice2");
		choice2.setValid(false);
		choice2.setQuestion(question);
		
		// when
		questionDao.create(question);
		mcqDao.create(choice);
		mcqDao.create(choice2);
		
		// then
		MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(question);
		
		questionOps.deleteQuestion(question);
		
		List<MCQChoice> result = mcqDao.search(criteria);
		Assert.assertEquals(0, result.size());
	}

}