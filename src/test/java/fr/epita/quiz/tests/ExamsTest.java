package fr.epita.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.apache.derby.impl.sql.execute.CreateConstraintConstantAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.ExamDAO;
import fr.epita.quiz.services.ExamOperationService;
import fr.epita.quiz.services.ExamQuestionRelDAO;
import fr.epita.quiz.services.GenericORMDao;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuestionOperationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ExamsTest {

	//private static final Logger LOGGER = LogManager.getLogger(ExamsTest.class);

	@Inject
	private ExamDAO examDao;

	@Inject
	private ExamOperationService relOps;

	@Test
	public void createExam() {
		// given
		Exam exam = new Exam();
		exam.setName("Advanced Java Programming");

		// when
		examDao.create(exam);
		
		// then
		Exam examCriteria = new Exam();
		examCriteria.setName("ava");
		
		List<Exam> results = examDao.search(examCriteria);
		Assert.assertNotEquals(0, results.size());
		
		for(Exam e : results) {
			relOps.deleteExam(e);
		}
		
	}

}