package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageTracks extends PageBase {
	
	@FindBy(xpath = "/html/body/div[1]/button")
	private WebElement buttonCreateTrack;
	
	@FindBy(xpath = "//*[@id=\"trackName\"]")
	private WebElement inputCreateTrackName;
	
	@FindBy(xpath = "//*[@id=\"trackLyrics\"]")
	private WebElement inputCreateTrackLyrics;
	
	@FindBy(xpath = "//*[@id=\"trackDuration\"]")
	private WebElement inputCreateTrackDuration;
	
	@FindBy(xpath = "//*[@id=\"trackAlbum\"]")
	private WebElement inputCreateTrackAlbumId;
	
	@FindBy(xpath = "//*[@id=\"trackPlaylist\"]")
	private WebElement inputCreateTrackPlaylistId;
	
	@FindBy(xpath = "/html/body/div[1]/div/span[1]/button")
	private WebElement buttonCreateTrackSubmit;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/button/h3")
	private WebElement textTrackName;
	
	public void clickCreateTrackButton() {
		buttonCreateTrack.click();
	}
	
	public void inputCreateTrackName(String name) {
		inputCreateTrackName.sendKeys(name);
	}
	
	public void inputCreateTrackLyrics(String lyrics) {
		inputCreateTrackLyrics.sendKeys(lyrics);
	}
	
	public void inputCreateTrackDuration(String duration) {
		inputCreateTrackDuration.sendKeys(duration);
	}
	
	public void inputCreateTrackAlbumId(String id) {
		inputCreateTrackAlbumId.sendKeys(id);
	}
	
	public void inputCreateTrackPlaylistId(String id) {
		inputCreateTrackPlaylistId.sendKeys(id);
	}
	
	public void clickCreateTrackSubmitButton() {
		buttonCreateTrackSubmit.click();
	}
	
	public String getTrackNameText() {
		return textTrackName.getText();
	}
	
	public void clickTrackNameText() {
		textTrackName.click();
	}
}
