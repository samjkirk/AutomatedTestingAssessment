package com.qa.quickstart.Assessment;

import static org.junit.Assert.assertNotNull;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddEmployee {
	// Report and driver
	static ExtentReports OrangeHRM_REPORT;
	ExtentTest addNewUser;
	ChromeDriver myDriver;
	
	// POM pages
	LoginPage login_page;
	MainDashboard main_dashboard;
	AddEmployeeTab add_employee_tab;
	InformationTab info_tab;
	
	// Navigate to the add employee tab
	@Given("^the Add Employee Tab$")
	public void the_Add_Employee_Tab() {
		
		// Set ChromeDriver PATH 
		// Set ExtentReport PATH 
		// Start test
		System.setProperty("webdriver.chrome.driver", Constant.DRIVER_PATH);
		OrangeHRM_REPORT = new ExtentReports (Constant.REPORT_PATH, true);
		addNewUser = OrangeHRM_REPORT.startTest("Testing the add new user function...");
		
		// Start Browser
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		assertNotNull(myDriver);
		addNewUser.log(LogStatus.INFO, "Broswer started");
		
		// Navigate to OrangeHRM dashboard
		myDriver.navigate().to(Constant.URL1);
		addNewUser.log(LogStatus.INFO, "Current URL: " + myDriver.getCurrentUrl());
		
		// Login
		login_page = PageFactory.initElements(myDriver, LoginPage.class);
		login_page.login(Constant.ADMIN_USERNAME, Constant.ADMIN_PASSWORD);
		addNewUser.log(LogStatus.INFO, "Successfully logged in..." + " | Current URL: " + myDriver.getCurrentUrl());
		
		// Navigate to add employee tab
		main_dashboard = PageFactory.initElements(myDriver, MainDashboard.class);
		main_dashboard.navigateToAddEmployee();
		addNewUser.log(LogStatus.INFO, "Successfully navigated to Add Employee tab" + " | Current URL: " + myDriver.getCurrentUrl());
	}

	@When("^I fill out the Employee Details correctly$")
	public void i_fill_out_the_Employee_Details_correctly() {
	    add_employee_tab = PageFactory.initElements(myDriver, AddEmployeeTab.class);
	    // Fill out employee details
	    add_employee_tab.fillOutDetails(Constant.employeeFirstName, Constant.employeeMiddleName, Constant.employeeLastName, Constant.employeeID, Constant.PATH);
	    addNewUser.log(LogStatus.INFO, "Successfully filled out employee details" + " | Current URL: " + myDriver.getCurrentUrl());
	}

	@When("^I choose to create Login Details$")
	public void i_choose_to_create_Login_Details() {
		// Check the "Create login details box"
		add_employee_tab.chooseToCreateLoginDetails();
		addNewUser.log(LogStatus.INFO, "Chosen to create login details" + " | Current URL: " + myDriver.getCurrentUrl());
	}

	@When("^I fill out the Login Details correctly$")
	public void i_fill_out_the_Login_Details_correctly() {
		// Send login details
	    add_employee_tab.fillOutLoginDetails(Constant.employeeUsername, Constant.employeePassword, false);
	    addNewUser.log(LogStatus.INFO, "Successfully filled out login details" + " | Current URL: " + myDriver.getCurrentUrl());
	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() {
		// Click save details
		try {
			add_employee_tab.saveDetails();
			Assert.assertFalse(myDriver.findElementByClassName("message").isDisplayed());
		    addNewUser.log(LogStatus.INFO, "Successfully saved details" + " | Current URL: " + myDriver.getCurrentUrl());
		} catch (AssertionError e) {
			addNewUser.log(LogStatus.FAIL, "User already exists. Failed to create new user." + " | Current URL: " + myDriver.getCurrentUrl());
			OrangeHRM_REPORT.flush();
		    myDriver.quit();
			fail();
		} 
	}

	@Then("^I can see information about the user$")
	public void i_can_see_information_about_the_user() {
		info_tab = PageFactory.initElements(myDriver, InformationTab.class);
		// Check to see if new user/employee was created
		try {
			assertEquals("Drizzy", info_tab.returnUserFirstName());
			addNewUser.log(LogStatus.PASS, "Successfully added a new employee" + " | Current URL: " + myDriver.getCurrentUrl());
		} catch (AssertionError e) {
			addNewUser.log(LogStatus.FAIL, "Failed to add a new employee" + " | Current URL: " + myDriver.getCurrentUrl());
			fail();
		} finally {
			OrangeHRM_REPORT.flush();
		    myDriver.quit();
		}
	}
}
