package edu.web.news.managment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.web.news.managment.beans.News;
import edu.web.news.managment.dao.DaoException;
import edu.web.news.managment.dao.NewsDao;
import edu.web.news.managment.service.NewsService;
import edu.web.news.managment.service.ServiceException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	@Override
	public void addNews(News news) {
		newsDao.addNews(news);
	}

	@Override
	public void deleteNews(int id) {
		newsDao.deleteNews(id);

	}

	@Override
	public void editNews(News news) {

		newsDao.editNews(news);

	}

	@Override
	public News findNewsById(int id) {
		return newsDao.findNewsById(id);
	}

	@Override
	public List<News> getNews() {
		return newsDao.getNews();

	}

	@Override
	public List<News> getNewsByCategory(String category) {
		return newsDao.getNewsByCategory(category);
	}

}
