package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageArtists extends PageBase {
	
	@FindBy(xpath = "/html/body/div[1]/button")
	private WebElement buttonCreateArtist;
	
	@FindBy(xpath = "//*[@id=\"artistName\"]")
	private WebElement inputCreateArtistName;
	
	@FindBy(xpath = "//*[@id=\"createArtistForm\"]/span[1]/button")
	private WebElement buttonCreateArtistSubmit;
	
	// this will need to be changed from artist1 to artist5
	// when the data.sql is implemented for testing
	@FindBy(xpath = "//*[@id=\"artists1\"]/button/h3")
	private WebElement textArtistName;
	
	@FindBy(xpath = "//*[@id=\"updateEachArtist\"]")
	private WebElement buttonUpdateArtist;
	
	@FindBy(xpath = "//*[@id=\"artistNameUpdate\"]")
	private WebElement inputUpdateArtist;
	
	@FindBy(xpath = "//*[@id=\"updateArtistBtn\"]")
	private WebElement buttonUpdateArtistSubmit;
	
	@FindBy(xpath = "//*[@id=\"artistNameDisplay\"]/h1")
	private WebElement testArtistNameUpdated;
	
	public void clickCreateArtist() {
		buttonCreateArtist.click();
	}
	
	public void inputCreateArtistName(String name) {
		inputCreateArtistName.sendKeys(name);
	}
	
	public void clickCreateArtistSubmit() {
		buttonCreateArtistSubmit.click();
	}
	
	public String getArtistName() {
		return textArtistName.getText();
	}
	
	public void clickArtistName() {
		textArtistName.click();
	}
	
	public void clickUpdateArtist() {
		buttonUpdateArtist.click();
	}
	
	public void inputUpdateArtistName(String name) {
		inputUpdateArtist.sendKeys(name);
	}

	public void clickUpdateArtistSubmit() {
		buttonUpdateArtistSubmit.click();
	}
	
	public String getArtistNameUpdated() {
		return testArtistNameUpdated.getText().substring(9);
	}
	
}
