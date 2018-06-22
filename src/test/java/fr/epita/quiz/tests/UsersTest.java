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
import fr.epita.quiz.datamodel.UserLogin;
import fr.epita.quiz.services.AuthenticationService;
import fr.epita.quiz.services.ExamDAO;
import fr.epita.quiz.services.GenericORMDao;
import fr.epita.quiz.services.MCQChoiceDAO;
import fr.epita.quiz.services.QuestionDAO;
import fr.epita.quiz.services.QuestionOperationService;
import fr.epita.quiz.services.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UsersTest {

	//private static final Logger LOGGER = LogManager.getLogger(UsersTest.class);

	@Inject
	private UserDAO userDao;
	
	@Inject
	private AuthenticationService authService;

	@Test
	public void createAndAuthenticate() {
		// given
		UserLogin user = new UserLogin();
		user.setUserName("root");
		user.setPassword("root");
		
		// when
		userDao.create(user);
		
		// then
		Assert.assertNotNull(user.getId());
		
		// login
		Assert.assertTrue(authService.authenticate(user));
		
		UserLogin user3 = new UserLogin();
		user3.setUserName("Samer2");
		user3.setPassword("blabla");
		Assert.assertFalse(authService.authenticate(user3));
	}
	
	@Test
	public void searchAndUpdate() {
		// given
		UserLogin user = new UserLogin();
		user.setUserName("Samer");
		user.setPassword("Masaad");
		
		// when
		userDao.create(user);
		
		// then
		List<UserLogin> results = userDao.search(user);
		Assert.assertNotNull(user.getId());
		
		for(UserLogin u : results) {
			u.setUserName("Updated");
			userDao.update(user);
		}
		
		UserLogin criteria = new UserLogin();
		criteria.setUserName("Updated");
		criteria.setPassword("Masaad");
		results = userDao.search(criteria);
		
		for(UserLogin u : results) {
			Assert.assertNotNull(u.getId());
		}
		
	}

}

