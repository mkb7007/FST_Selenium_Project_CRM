package project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity5 {
	
	//Instantiating driver
		WebDriver driver;
		
		@BeforeClass
		  public void setUp() {
			
			//Invoking fire fox driver
			driver = new FirefoxDriver();
			
			//Visiting the web page
			driver.get("http://crm.local:3050/#/home");
			
			//implicit wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		  }
		
	  @Test
	  public void findNavigation() {
		  WebElement navbar = driver.findElement(By.cssSelector(".navbar"));
		  String navbarColor = navbar.getCssValue("background-color");
		  System.out.println("color of navbar :"+navbarColor);
		  assertEquals(navbarColor,"rgb(83, 77, 100)");
	  }
	  

	  @AfterClass
	  public void tearDown() {
		  //Closing the browser
		  driver.quit();
		  }


}
