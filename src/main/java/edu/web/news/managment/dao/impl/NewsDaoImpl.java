package edu.web.news.managment.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.web.news.managment.beans.News;
import edu.web.news.managment.dao.DaoException;
import edu.web.news.managment.dao.NewsDao;

@Repository
public class NewsDaoImpl implements NewsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public NewsDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addNews(News news) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.persist(news);
	}

	@Override
	public void deleteNews(int id) {
		News news = findNewsById(id);
		if (news != null) {
			sessionFactory.getCurrentSession().remove(news);
		}
	}

	@Override
	public void editNews(News news) {

		Session currentSession = sessionFactory.getCurrentSession();
		News existingNews = currentSession.get(News.class, news.getId());
		if (existingNews != null) {
			existingNews.setTitle(news.getTitle());
			existingNews.setContent(news.getContent());
			existingNews.setCategory(news.getCategory());
			currentSession.merge(existingNews);
		}

	}

	@Override
	public News findNewsById(int id) {

		return sessionFactory.getCurrentSession().get(News.class, id);
	}

	@Override
	public List<News> getNews() {
		return sessionFactory.getCurrentSession().createQuery("from News order by id desc", News.class).getResultList();
	}

	@Override
	public List<News> getNewsByCategory(String category) {
		return sessionFactory.getCurrentSession()
				.createQuery("from News where category = :category order by id desc", News.class)
				.setParameter("category", category).getResultList();
	}

}
