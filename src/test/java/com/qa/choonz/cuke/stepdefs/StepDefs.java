package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.choonz.cuke.pom.PageArtists;
import com.qa.choonz.cuke.pom.PageBase;
import com.qa.choonz.cuke.pom.PageGenres;
import com.qa.choonz.cuke.pom.PagePlaylists;
import com.qa.choonz.cuke.pom.PageUser;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {"/Choonz_Project/src/test/resources/Choonz-schema.sql",
			"/Choonz_Project/src/test/resources/data.sql"
			},
	executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StepDefs {
	
	private static RemoteWebDriver driver;
	
	private static PageBase base;
	private static PageUser userPage;
	private static PageArtists artistsPage;
	private static PageGenres genresPage;
	private static PagePlaylists playlistsPage;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();//chromeCfg());
	}
	
	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
	    driver.get(string);
		
		base = PageFactory.initElements(driver, PageBase.class);
		userPage = PageFactory.initElements(driver, PageUser.class);
		artistsPage = PageFactory.initElements(driver, PageArtists.class);
		genresPage = PageFactory.initElements(driver, PageGenres.class);
		playlistsPage = PageFactory.initElements(driver, PagePlaylists.class);
	}
	
	@Given("that I can genre {string}")
	public void that_i_can_genre(String string) {
	    driver.get(string);
		
		base = PageFactory.initElements(driver, PageBase.class);
		userPage = PageFactory.initElements(driver, PageUser.class);
		artistsPage = PageFactory.initElements(driver, PageArtists.class);
		base.waitUntilPageLoad(driver);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("I go home")
	public void i_go_home() {
		hang();
	    userPage.navHome();
	}
	
	@When("I toggle the navbar")
	public void i_toggle_the_navbar() {
		hang();
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
	}
	
	@When("I accept the alert")
	public void i_accept_the_alert() {
		driver.switchTo().alert().accept();
	}

	@When("I enter a username of {string} in the signup form")
	public void i_enter_a_username_of_in_the_signup_form(String string) {
		hang();
	    userPage.inputSignUpUsername(string);
	}
	
	@When("I enter a username of {string} in the login form")
	public void i_enter_a_username_of_in_the_login_form(String string) {
		hang();
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
		hang();
	    userPage.navLogin();
		
	}

	@When("I submit the login form")
	public void i_submit_the_login_form() {
	    userPage.clickLoginSubmit();
	}

	@When("I click the create artist button")
	public void i_click_the_create_artist_button() {
		hang();
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
	
	@When("I select an artist")
	public void i_select_an_artist() {
		hang();
	    artistsPage.clickArtistName();
	}
	
	@When("I select a genre")
	public void i_select_a_genre() {
		hang();
	    genresPage.clickTextGenreName();
	}

	@When("I click the update artist button")
	public void i_click_the_update_artist_button() {
		hang();
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
	
	@When("I click the delete user button")
	public void i_click_the_delete_user_button() {
		hang();
	    userPage.clickDeleteUser();
	}
	
	@When("I click the delete artist button")
	public void i_click_the_delete_artist_button() {
	    hang();
	    artistsPage.clickDeleteArtist();
	}
	
	@When("I navigate to the genres page")
	public void i_navigate_to_the_genres_page() {
		base.waitUntilNavExpand(driver);
	    base.navGenres();
	}
	
	@When("I click the create genre button")
	public void i_click_the_create_genre_button() {
	    hang();
	    genresPage.clickCreateGenreButton();
	}
	
	@When("I enter the create genre details:")
	public void i_enter_the_create_genre_details(Map<String, String> dataTable) {
		hang();
		String
			genreName,
			genreDescription;
			
		genreName = dataTable.get("genre name");
		genreDescription = dataTable.get("genre description");
		
		genresPage.inputCreateGenreName(genreName);
		genresPage.inputCreateGenreDescription(genreDescription);
	}
	
	@When("I submit the create genre form")
	public void i_submit_the_create_genre_form() {
	    genresPage.clickCreateGenreSubmitButton();
	}
	
	@When("I submit the update genre form")
	public void i_submit_the_update_genre_form() {
	    genresPage.clickUpdateGenreButtonSubmit();
	}
	
	@When("I click the update genre button")
	public void i_click_the_update_genre_button() {
	    hang();
		genresPage.clickUpdateGenreButton();
	}
	
	@When("I enter the update genre details:")
	public void i_enter_the_update_genre_details(Map<String, String> dataTable) {
	    hang();
		String
			genreUpdatedName,
			genreUpdatedDescription;
			
		genreUpdatedName = dataTable.get("genre updated name");
		genreUpdatedDescription = dataTable.get("genre updated description");
		
		genresPage.inputUpdateGenreName(genreUpdatedName);
		genresPage.inputUpdateGenreDescription(genreUpdatedDescription);
	}
	
	@When("I click the delete genre button")
	public void i_click_the_delete_genre_button() {
		hang();
	    genresPage.clickDeleteGenre();
	}
	
	@When("I navigate to the playlists page")
	public void i_navigate_to_the_playlists_page() {
	    hang();
	    base.navPlaylists();
	}
	
	@When("I click the create playlist button")
	public void i_click_the_create_playlist_button() {
	    hang();
	    playlistsPage.clickCreatePlaylistButton();
	}
	
	@When("I enter the create playlist details:")
	public void i_enter_the_create_playlist_details(Map<String, String> dataTable) {
	    hang();
		String
			playlistName,
			playlistArtwork,
			playlistDescription;
			
		playlistName = dataTable.get("playlist name");
		playlistArtwork = dataTable.get("playlist artwork");
		playlistDescription = dataTable.get("playlist description");
		
		playlistsPage.inputCreatePlaylistName(playlistName);
		playlistsPage.inputCreatePlaylistArtwork(playlistArtwork);
		playlistsPage.inputCreatePlaylistDescription(playlistDescription);
	}
	
	@When("I submit the create playlist form")
	public void i_submit_the_create_playlist_form() {
	    playlistsPage.clickCreatePlaylistSubmitButton();
	}
	
	@Then("I can read a playlist with the name {string}")
	public void i_can_read_a_playlist_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = playlistsPage.getCreatePlaylistText();
	    assertEquals(expected,result);
	}

	@Then("I can read {string} on the genre page")
	public void i_can_read_on_the_genre_page(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getSingleGenreNameText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read an updated genre with the name {string}")
	public void i_can_read_an_updated_genre_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getSingleGenreNameText();
	    assertEquals(expected,result);
	}
	
	@Then("I can read a genre with the name {string}")
	public void i_can_read_a_genre_with_the_name(String string) {
	    hang();
	    String expected = string;
	    String result = genresPage.getTextGenreName();
	    assertEquals(expected,result);
	}
	
	@Then("I can read {string} on the artist page")
	public void i_can_read_on_the_artist_page(String string) {
	    hang();
	    String expected = string;
	    String result = artistsPage.getArtistDeletedText();
	    assertEquals(expected,result);
	}
	
	@Then("the user account is deleted")
	public void the_user_account_is_deleted() {
		hang();
		String expected = "User deleted.";
		String result = userPage.getUserDeleteText();
		assertEquals(expected,result);
	}
	
	@Then("I can read an updated artist with the name {string}")
	public void i_can_read_an_updated_artist_with_the_name(String string) {
		hang();
	    String expected = string;
	    String result = artistsPage.getArtistNameUpdated();
	    assertEquals(expected,result);
	}

	@Then("I can read a list of artists")
	public void i_can_read_a_list_of_artists() {
		hang();
		assertNotNull(artistsPage.getArtistName());
	}

	@Then("I can read a list of genres")
	public void i_can_read_a_list_of_genres() {
		hang();
		assertNotNull(genresPage.getTextGenreName());
	}

	@Then("I can read a list of playlists")
	public void i_can_read_a_list_of_playlists() {
		hang();
		assertNotNull(playlistsPage.getCreatePlaylistText());
	}
	
	@Then("I can read a single artist")
	public void i_can_read_a_single_artist() {
		hang();
		assertNotNull(artistsPage.getArtistNameUpdated());
	}
	
	@Then("I can read a single genre")
	public void i_can_read_a_single_genre() {
		hang();
		assertNotNull(genresPage.getSingleGenreNameText());
	}

	@Then("I can read an artist with the name {string}")
	public void i_can_read_an_artist_with_the_name(String string) {
		hang();
	    String expected = string;
	    String result = artistsPage.getArtistName();
	    assertEquals(expected,result);
	}

	@Then("I see the text {string}")
	public void i_see_the_text(String string) {
		hang();
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
	
	public static void hang() {
		base.waitUntilPageLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static ChromeOptions chromeCfg() {
//	     Map<String, Object> prefs = new HashMap<String, Object>();
//	     ChromeOptions cOptions = new ChromeOptions();
//	      
//	     // Settings
//	     prefs.put("profile.default_content_setting_values.cookies", 2);
//	     prefs.put("network.cookie.cookieBehavior", 2);
//	     prefs.put("profile.block_third_party_cookies", true);
//	
//	     // Create ChromeOptions to disable Cookies pop-up
//	     cOptions.setExperimentalOption("prefs", prefs);
//	
//	     return cOptions;
//    }
   
}
