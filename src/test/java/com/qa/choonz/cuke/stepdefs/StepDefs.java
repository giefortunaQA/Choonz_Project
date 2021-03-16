package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.cuke.pom.PageBase;
import com.qa.choonz.cuke.pom.PageUser;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	private static RemoteWebDriver driver;
	private static PageBase base = PageFactory.initElements(driver, PageBase.class);
    private static PageUser userPage = PageFactory.initElements(driver, PageUser.class);

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
	    driver.get(string);
	}
	
	@When("I toggle the navbar")
	public void i_toggle_the_navbar() {
		base.waitUntilNavToggle(driver);
		base.toggleNav();
	}

	@When("I click the account button")
	public void i_click_the_account_button() {
		base.waitUntilUserLink(driver);
	    base.navLogin();
	}

	@When("I cancel the alert")
	public void i_cancel_the_alert() {
	    driver.switchTo().alert().dismiss();
	}

	@When("I enter a username of {string}")
	public void i_enter_a_username_of(String string) {
	    userPage.inputUsername(string);
	}

	@When("I enter a password of {string}")
	public void i_enter_a_password_of(String string) {
	    userPage.inputPassword(string);
	}

	@When("I submit the sign up form")
	public void i_submit_the_sign_up_form() {
	    userPage.clickSubmit();
	}

	@Then("I see the text {string}")
	public void i_see_the_text(String string) {
	    String expected = string;
	    String result = userPage.getResultText();
	    assertEquals(expected,result);
	}
	
	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
}
