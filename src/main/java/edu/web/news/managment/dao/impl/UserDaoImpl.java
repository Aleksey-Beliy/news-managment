package edu.web.news.managment.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import edu.web.news.managment.beans.User;
import edu.web.news.managment.beans.UserData;
import edu.web.news.managment.beans.UserRole;
import edu.web.news.managment.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager entityManager;

	public UserDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private String SAVE_USER = "FROM User WHERE userData.email = :email";

	@Override
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();

		// Проверка, существует ли пользователь с таким email
		Query<User> query = currentSession.createQuery(SAVE_USER, User.class);
		query.setParameter("email", user.getUserData().getEmail());

		User existingUser = query.uniqueResult();

		if (existingUser != null) {
			// Если пользователь уже существует, выбросить исключение
			throw new IllegalStateException("Пользователь с таким email уже зарегистрирован.");
		}

		// Если пользователя нет, сохранить его
		currentSession.persist(user);
	}

	private String CHECK_AUTH = "SELECT u FROM User u WHERE u.userData.email = :email AND u.userData.password = :password";

	@Override
	public User checkAuth(UserData userData, HttpServletResponse response, HttpServletRequest request,
			boolean rememberMe) {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<User> query = currentSession.createQuery(CHECK_AUTH, User.class);
		query.setParameter("email", userData.getEmail());
		query.setParameter("password", userData.getPassword());

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		if (user != null) {
			// Сохранение пользователя в сессии
			HttpSession session = request.getSession();
			session.setAttribute("authenticatedUser", user);

			// Если установлена галочка "Remember Me", установка куки
			if (rememberMe) {
				Cookie rememberMeCookie = new Cookie("rememberMe", user.getUserData().getEmail());
				rememberMeCookie.setMaxAge(7 * 24 * 60 * 60); // срок действия куки — 7 дней
				rememberMeCookie.setPath("/");
				response.addCookie(rememberMeCookie);
			}
		}

		return user;
	}

	private String SELECT_ROLE = "SELECT u.userRole FROM User u WHERE u.id = :userId";

	public UserRole findUserRoleByUserId(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();

		TypedQuery<UserRole> query = currentSession.createQuery(SELECT_ROLE, UserRole.class);
		query.setParameter("userId", userId);

		UserRole userRole = null;

		userRole = query.getSingleResult();

		return userRole;
	}

	private String FIND_USER_BY_EMAIL = "SELECT u FROM User u JOIN u.userData ud WHERE ud.email = :email";

	public User findUserByEmail(String email) {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<User> query = currentSession.createQuery(FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);

		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return user;
	}

	private String UPDATE_PASSWORD = "UPDATE UserData SET password = :newPassword WHERE email = :email";

	@Override
	public void updatePassword(String email, String newPassword) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> query = session.createQuery(UPDATE_PASSWORD);
		query.setParameter("newPassword", newPassword);
		query.setParameter("email", email);
		int result = query.executeUpdate();
		if (result == 0) {
			throw new RuntimeException("Ошибка при обновлении пароля");
		}
	}
}
