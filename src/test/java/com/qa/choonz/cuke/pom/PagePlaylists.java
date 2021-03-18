package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PagePlaylists extends PageBase {
	
	@FindBy(xpath = "/html/body/div[1]/button/span[1]")
	private WebElement buttonCreatePlaylist;
	
	@FindBy(xpath = "//*[@id=\"playlistName\"]")
	private WebElement inputCreatePlaylistName;
	
	@FindBy(xpath = "//*[@id=\"playlistArtwork\"]")
	private WebElement inputCreatePlaylistArtwork;
	
	@FindBy(xpath = "//*[@id=\"playlistDesc\"]")
	private WebElement inputCreatePlaylistDescription;
	
	@FindBy(xpath = "//*[@id=\"createPlaylistForm\"]/span[1]/button")
	private WebElement buttonCreatePlaylistSubmit;
	
	// this will need to be changed from playlists1 to playlistsX
	// when the data.sql is used for testing
	@FindBy(xpath = "//*[@id=\"playlists1\"]/button/h3")
	private WebElement textPlaylistName;
	
	@FindBy(xpath = "/html/body/div/div[1]/div[2]/div/div/h1")
	private WebElement textPlaylistNameSingle;
	
	@FindBy(xpath = "//*[@id=\"playlistNameDisplay\"]")
	private WebElement textPlaylistNameDisplay;
	
	@FindBy(xpath = "//*[@id=\"updateEachPlaylist\"]")
	private WebElement buttonUpdatePlaylist;
	
	@FindBy(xpath = "//*[@id=\"deleteEachPlaylist\"]")
	private WebElement buttonDeletePlaylist;
	
	@FindBy(xpath = "//*[@id=\"playlistNameUpdate\"]")
	private WebElement inputUpdatePlaylistName;
	
	@FindBy(xpath = "//*[@id=\"playlistArtworkUpdate\"]")
	private WebElement inputUpdatePlaylistArtwork;
	
	@FindBy(xpath = "//*[@id=\"playlistDescUpdate\"]")
	private WebElement inputUpdatePlaylistDescription;
	
	@FindBy(xpath = "//*[@id=\"updatePlaylistBtn\"]")
	private WebElement buttonUpdatePlaylistSubmit;
	
	public void clickCreatePlaylistButton() {
		buttonCreatePlaylist.click();
	}

	public void inputCreatePlaylistName(String name) {
		inputCreatePlaylistName.sendKeys(name);
	}
	
	public void inputCreatePlaylistArtwork(String url) {
		inputCreatePlaylistArtwork.sendKeys(url);
	}
	
	public void inputCreatePlaylistDescription(String description) {
		inputCreatePlaylistDescription.sendKeys(description);
	}
	
	public void clickCreatePlaylistSubmitButton() {
		buttonCreatePlaylistSubmit.click();
	}
	
	public String getCreatePlaylistText() {
		return textPlaylistName.getText();
	}
	
	public void clickPlaylistName() {
		textPlaylistName.click();
	}
	
	public String getSinglePlaylistText() {
		return textPlaylistNameSingle.getText();
	}
	
	public void clickUpdatePlaylistButton() {
		buttonUpdatePlaylist.click();
	}
	
	public void clickUpdatePlaylistSubmitButton() {
		buttonUpdatePlaylistSubmit.click();
	}
	
	public void clickDeletePlaylistButton() {
		buttonDeletePlaylist.click();
	}
	
	public void inputUpdatePlaylistName(String name) {
		inputUpdatePlaylistName.sendKeys(name);
	}
	
	public void inputUpdatePlaylistArtwork(String url) {
		inputUpdatePlaylistArtwork.sendKeys(url);
	}
	
	public void inputUpdatePlaylistDescription(String description) {
		inputUpdatePlaylistDescription.sendKeys(description);
	}
	
	public String getDeletePlaylistText() {
		return textPlaylistNameDisplay.getText();
	}
}
