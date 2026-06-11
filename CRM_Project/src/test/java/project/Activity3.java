package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {
	
	//Instantiating driver
	WebDriver driver;
	
	@BeforeClass
	  public void setUp() throws InterruptedException {
		
		//Invoking fire fox driver
		driver = new FirefoxDriver();
		
		//Visiting the web page
		driver.get("http://crm.local:3050/#/Login");
		Thread.sleep(6000);
		
	  }
	
  @Test
  public void copyrightTextCheck() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  String copyrightText=driver.findElement(By.xpath("//a[text()=' © Supercharged by SuiteCRM ']")).getText();
		  System.out.println(copyrightText);
	  }
  

  @AfterClass
  public void tearDown() {
	  //Closing the browser
	  driver.quit();
	  }
}

