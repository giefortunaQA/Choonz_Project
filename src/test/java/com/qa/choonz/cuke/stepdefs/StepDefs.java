package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.choonz.cuke.pom.PageArtists;
import com.qa.choonz.cuke.pom.PageBase;
import com.qa.choonz.cuke.pom.PageUser;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	private static RemoteWebDriver driver;
	
	private static PageBase base;
	private static PageUser userPage;
	private static PageArtists artistsPage;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
	    driver.get(string);
		
		base = PageFactory.initElements(driver, PageBase.class);
		userPage = PageFactory.initElements(driver, PageUser.class);
		artistsPage = PageFactory.initElements(driver, PageArtists.class);
	}
	
	@When("I go home")
	public void i_go_home() {
		base.waitUntilPageLoad(driver);
	    userPage.navHome();
	}
	
	@When("I toggle the navbar")
	public void i_toggle_the_navbar() {
		base.waitUntilPageLoad(driver);
		base.toggleNav();
	}

	@When("I click the account button")
	public void i_click_the_account_button() {
		base.waitUntilNavExpand(driver);
	    base.navLogin();
	}
	
	@When("I navigate to the artists page")
	public void i_navigate_to_the_artists_page() {
		base.waitUntilNavExpand(driver);
	    base.navArtists();
	}

	@When("I dismiss the alert")
	public void i_cancel_the_alert() {
	    driver.switchTo().alert().dismiss();
		base.waitUntilPageLoad(driver);
	}
	
	@When("I accept the alert")
	public void i_accept_the_alert() {
		driver.switchTo().alert().accept();
		base.waitUntilPageLoad(driver);
	}

	@When("I enter a username of {string} in the signup form")
	public void i_enter_a_username_of_in_the_signup_form(String string) {
	    userPage.inputSignUpUsername(string);
	}
	
	@When("I enter a username of {string} in the login form")
	public void i_enter_a_username_of_in_the_login_form(String string) {
	    userPage.inputLoginUsername(string);
	}

	@When("I enter a password of {string} in the signup form")
	public void i_enter_a_password_of_in_the_signup_form(String string) {
	    userPage.inputSignUpPassword(string);
	}
	
	@When("I enter a password of {string} in the login form")
	public void i_enter_a_password_of_in_the_login_form(String string) {
	    userPage.inputLoginPassword(string);
	}

	@When("I submit the sign up form")
	public void i_submit_the_sign_up_form() {
	    userPage.clickSignUpSubmit();
	}
	
	@When("I click the sign in link")
	public void i_click_the_sign_in_link() {
		base.waitUntilPageLoad(driver);
	    userPage.navLogin();
		base.waitUntilPageLoad(driver);
	}

	@When("I submit the login form")
	public void i_submit_the_login_form() {
	    userPage.clickLoginSubmit();
	}

	@When("I click the create artist button")
	public void i_click_the_create_artist_button() {
		base.waitUntilPageLoad(driver);
	    artistsPage.clickCreateArtist();
	}

	@When("I enter the create artist details:")
	public void i_enter_the_create_artist_details(Map<String, String> dataTable) {
	    
		String
			artistName;
		
		artistName = dataTable.get("artist name");
		
		artistsPage.inputCreateArtistName(artistName);
		
	}

	@When("I submit the create artist form")
	public void i_submit_the_create_artist_form() {
		artistsPage.clickCreateArtistSubmit();
	}
	
	@When("I select the artist {string}")
	public void i_select_the_artist(String string) {
	    artistsPage.clickArtistName();
	}

	@When("I click the update artist button")
	public void i_click_the_update_artist_button() {
	    artistsPage.clickUpdateArtist();
	}

	@When("I enter the update artist details:")
	public void i_enter_the_update_artist_details(Map<String, String> dataTable) {

		String
			artistUpdatedName;
		
		artistUpdatedName = dataTable.get("artist updated name");
		
		artistsPage.inputUpdateArtistName(artistUpdatedName);
	}

	@When("I submit the update artist form")
	public void i_submit_the_update_artist_form() {
	    artistsPage.clickUpdateArtistSubmit();
	}

	@Then("I can read an updated artist with the name {string}")
	public void i_can_read_an_updated_artist_with_the_name(String string) {
		base.waitUntilPageLoad(driver);
	    String expected = string;
	    String result = artistsPage.getArtistNameUpdated();
	    assertEquals(expected,result);
	}

	@Then("I can read an artist with the name {string}")
	public void i_can_read_an_artist_with_the_name(String string) {
		base.waitUntilPageLoad(driver);
	    String expected = string;
	    String result = artistsPage.getArtistName();
	    assertEquals(expected,result);
	}

	@Then("I see the text {string}")
	public void i_see_the_text(String string) {
		base.waitUntilPageLoad(driver);
	    String expected = string;
	    String result = userPage.getResultText();
	    assertEquals(expected,result);
	}

	@Then("I can see the logout button")
	public void i_can_see_the_logout_button() {
		base.waitUntilNavExpand(driver);
		String expected = "Logout";
		String result = userPage.getLogoutText();
		assertEquals(expected,result);
	}
	
	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
}
