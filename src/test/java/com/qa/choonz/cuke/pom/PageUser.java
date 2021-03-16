package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageUser extends PageBase {

	@FindBy(xpath = "//*[@id=\"usernameUp\"]")
	private WebElement inputUsername;
	
	@FindBy(xpath = "//*[@id=\"passwordUp\"]")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//*[@id=\"signUpForm\"]/div/div[2]/button")
	private WebElement buttonSubmit;
	
	@FindBy(xpath = "//*[@id=\"signUpForm\"]")
	private WebElement resultText;
	
	public void inputUsername(String username) {
		inputUsername.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		inputUsername.sendKeys(password);
	}
	
	public void clickSubmit() {
		buttonSubmit.click();
	}
	
	public String getResultText() {
		return resultText.getText().substring(0,13);
	}
	
}
