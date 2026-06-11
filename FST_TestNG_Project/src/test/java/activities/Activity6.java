package activities;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Activity6 {
WebDriver driver;
	
	@BeforeClass
	  public void setUp() {
		
		//Initialize driver object
		driver = new FirefoxDriver();
		
		//Open the page
		driver.get("https://training-support.net/webelements/login-form/");
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	  }
	
	 @Test(priority = 1)
	  public void verifyPageTitle() {
		  
		  //Assert the page title
		  //assertEquals(expected, actual)
		  assertEquals(driver.getTitle(), "Selenium: Login Form");
	  }
	  
	 @Test(priority = 2)
	  @Parameters({ "username", "password", "message" })
	  public void validLogin(String username, String password, @Optional("Login Success!") String expectedMessage){
		  
		 //finding user-name and password field and inputting
		 driver.findElement(By.id("username")).sendKeys(username);
		 driver.findElement(By.id("password")).sendKeys(password);
		 
		 //clicking submit
		 driver.findElement(By.cssSelector("button.svelte-1pdjkmx")).click();
		 
		 
		 //asserting
		 assertEquals(driver.findElement(By.cssSelector("h1.text-emerald-500")).getText(), "Login Success!");
		 
	  }

	  @AfterClass
	  public void tearDown() {
		  
		  //Close the driver
		  driver.quit();
		  
	}
}