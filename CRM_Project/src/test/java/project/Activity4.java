package project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {
	
	//Instantiating driver
	WebDriver driver;
	
	@BeforeClass(alwaysRun = true)
	  public void setUp() {
		
		//Invoking fire fox driver
		driver = new FirefoxDriver();
		
		//Visiting the web page
		driver.get("http://crm.local:3050/#/Login");
		
	  }
	
  @Test(priority = 1)
  public void verifyPage() {
	  //Verifying page title
	  assertEquals(driver.getTitle(), "SuiteCRM");
  }
  
  @Test(priority = 2)
  public void loginTest() {
	 
	  //Adding explicit wait
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 //Finding the user-name field by waiting until user-name field appears
	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder = 'Username']")));
	  
	//Sending value to user-name
	  element.sendKeys("admin");
	  
	//Finding the password field and passing value
	  driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("5Nx#I6BK%r3$8vz0ch");
	  
	  //clicking login
	  driver.findElement(By.id("login-button")).click();
	  
	  //Verifying the status for successful login
	  wait.until(ExpectedConditions.urlContains("home"));
	  String currentUrl = driver.getCurrentUrl();
	  String expectedUrl = "http://crm.local:3050/#/home";
	  
	  assertEquals(currentUrl, expectedUrl);
	  
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
	  //Closing the browser
//	  driver.quit();
	  }

}
