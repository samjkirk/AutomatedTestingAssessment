package com.qa.quickstart.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationTab {
	
	// Get element variables
	@FindBy(id = "personal_txtEmpFirstName")
	private WebElement firstname;

	// Return new users first name from information page
	public String returnUserFirstName() {
		return firstname.getAttribute("value");
	}
}
