package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import fr.epita.quiz.datamodel.UserLogin;

public class AuthenticationService {

	@Inject
	UserDAO userDao;

	/**
	 * Authentication Service
	 * 
	 * @param user
	 * 		the user to login
	 * @return
	 * 		boolean
	 */
	public boolean authenticate(UserLogin user) {

		if (user == null || user.getUserName() == null || user.getHashedPassword() == null) {
			return false;
		}
		
		List<UserLogin> results = userDao.search(user);
		if (!results.isEmpty())
			return true;
		return false;
	}

}