package edu.web.news.managment.dao;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}
}
