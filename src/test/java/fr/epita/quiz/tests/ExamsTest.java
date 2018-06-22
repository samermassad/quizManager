package fr.epita.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.services.ExamDAO;
import fr.epita.quiz.services.ExamOperationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ExamsTest {

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
		examCriteria.setName("Advanced Java Programming");
		
		List<Exam> results = examDao.search(examCriteria);
		Assert.assertNotEquals(0, results.size());
		
		for(Exam e : results) {
			relOps.deleteExam(e);
		}
		
	}

}