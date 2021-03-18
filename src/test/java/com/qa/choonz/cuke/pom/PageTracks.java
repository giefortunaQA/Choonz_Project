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
	
	@FindBy(xpath = "//*[@id=\"updateEachGenre\"]")
	private WebElement buttonUpdateTrack;
	
	@FindBy(xpath = "//*[@id=\"deleteEachTrack\"]")
	private WebElement buttonDeleteTrack;
	
	@FindBy(xpath = "//*[@id=\"trackNameUpdate\"]")
	private WebElement inputUpdateTrackName;
	
	@FindBy(xpath = "//*[@id=\"trackLyricsUpdate\"]")
	private WebElement inputUpdateTrackLyrics;
	
	@FindBy(xpath = "//*[@id=\"trackDurationUpdate\"]")
	private WebElement inputUpdateTrackDuration;
	
	@FindBy(xpath = "//*[@id=\"trackAlbumUpdate\"]")
	private WebElement inputUpdateTrackAlbumId;
	
	@FindBy(xpath = "//*[@id=\"trackPlaylistUpdate\"]")
	private WebElement inputUpdateTrackPlaylistId;
	
	@FindBy(xpath = "//*[@id=\"updateTrackBtn\"]")
	private WebElement buttonUpdateTrackSubmit;
	
	@FindBy(xpath = "//*[@id=\"eachTrackDiv\"]")
	private WebElement textDeleteTrack;
	
	@FindBy(xpath = "/html/body/div/div/div[2]/div[1]/h1")
	private WebElement textTrackNameSingle;
	
	public String getTrackNameSingle() {
		return textTrackNameSingle.getText();
	}
	
	public String getDeleteTrackText() {
		return textDeleteTrack.getText();
	}
	
	public void inputUpdateTrackName(String name) {
		inputUpdateTrackName.sendKeys(name);
	}
	
	public void inputUpdateTrackLyrics(String lyrics) {
		inputUpdateTrackLyrics.sendKeys(lyrics);
	}
	
	public void inputUpdateTrackDuration(String duration) {
		inputUpdateTrackDuration.sendKeys(duration);
	}
	
	public void inputUpdateTrackAlbumId(String id) {
		inputUpdateTrackAlbumId.sendKeys(id);
	}
	
	public void inputUpdateTrackPlaylistId(String id) {
		inputUpdateTrackPlaylistId.sendKeys(id);
	}
	
	public void clickUpdateTrackSubmitButton() {
		buttonUpdateTrackSubmit.click();
	}
	
	public void clickUpdateTrackButton() {
		buttonUpdateTrack.click();
	}
	
	public void clickDeleteTrackButton() {
		buttonDeleteTrack.click();
	}
	
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
