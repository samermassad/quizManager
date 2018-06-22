package fr.epita.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.ExamQuestionRel;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionType;
import fr.epita.quiz.services.ExamDAO;
import fr.epita.quiz.services.ExamQuestionRelDAO;
import fr.epita.quiz.services.QuestionDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ExamsQuestionRelTest {

	@Inject
	private QuestionDAO questionDao;

	@Inject
	private ExamDAO examDao;

	@Inject
	private ExamQuestionRelDAO relDao;

	@Test
	public void createRel() {
		// given
		Exam exam = new Exam();
		exam.setName("Advanced Java Programming");

		Question question = new Question();
		question.setQuestion("Search Question?");
		question.setType(QuestionType.MCQ);

		// when
		examDao.create(exam);
		questionDao.create(question);

		// then
		ExamQuestionRel rel = new ExamQuestionRel();
		rel.setExam(exam);
		rel.setQuestion(question);

		relDao.create(rel);
		relDao.delete(rel);
	}

	@Test
	public void search() {
		// given
		Exam exam = new Exam();
		exam.setName("Advanced Java Programming");

		Question question = new Question();
		question.setQuestion("Search Question?");
		question.setType(QuestionType.MCQ);

		// when
		examDao.create(exam);
		questionDao.create(question);

		// then
		ExamQuestionRel rel = new ExamQuestionRel();
		rel.setExam(exam);
		rel.setQuestion(question);

		relDao.create(rel);
		
		ExamQuestionRel criteria = new ExamQuestionRel();
		criteria.setExam(exam);
		
		List<ExamQuestionRel> results = relDao.search(criteria);
		Assert.assertNotEquals(0, results.size());
		
	}

}