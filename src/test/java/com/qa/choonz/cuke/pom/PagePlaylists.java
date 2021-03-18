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
	
	@FindBy(xpath = "//*[@id=\"playlists1\"]/button/h3")
	private WebElement textPlaylistName;
	
	@FindBy(xpath = "/html/body/div/div[1]/div[2]/div/div/h1")
	private WebElement textPlaylistNameSingle;
	
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
	
}
