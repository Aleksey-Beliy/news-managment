package edu.web.news.managment.dao;

import edu.web.news.managment.beans.User;
import edu.web.news.managment.beans.UserData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface UserDao {

	User checkAuth(UserData userData, HttpServletResponse response, HttpServletRequest request, boolean rememberMe);

	void saveUser(User user);

	User findUserByEmail(String email);
	
	void updatePassword(String email, String newPassword);

}
