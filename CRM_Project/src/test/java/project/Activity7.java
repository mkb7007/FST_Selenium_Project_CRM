package project;

import static org.testng.Assert.assertEquals;

/*Based on the screenshots and the HTML snippet, your task is:

1. Login to SuiteCRM.
2. Navigate to **Leads**.
3. Click the **Additional Information (i)** icon for the first lead.
4. Read the phone number from the popup.
5. Print the phone number in the console.
6. Close the browser.

Using the same TestNG blueprint you provided, here's a complete example:

```java
package project;*/

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity7 {

	// Declare WebDriver
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {

		// Initialize driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open CRM Login Page
		driver.get("http://crm.local:3050/#/Login");

		// Login
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("5Nx#I6BK%r3$8vz0ch");

		driver.findElement(By.id("login-button")).click();
	}

//    @Test
//    public void openLeads() {
//    	Actions actions = new Actions(driver);
//    	WebElement menu = driver.findElement(By.xpath("//span[text()='Leads']/parent::a"));
//
//    	actions.moveToElement(menu).click().perform();
//    	
//    	WebElement view_leads=driver.findElement(By.xpath("//a[.//span[text()='View Leads']]"));
//    	actions.moveToElement(view_leads).click().perform();
//    	
//    	
//    }
//    
	@Test
	public void openLeads() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);

		// Step 1: Hover on "Leads"
		WebElement menu = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Leads']/ancestor::a")));
		actions.moveToElement(menu).perform();

		// Step 2: Wait for dropdown "View Leads" to appear
		WebElement viewLeads = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//span[text()='View Leads']]")));

		// Step 3: Click it
		wait.until(ExpectedConditions.elementToBeClickable(viewLeads));
		viewLeads.click();
	}

	@Test(priority = 3)
	public void readAdditionalInformation() throws InterruptedException {

		// Get the Office phone number of the first user
		WebElement phoneNum = driver
				.findElement(By.xpath("//a[@href='tel:4089827873']"));

		// Verify the phone number
		assertEquals("4089827873", phoneNum.getText().trim());

		// Get the company name
		    WebElement companyName = driver.findElement(
		        By.xpath("//scrm-varchar-detail[normalize-space()='Max Holdings Ltd']")
		    );

		    // Verify the company name
		    assertEquals("Max Holdings Ltd", companyName.getText().trim());
		}

	
	

	@AfterClass
	public void tearDown() {

		// Close Browser
       driver.quit();
	}
}
