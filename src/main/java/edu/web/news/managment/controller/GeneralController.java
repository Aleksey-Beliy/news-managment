package edu.web.news.managment.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/general")
public class GeneralController {

	@RequestMapping("/changeLocale")
	public String changeLocale(@RequestParam(value = "lang", required = false) String lang, HttpServletRequest request,
			HttpServletResponse response) {
		if (lang == null || lang.isEmpty()) {
			lang = "en";
		}

		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));

		String referer = request.getHeader("Referer");

		if (referer == null || referer.isEmpty()) {
			referer = "/";
		}

		return "redirect:" + referer;
	}

}
