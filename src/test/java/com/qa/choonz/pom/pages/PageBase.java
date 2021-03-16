package com.qa.choonz.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageBase {
	
	@FindBy(xpath = "/html/body/nav/a")
	private WebElement linkHome;
	
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
	
}
