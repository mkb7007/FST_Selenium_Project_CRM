package activities;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Activity7 {
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
	  
	 
	 @DataProvider(name = "Invalid Credentials")
	 public Object[][] creds() {
		 return new Object[][] {
			 {"admin", "WrongPassword"},
			 {"WrongAdmin", "password"},
			 {"WrongAdmin", "wrongPassword"},
			 {"0_0", ":p"},
			 {"<script>alert('Hacked!')</script>", "<script>alert('!PWND!')</script>"}
		 };
	 }
	 
	 @Test(priority = 2, dataProvider = "Invalid Credentials")
	  public void validLogin(String username, String password) throws InterruptedException {
		  
		 //finding user-name and password field and inputting
		 driver.findElement(By.id("username")).sendKeys(username);
		 driver.findElement(By.id("password")).sendKeys(password);
		 
		 //clicking submit
		 driver.findElement(By.cssSelector("button.svelte-1pdjkmx")).click();
		 
		 Thread.sleep(5000);
		 
		 
		 //Verify the success message
		 assertEquals(driver.findElement(By.id("subheading")).getText(), "Invalid credentials");
		 
	  }
	 
	 @AfterMethod
	 public void resetPage() {
		 driver.navigate().refresh();
	 }
	 

	  @AfterClass
	  public void tearDown() {
		  
		  //Close the driver
		  driver.quit();
		  
	}
}