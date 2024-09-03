package edu.web.news.managment.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

	public User() {

	}

	public User(int id, String name, UserRole userRole, UserData userData) {
		super();
		this.id = id;
		this.name = name;
		this.userRole = userRole;
		this.userData = userData;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRole userRole;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_data_id")
	private UserData userData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userRole=" + userRole + ", userData=" + userData + "]";
	}

}
