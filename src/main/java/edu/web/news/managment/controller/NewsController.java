package edu.web.news.managment.controller;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.web.news.managment.beans.News;
import edu.web.news.managment.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/startPage")
	public String goToStartPage(Model model) {
		List<News> newsList = newsService.getNews();
		model.addAttribute("mainNews", newsList);
		return "/start_page";
	}

	@RequestMapping("/goToAddArticle")
	public String goToAddArticlePage(Model model) {
		model.addAttribute("news", new News());
		return "/add_article_page";
	}

	@RequestMapping("/goToEditArticle")
	public String goToEditArticle(@RequestParam("id") int id, Model model) {
		News news = newsService.findNewsById(id);
		model.addAttribute("editArticle", news);
		return "/edit_article";
	}

	@RequestMapping("/addArticle")
	public String addArticle(@ModelAttribute("news") News news, Model model, Locale locale) {
		newsService.addNews(news);
		model.addAttribute("message", messageSource.getMessage("message.article.add.successfully", null, locale));
		return "/add_article_page";
	}

	@RequestMapping("/viewArticle")
	public String viewArticle(@RequestParam("id") int id, Model model) {
		News news = newsService.findNewsById(id);
		model.addAttribute("article", news);
		return "/viewArticle";
	}

	@RequestMapping("/showArticleFromCategory")
	public String showArticleFromCategory(@RequestParam("category") String category, Model model) {
		List<News> listNews = newsService.getNewsByCategory(category);
		model.addAttribute("listNews", listNews);
		return "/article_by_category";
	}

	@RequestMapping("/editArticle")
	public String editArticle(@ModelAttribute("editArticle") News news) {

		newsService.editNews(news);
		return "redirect:/news/startPage"; // Перенаправление на главную страницу после успешного обновления

	}

	@RequestMapping("/deleteArticle")
	public String deleteArticle(@RequestParam("id") int id) {
		newsService.deleteNews(id);
		return "redirect:/news/startPage";

	}

}
