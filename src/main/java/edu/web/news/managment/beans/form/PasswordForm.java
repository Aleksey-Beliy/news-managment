package edu.web.news.managment.beans.form;

import jakarta.validation.constraints.NotEmpty;

public class PasswordForm {

	@NotEmpty
	private String currentPassword;

	@NotEmpty
	private String newPassword;

	@NotEmpty
	private String confirmPassword;
	
	public PasswordForm() {
		
	}

	public PasswordForm(@NotEmpty String currentPassword,
			@NotEmpty String newPassword,
			@NotEmpty String confirmPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
