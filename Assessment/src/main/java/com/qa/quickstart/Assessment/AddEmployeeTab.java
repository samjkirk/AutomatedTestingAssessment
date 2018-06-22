package com.qa.quickstart.Assessment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmployeeTab {
	WebDriver myDriver;
	
	// Get element variables
	@FindBy(id = "firstName")
	private WebElement firstName;
	@FindBy(id = "middleName")
	private WebElement middleName;
	@FindBy(id = "lastName")
	private WebElement lastName;
	@FindBy(id = "employeeId")
	private WebElement employeeId;
	@FindBy(id = "photofile")
	private WebElement photoFile;
	@FindBy(id = "chkLogin")
	private WebElement chkLogin;
	@FindBy(id = "user_name")
	private WebElement user_name;
	@FindBy(id = "user_password")
	private WebElement user_password;
	@FindBy(id = "re_password")
	private WebElement re_password;
	@FindBy(id = "status")
	private WebElement status;
	@FindBy(id = "btnSave")
	private WebElement saveButton;
	
	// Set WebDriver
	public AddEmployeeTab(WebDriver myDriver) {
		this.myDriver = myDriver;
	}
	
	// Fill out employee details
	public void fillOutDetails(	String first_Name, String middle_Name, String last_Name, String employee_id,String photoFilePATH) {
		firstName.sendKeys(first_Name);
		middleName.sendKeys(middle_Name);
		lastName.sendKeys(last_Name);
		employeeId.clear();
		employeeId.sendKeys(employee_id);
		photoFile.sendKeys(photoFilePATH);
	}
	
	// Check create login details box
	public void chooseToCreateLoginDetails() {
		chkLogin.click();
	}
	
	// Fill out login details
	public void fillOutLoginDetails(String username, String password, boolean enabled) {
		Actions action = new Actions(myDriver);
		
		user_name.sendKeys(username);
		user_password.sendKeys(password);
		re_password.sendKeys(password);
		
		// Enabled/Disabled
		if (!enabled) {
			status.click();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).perform();
		}
	}
	
	// Save details
	public void saveDetails() {
		saveButton.click();
	}
}
