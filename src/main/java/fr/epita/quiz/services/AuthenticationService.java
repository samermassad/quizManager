package fr.epita.quiz.services;

import java.util.List;

import javax.inject.Inject;

import fr.epita.quiz.datamodel.UserLogin;

public class AuthenticationService {

	@Inject
	UserDAO userDao;

	public boolean authenticate(UserLogin user) {

		if (user == null || user.getUserName() == null || user.getHashedPassword() == null) {
			return false;
		}

		List<UserLogin> results = userDao.search(user);
//		System.out.println("Results size: " + results.size());
//		for(UserLogin u: results) {
//			System.out.println("Username: " + u.getUserName());
//			System.out.println("Password: " + u.getHashedPassword());
//		}
		if (results.size() > 0)
			return true;
		else
			return false;
	}

}