package com.qa.choonz.pom.cuke.stepdefs;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.pom.pages.PageBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	private static RemoteWebDriver driver;
	private static PageBase base = PageFactory.initElements(driver, PageBase.class);

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I click the account button")
	public void i_click_the_account_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I cancel the alert")
	public void i_cancel_the_alert() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I enter a username of {string}")
	public void i_enter_a_username_of(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I enter a password of {string}")
	public void i_enter_a_password_of(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I submit the sign up form")
	public void i_submit_the_sign_up_form() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I see the text {string}")
	public void i_see_the_text(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
}
