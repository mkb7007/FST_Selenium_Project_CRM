package project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
//import java.util.List;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity9{
	
	//Instantiating driver
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@BeforeClass(alwaysRun = true)
	  public void setUp() throws InterruptedException {
		
		//Invoking fire fox driver
		
		//Visiting the web page
		driver.get("http://crm.local:3050/#/Login");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 //Finding the user-name field by waiting until user-name field appears
		 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder = 'Username']")));
		  
		//Sending value to user-name
		  element.sendKeys("admin");
		  
		//Finding the password field and passing value
		  driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		  element.click();
		  //clicking login
		  driver.findElement(By.id("login-button")).click();
		  
		  Thread.sleep(5000);


	  }
	
  @Test(priority = 1)
  public void verifyPage() {
	  
	  //Verifying page title
	  assertEquals(driver.getTitle(), "SuiteCRM");
  }
  
  
  @Test(priority = 2)
  public void testSalesLead() throws InterruptedException {
	  
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//a[span[text()='Leads']]")).click();
	  driver.findElement(By.xpath("//a[span[text()='View Leads']]")).click();
	  
	  
	  Thread.sleep(5000);

	  List<WebElement> names = driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
	 
	  List<WebElement> users = driver.findElements(By.xpath("//table/tbody/tr/td[8]"));
	  
	  System.out.println("++=====++==========++========++=========++========");

	  
	  for (int i=0;i<names.size();i++) {
		  System.out.println("Username :" +names.get(i).getText()+" UserType : "+ users.get(i).getText() );
	  }
	  
	  System.out.println("++=====++==========++========++=========++========");

  }
  
  
  @AfterClass(alwaysRun = true)
  public void tearDown() {
	  //Closing the browser
	  driver.quit();
	  }

}
  