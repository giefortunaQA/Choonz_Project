package com.qa.choonz.pom.cuke.stepdefs;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.pom.pages.PageBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class StepDefs {
	
	private static RemoteWebDriver driver;
	private static PageBase base = PageFactory.initElements(driver, PageBase.class);

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
}
