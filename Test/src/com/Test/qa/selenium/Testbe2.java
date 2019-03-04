package com.Test.qa.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Testbe2 {
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
	
	
	@Test
	public void profileVerification() throws InterruptedException {
		
		//Launch URL		
		driver.get("https://www.be2.com");
		driver.manage().window().maximize();	
		
		//Login to Website		
		driver.findElement(By.xpath("//span[text()='Login' and @class='cta']")).click();
		driver.findElement(By.id("be2_login_username")).sendKeys("testcandidate10@testsystem.fc4cd.com");
		driver.findElement(By.id("be2_login_password")).sendKeys("shGBGe3gAG");
		driver.findElement(By.id("be2_loginButton")).click();		
    	Assert.assertEquals(driver.getTitle(), "Matchmaking service from be2 - start now!"); 
    	
    	//Verifying the Welcome mail    	
		driver.findElement(By.xpath("//span[text()='Mailbox']")).click();
		driver.findElement(By.xpath("//div[@class='last-message']")).click();
		driver.findElement(By.xpath("//div[@class='message-row' and @type='WELLCOME_MESSAGE_ON_BEHALF']")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='message-row']")).getAttribute("type").toString(),"WELLCOME_MESSAGE_ON_BEHALF");	
		
		//Another way to verify the Welcome mail 
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contact' and @data-ft-user-contact-id ]")).isDisplayed());
//		boolean welcomemail = driver.findElement(By.xpath("//div[@class='last-message']")).getText().contains("Dear new be2 Member We are very happy to welcome you.");
//		Assert.assertTrue(welcomemail);
		
        //Verifying the age of own profile			
		driver.findElement(By.xpath("//span[@class='my-profile-text' and text()='My Profile']")).click();
		String agetext =driver.findElement(By.xpath("//table[@class='text-copy']//tbody//tr//following-sibling::tr//td[text()='Age']//following-sibling::td")).getText();
		int age = Integer.parseInt(agetext);
		Assert.assertEquals(age, 35);
		
		//Logout the website
		driver.findElement(By.xpath("//li[@class ='nav-logout']//a[@title='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Login' and @class='cta']")).isDisplayed());
	}
	
	@AfterMethod
	public void closeBrowser() {	
		driver.quit();
	}
	

}
