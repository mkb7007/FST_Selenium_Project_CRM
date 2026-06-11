package activities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeworkActivity {
	WebDriver driver;
	
	@BeforeClass
	  public void setUp() {
		//Driver instance for fire-fox driver
		driver = new FirefoxDriver();
		//visiting the page
		driver.get("https://training-support.net/webelements/alerts");
	  }
	
	@Test(priority = 1)
	  public void verifyPageTitle() {
		  
		  //Assert the page title
		  assertEquals(driver.getTitle(), "Selenium: Alerts");
	  }
	
  @Test(priority = 2)
  public void simpleAlertTestCase() {
	  
	  //Finding the simple alert button and clicking it for assertion
	  WebElement simpleAlert = driver.findElement(By.id("simple"));
	  simpleAlert.click();
	  Alert simple = driver.switchTo().alert();
	  assertEquals(simple.getText(), "You've just triggered a simple alert!");
	  simple.accept();
	  assertEquals(driver.findElement(By.id("result")).getText(), "You just accepted a simple alert!");
	  
  }
  
	
  @Test(priority = 3)
  public void confirmAlertTestCase() {
	  
	//Finding the confirm alert button and clicking it for assertion
	  WebElement confirmAlert = driver.findElement(By.id("confirmation"));
	  confirmAlert.click();
	  Alert confirm = driver.switchTo().alert();
	  assertEquals(confirm.getText(), "You've just triggered a confirmation alert!");
	  confirm.accept();
	  assertEquals(driver.findElement(By.id("result")).getText(), "You just accepted a confirmation alert!");
  }
  
	
  @Test(priority = 4)
  public void promptAlertTestCase() {
	  
	//Finding the prompt alert button and clicking it for assertion
	  WebElement promptAlert = driver.findElement(By.id("prompt"));
	  promptAlert.click();
	  Alert prompt = driver.switchTo().alert();
	  assertEquals(prompt.getText(), "I'm a Prompt! Type something into me!");
	  prompt.sendKeys("Hello");
	  prompt.accept();
	  assertEquals(driver.findElement(By.id("result")).getText(), "You typed \"Hello\" into the prompt!");
  }
  

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
