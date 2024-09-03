package edu.web.news.managment.dao;

import java.util.List;

import edu.web.news.managment.beans.News;

public interface NewsDao {

	void addNews(News news);

	void deleteNews(int id);

	void editNews(News news);

	News findNewsById(int id);

	List<News> getNews();

	List<News> getNewsByCategory(String category);
}
