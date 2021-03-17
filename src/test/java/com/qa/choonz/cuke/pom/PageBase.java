package com.qa.choonz.cuke.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	@FindBy(xpath = "/html/body/nav/a")
	private WebElement linkHome;
	
	@FindBy(xpath = "/html/body/nav/button")
	private WebElement toggleNav;
	
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")
	private WebElement linkTracks;
	
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a")
	private WebElement linkArtists;
	
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[3]/a")
	private WebElement linkAlbums;
	
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a")
	private WebElement linkPlaylists;
	
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[5]/a")
	private WebElement linkGenres;
	
	@FindBy(xpath = "//*[@id=\"userDisplay\"]/button")
	private WebElement linkUser;
	
	@FindBy(xpath = "//*[@id=\"userDisplay\"]/button[2]")
	private WebElement linkLogout;
	
	public void navHome() {
		linkHome.click();
	}
	
	public void toggleNav() {
		toggleNav.click();
	}
	
	public void navTracks() {
		linkTracks.click();
	}
	
	public void navArtists() {
		linkArtists.click();
	}
	
	public void navAlbums() {
		linkAlbums.click();
	}
	
	public void navPlaylists() {
		linkPlaylists.click();
	}
	
	public void navGenres() {
		linkGenres.click();
	}
	
	public void navLogin() {
		linkUser.click();
	}
	
	public void navLogout() {
		linkLogout.click();
	}
	
	public String getLogoutText() {
		return linkLogout.getText();
	}
	
//	public void waitUntilPageLoad(WebDriver driver) {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//	}
//	
	
	public void waitUntilNavToggle(WebDriver driver) {
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/nav/button")));
	}
	
//	public void waitUntilUserLink(WebDriver driver) {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userDisplay")));
//	}
	
//	public void waitFive(WebDriver driver) {
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	}
	
}
