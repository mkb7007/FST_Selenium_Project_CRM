package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertEquals; //static import
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class FirstTest {
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
  

  @AfterClass
  public void tearDown() {
	  
	  //Close the driver
	  driver.quit();
	  
  }

}
