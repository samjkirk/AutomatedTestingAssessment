package com.qa.quickstart.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver myDriver;
	
	// Get element variables
	@FindBy(id = "txtUsername")
	private WebElement username;
	@FindBy(id = "txtPassword")
	private WebElement password;
	@FindBy(id = "btnLogin")
	private WebElement loginButton;
	
	// Set WebDriver
	public LoginPage(WebDriver myDriver) {
		this.myDriver = myDriver;
	}
	
	// Login
	public void login() {
		username.sendKeys("Admin");
		password.sendKeys("admin");
		loginButton.click();
	}
}
