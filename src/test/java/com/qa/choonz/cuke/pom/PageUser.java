package com.qa.choonz.cuke.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageUser extends PageBase {

	@FindBy(xpath = "//*[@id=\"usernameUp\"]")
	private WebElement inputSignUpUsername;
	
	@FindBy(xpath = "//*[@id=\"passwordUp\"]")
	private WebElement inputSignUpPassword;
	
	@FindBy(xpath = "//*[@id=\"signUpForm\"]/div/div[2]/button")
	private WebElement buttonSignUpSubmit;
	
	@FindBy(xpath = "//*[@id=\"usernameIn\"]")
	private WebElement inputLoginUsername;
	
	@FindBy(xpath = "//*[@id=\"passwordIn\"]")
	private WebElement inputLoginPassword;
	
	@FindBy(xpath = "//*[@id=\"signInForm\"]/div/div[2]/button")
	private WebElement buttonLoginSubmit;
	
	@FindBy(xpath = "//*[@id=\"signUpForm\"]")
	private WebElement resultText;
	
	public void inputSignUpUsername(String username) {
		inputSignUpUsername.sendKeys(username);
	}
	
	public void inputSignUpPassword(String password) {
		inputSignUpUsername.sendKeys(password);
	}
	
	public void clickSignUpSubmit() {
		buttonSignUpSubmit.click();
	}
	
	public void inputLoginUsername(String username) {
		inputLoginUsername.sendKeys(username);
	}
	
	public void inputLoginPassword(String password) {
		inputLoginUsername.sendKeys(password);
	}
	
	public void clickLoginSubmit() {
		buttonLoginSubmit.click();
	}
	
	public String getResultText() {
		return resultText.getText().substring(0,13);
	}
	
}
