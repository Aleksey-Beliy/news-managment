package edu.web.news.managment.service.impl;

import edu.web.news.managment.beans.User;
import edu.web.news.managment.beans.UserData;
import edu.web.news.managment.dao.UserDao;
import edu.web.news.managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User checkAuth(UserData userData, HttpServletResponse response, HttpServletRequest request,
			boolean rememberMe) {
		return userDao.checkAuth(userData, response, request, rememberMe);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public boolean userExists(String email) {
		return userDao.findUserByEmail(email) != null;
	}

	@Override
	public boolean updatePassword(String email, String currentPassword, String newPassword, String confirmPassword) {

		User user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new RuntimeException("Пользователь не найден");
		}

		// Проверка текущего пароля
		if (!user.getUserData().getPassword().equals(currentPassword)) {
			return false; // Неверный текущий пароль
		}

		// Проверка совпадения нового пароля и подтверждения
		if (!newPassword.equals(confirmPassword)) {
			throw new RuntimeException("Новый пароль и подтверждение не совпадают");
		}

		// Обновление пароля
		userDao.updatePassword(email, newPassword);
		return true;
	}

}
