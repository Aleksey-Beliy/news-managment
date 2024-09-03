package edu.web.news.managment.service;

import edu.web.news.managment.beans.User;
import edu.web.news.managment.beans.UserData;
import edu.web.news.managment.beans.form.PasswordForm;
import edu.web.news.managment.dao.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	User checkAuth(UserData userData, HttpServletResponse response, HttpServletRequest request, boolean rememberMe);

	void saveUser(User user);

	User findUserByEmail(String email);

	boolean userExists(String email);

	boolean updatePassword(String email, String currentPassword, 
            String newPassword, String confirmPassword) throws Exception;
}
