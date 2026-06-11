package project;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity_6 {
	WebDriver driver;
	@BeforeClass
	  public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://crm.local:3050/#/Login");
	  }
	@Test
	public void verifyAccountsMenu() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        login(wait);
	        clickAccountsMenu(wait);
	        System.out.println("Login successful and Accounts menu clicked");
	    } catch (Exception e) {
	        System.out.println("Accounts menu not found");
	    }
	}

	//Helper Methods
	private void login(WebDriverWait wait) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("admin");
	    driver.findElement(By.name("password")).sendKeys("5Nx#I6BK%r3$8vz0ch");
	    driver.findElement(By.id("login-button")).click();
	}
	private void clickAccountsMenu(WebDriverWait wait) {
	    By accountsMenu = By.xpath("//a/span[contains(text(),'Accounts')]");
	    WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(accountsMenu));
	    menu.click();
	}

  @AfterClass
  public void tearDown() {
	  //Closing the browser
	  driver.quit();
	  }
}