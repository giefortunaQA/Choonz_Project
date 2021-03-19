package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAlbums extends PageBase {
	
	@FindBy(xpath = "/html/body/div[1]/button/span[1]")
	private WebElement buttonCreateAlbum;
	
	@FindBy(xpath = "//*[@id=\"albumName\"]")
	private WebElement inputCreateAlbumName;
	
	@FindBy(xpath = "//*[@id=\"albumCover\"]")
	private WebElement inputCreateAlbumCover;
	
	@FindBy(xpath = "//*[@id=\"album_artistId\"]")
	private WebElement inputCreateAlbumArtistId;
	
	@FindBy(xpath = "//*[@id=\"album_genreId\"]")
	private WebElement inputCreateAlbumGenreId;
	
	@FindBy(xpath = "/html/body/div[1]/div/span[1]/button")
	private WebElement buttonCreateAlbumSubmit;
	
	// this might need to be changed after data.sql is added
	@FindBy(xpath = "/html/body/div[2]/div[1]/div/button/h3")
	private WebElement textAlbumName;
	
	@FindBy(xpath = "/html/body/div/div[1]/div[2]/div/div/h1")
	private WebElement textAlbumNameSingle;
	
	@FindBy(xpath = "//*[@id=\"albumNameDisplay\"]")
	private WebElement textAlbumNameDisplay;
	
	@FindBy(xpath = "//*[@id=\"updateEachAlbum\"]")
	private WebElement buttonUpdateAlbum;
	
	@FindBy(xpath = "//*[@id=\"albumNameUpdate\"]")
	private WebElement inputUpdateAlbumName;
	
	@FindBy(xpath = "//*[@id=\"albumCoverUpdate\"]")
	private WebElement inputUpdateAlbumCover;
	
	@FindBy(xpath = "//*[@id=\"albumArtistUpdate\"]")
	private WebElement inputUpdateAlbumArtistId;
	
	@FindBy(xpath = "//*[@id=\"albumGenreUpdate\"]")
	private WebElement inputUpdateAlbumGenreId;
	
	@FindBy(xpath = "//*[@id=\"updateAlbumBtn\"]")
	private WebElement buttonUpdateAlbumSubmit;
	
	@FindBy(xpath = "//*[@id=\"deleteEachAlbum\"]")
	private WebElement buttonDeleteAlbum;
	
	public void clickCreateAlbumButton() {
		buttonCreateAlbum.click();
	}
	
	public void inputCreateAlbumName(String name) {
		inputCreateAlbumName.sendKeys(name);
	}
	
	public void inputCreateAlbumCover(String url) {
		inputCreateAlbumCover.sendKeys(url);
	}
	
	public void inputCreateAlbumArtistId(String id) {
		inputCreateAlbumArtistId.sendKeys(id);
	}
	
	public void inputCreateAlbumGenreId(String id) {
		inputCreateAlbumGenreId.sendKeys(id);
	}
	
	public void clickCreateAlbumSubmitButton() {
		buttonCreateAlbumSubmit.click();
	}
	
	public String getCreateAlbumText() {
		return textAlbumName.getText();
	}
	
	public void clickAlbumName() {
		textAlbumName.click();
	}
	
	public String getSingleAlbumName() {
		return textAlbumNameSingle.getText();
	}
	
	public void clickUpdateAlbumButton() {
		buttonUpdateAlbum.click();
	}
	
	public void inputUpdateAlbumName(String name) {
		inputUpdateAlbumName.sendKeys(name);
	}
	
	public void inputUpdateAlbumCover(String url) {
		inputUpdateAlbumCover.sendKeys(url);
	}
	
	public void inputUpdateAlbumArtistId(String id) {
		inputUpdateAlbumArtistId.sendKeys(id);
	}
	
	public void inputUpdateAlbumGenreId(String id) {
		inputUpdateAlbumGenreId.sendKeys(id);
	}
	
	public void clickUpdateAlbumSubmitButton() {
		buttonUpdateAlbumSubmit.click();
	}
	
	public void clickDeleteAlbumButton() {
		buttonDeleteAlbum.click();
	}
	
	public String getDeleteAlbumText() {
		return textAlbumNameDisplay.getText();
	}
}
