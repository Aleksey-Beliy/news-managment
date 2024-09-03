package edu.web.news.managment.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.web.news.managment.beans.User;
import edu.web.news.managment.service.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RememberMeFilter implements Filter {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public RememberMeFilter() {
		super();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) filterConfig.getServletContext());
		this.userService = (UserService) context.getBean("userService");
		if (this.userService == null) {
			throw new IllegalStateException("UserService not initialized");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;


		// Проверка наличия куки "rememberMe"
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("rememberMe".equals(cookie.getName())) {
					String email = cookie.getValue();
					// Поиск пользователя по email
					User user = userService.findUserByEmail(email);
					if (user != null) {
						// Автоматическая установка пользователя в сессию
						httpRequest.getSession().setAttribute("user", user);
					}
					break;
				}
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}