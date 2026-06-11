package activities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {
	//Declare WebDriver
		WebDriver driver;
		
		@BeforeClass
		  public void setUp() {
			
			//Initialize driver object
			driver = new FirefoxDriver();
			
			//Open the page
			driver.get("https://training-support.net");
			
		  }
		
	  @Test(priority = 1)
	  public void verifyPageTitle() {
		  
		  //Assert the page title
		  //assertEquals(expected, actual)
		  assertEquals(driver.getTitle(), "Training Support");  
	  }
	  
	  //instead of priority also use @Test(dependsOnMethods = {"verifyPageTitle"})
	  @Test(priority = 2)
	  public void clickAboutUs()  {
		  
		  //Locate and click the about us link
		  driver.findElement(By.linkText("About Us")).click();
		  
		  //assert about page title
		  assertEquals(driver.getTitle(), "About Training Support");
	  }
	  

	  @AfterClass
	  public void tearDown() {
		  
		  //Close the driver
		  driver.quit();
		  
	  }
}
