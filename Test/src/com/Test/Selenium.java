package com.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium {


	
	
	WebDriver driver;
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
	
	@Test
	public void profilecreation() {
		driver.get("https://www.be2.com");
		driver.manage().window().maximize();	
		driver.findElement(By.xpath("//span[text()='Login' and @class='cta']")).click();
		driver.findElement(By.id("be2_login_username")).sendKeys("testcandidate10@testsystem.fc4cd.com");
		driver.findElement(By.id("be2_login_password")).sendKeys("shGBGe3gAG");
		driver.findElement(By.id("be2_loginButton")).click();		
    	Assert.assertEquals(driver.getTitle(), "Matchmaking service from be2 - start now!");
    	
		driver.findElement(By.xpath("//span[text()='Mailbox']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contact' and @data-ft-user-contact-id ]")).isDisplayed());
		boolean welcomemail = driver.findElement(By.xpath("//div[@class='last-message']")).getText().contains("Dear new be2 Member We are very happy to welcome you.");
		Assert.assertTrue(welcomemail);
		
		driver.findElement(By.xpath("//span[@class='my-profile-text' and text()='My Profile']")).click();
		String agetext =driver.findElement(By.xpath("//table[@class='text-copy']//tbody//tr//following-sibling::tr//td[text()='Age']//following-sibling::td")).getText();
		int age = Integer.parseInt(agetext);
		Assert.assertEquals(age, 35);
		
		driver.findElement(By.xpath("//li[@class ='nav-logout']//a[@title='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Login' and @class='cta']")).isDisplayed());
	}
	
	@AfterMethod
	public void closeBrowser() {	
		driver.quit();
	}
	

}
