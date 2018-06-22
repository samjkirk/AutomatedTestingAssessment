package com.qa.quickstart.Assessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainDashboard {
	WebDriver myDriver;
	
	// Get element variables
	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement Menu_PIM;
	@FindBy(id = "menu_pim_addEmployee")
	private WebElement Menu_addEmployee;
	

	// Set WebDriver
	public MainDashboard(WebDriver myDriver) {
		this.myDriver = myDriver;
	}
	
	// Navigate to add employee tab
	public void navigateToAddEmployee() {
		Actions action = new Actions(myDriver);
		action.moveToElement(Menu_PIM).moveByOffset(0, -20).moveToElement(Menu_addEmployee).click().perform();
	}
}
