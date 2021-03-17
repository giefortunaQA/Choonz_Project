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
	
	@FindBy(xpath = "//*[@id=\"artists1\"]/button/h3")
	private WebElement textArtistName;
	
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
	
}
