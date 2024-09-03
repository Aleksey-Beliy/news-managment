package edu.web.news.managment.controller;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.web.news.managment.beans.UserRole;
import edu.web.news.managment.beans.form.PasswordForm;
import edu.web.news.managment.beans.User;
import edu.web.news.managment.beans.UserData;
import edu.web.news.managment.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/auth")
	public String goToAuthPage(Model model) {
		model.addAttribute("userAuth", new UserData());
		return "/authentication";
	}

	@RequestMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "/registration";
	}

	@RequestMapping("/profile")
	public String goToUserProfile(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/user/auth";
		}

		model.addAttribute("user", user);
		model.addAttribute("passwordForm", new PasswordForm());
		return "/user_profile";
	}

	private int DEFAULT_ROLE_ID = 3;
	private String DEFAULT_ROLE_TITLE = "reader";

	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, Model model, Locale locale) {
		// Проверяем, существует ли уже пользователь с таким email
		if (userService.userExists(user.getUserData().getEmail())) {
			// Добавляем сообщение об ошибке и перенаправляем на страницу регистрации
			model.addAttribute("error", messageSource.getMessage("error.user.registration", null, locale));
			return "/registration";
		}
		if (user.getUserData() == null) {
			model.addAttribute("error", "User data is missing");
			return "/registration";
		}
		// Если пользователь не существует, продолжаем регистрацию
		UserRole role = new UserRole(DEFAULT_ROLE_ID, DEFAULT_ROLE_TITLE);
		user.setUserRole(role);
		userService.saveUser(user);
		// Перенаправляем на страницу приветствия после успешной регистрации
		return "/start_page";
	}

	@RequestMapping("/doAuthentication")
	public String checkAuth(@ModelAttribute("userAuth") UserData userData,
			@RequestParam(value = "rememberMe", required = false) String rememberMe, Model model, Locale locale,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		if (session.getAttribute("user") != null) {
			return "redirect:/news/startPage";
		}

		if (userData == null || userData.getEmail() == null || userData.getPassword() == null) {
			model.addAttribute("error", messageSource.getMessage("error.user.authentication", null, locale));
			return "/authentication";
		}

		boolean isRememberMe = "on".equals(rememberMe);

		User user = userService.checkAuth(userData, response, request, isRememberMe);

		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/news/startPage";
		} else {
			model.addAttribute("error", messageSource.getMessage("error.user.authentication", null, locale));
			return "redirect:/user/authentication";
		}
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute("passwordForm") PasswordForm passwordForm, Model model,
			HttpSession session) {
		try {
			// Получение текущего пользователя
			User user = (User) session.getAttribute("user");

			boolean isUpdated = userService.updatePassword(user.getUserData().getEmail(),
					passwordForm.getCurrentPassword(), passwordForm.getNewPassword(),
					passwordForm.getConfirmPassword());
			if (isUpdated) {
				model.addAttribute("successMessage", "Пароль успешно обновлен!");
			} else {
				model.addAttribute("errorMessage",
						"Не удалось обновить пароль. Проверьте текущий пароль и попробуйте снова.");
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "/user_profile";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Завершаем сессию
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		Cookie cookie = new Cookie("rememberMe", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		return "redirect:/user/auth";
	}

}
