package edu.web.news.managment.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "news")
public class News {

	public News() {

	}

	public News(int id, String title, String category, String content, String imgPath) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
		this.imgPath = imgPath;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "content")
	private String content;

	@Column(name = "imgPath")
	private String imgPath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		 this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", category=" + category + ", content=" + content + ", imgPath="
				+ imgPath + "]";
	}
	
	

}
