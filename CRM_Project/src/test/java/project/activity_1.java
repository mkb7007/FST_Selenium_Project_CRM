package project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity_1 {
	// Declare the WebDriver
	WebDriver driver;
	WebDriverWait wait;
	
@BeforeClass
	 public void beforeClass() {
		  // Initialize the driver objects
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		  
		  // Open the page
		  driver.get("http://crm.local:3050/#/Login");
	  }
	
  @Test()
  public void verifyPageTitle() {
	  // Assert the page title
	  assertEquals(driver.getTitle(), "SuiteCRM");  
  }
  
  @AfterClass
  // public void afterClass() 
  public void tearDown(){
	  // Close the browser
	  driver.quit();
  }
}
