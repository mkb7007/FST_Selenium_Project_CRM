package activities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {
	//Declare WebDriver
	WebDriver driver;
	
	@BeforeClass
	  public void setUp() {
		
		//Initialize driver object
		driver = new FirefoxDriver();
		
		//Open the page
		driver.get("https://training-support.net/webelements/target-practice/");
		
	  }
	
  @Test(priority = 1)
  public void verifyPageTitle() {
	  
	  //Assert the page title
	  //assertEquals(expected, actual)
	  assertEquals(driver.getTitle(), "Selenium: Target Practice");  
  }
  

  @Test(priority = 2)
  public void findBlack()  {
	  
	  //Locate and click the about us link
	  driver.findElement(By.id("black button"));
	  
	  //assert about page title
	  assertEquals(driver.getTitle(), "black button found");
  }
  
  @Test(enabled = false)
  public void findTeal()  {
	  
	  //Locate and click the about us link
	  driver.findElement(By.cssSelector("button.svelte-2hb4ib"));
	  
	  //assert about page title
	  assertEquals(driver.getTitle(), "Teal color");
  }
  
  @Test(priority = 3)
  public void verifyPage() {
	  boolean status = true;
	  if(status) {
		  throw new SkipException("The current exec is skipped !");
	  }
	  //Assert the page title
	  //assertEquals(expected, actual)
	  assertEquals(driver.getTitle(), "Training Support");  
  }
  
  
  
  @AfterClass
  public void tearDown() {
	  
	  //Close the driver
	  driver.quit();
	  
  }
}