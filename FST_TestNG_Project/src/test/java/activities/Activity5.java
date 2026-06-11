package activities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Activity5 {
	// Declare WebDriver
	WebDriver driver;
	

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		// Initialize the driver object
		driver = new FirefoxDriver();

		// Open the page
		driver.get("https://training-support.net/webelements/target-practice");
	}
	@Test(alwaysRun = true)
	public void verifyPageTitle() {
		
		//verify title of the page
		assertEquals(driver.getTitle(), "Selenium: Target Practice");
	}

	@Test(groups = {"headingTests"})
	public void headerTest1() {
		String thirdHeader = driver.findElement(By.xpath("//h3")).getText();
		// Find the 3rd header on the page and assert its text
		assertEquals(thirdHeader, "Heading #3");
	}

	@Test(groups = {"headingTests"})
	public void headerTest2() {
		// Find the 5th header on the page and assert its color
		WebElement heading5 = driver.findElement(By.xpath("//h5[contains(@class, 'text-purple-600')]"));
		String heading5Color = heading5.getCssValue("color");
		assertEquals(heading5Color, "rgb(147, 51, 234)");
		assertEquals(Color.fromString(heading5Color).asHex(), "#9333ea");
	}

	@Test(groups = {"buttonTests"})
	public void buttonTest1() {
		// Find the purple button and assert all it's classes.
		String purpleButton = driver.findElement(By.cssSelector("button.text-purple-900")).getAttribute("class");
		assertEquals(purpleButton, "rounded-xl bg-purple-200 p-2 text-3xl font-bold text-purple-900 svelte-2hb4ib");
		assertTrue(purpleButton.contains("text-purple-900"));
	}

	@Test(groups = {"buttonTests"})
	public void buttonTest2() {
		// Find the slate button and assert it's text.
		String slateButton = driver.findElement(By.cssSelector("button.text-slate-900")).getText();
		assertEquals(slateButton, "Slate");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

}



