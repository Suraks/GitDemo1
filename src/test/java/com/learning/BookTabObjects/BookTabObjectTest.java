package com.learning.BookTabObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BookTabObjectTest {
	
	WebDriver driver = null;
	
	public BookTabObjectTest(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement departureField;
	
	@FindBy(id = "ctl00_mainContent_ddl_destinationStation1_CTXT")
	public WebElement arrivalField;
	
	
	public WebElement getDepatureCity() {
		return driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
	} 
	
	public WebElement getArrivalCity(){
		return arrivalField;
	}

	
}
