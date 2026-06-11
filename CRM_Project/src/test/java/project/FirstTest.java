package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {
	
	//Instantiating driver
	WebDriver driver;
	
	@BeforeClass
	  public void setUp() {
		
		//Invoking fire fox driver
		driver = new FirefoxDriver();
		
		//Visiting the web page
		driver.get("http://crm.local:3050/#/Login");
		
	  }
	
  @Test
  public void f() {
  }
  

  @AfterClass
  public void tearDown() {
	  //Closing the browser
	  driver.quit();
	  }

}
