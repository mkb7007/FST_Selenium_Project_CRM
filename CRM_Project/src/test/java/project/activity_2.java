package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity_2 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();

        driver.get("http://crm.local:3050/#/Login");
    }

    @Test
    public void getHeaderImageURL() {

        // Wait for the logo image to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement logo = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[contains(@class,'image-company_logo')]")
            )
        );

        // Get the image URL
        String imageURL = logo.getAttribute("src");

        // Print the URL
        System.out.println("Header Image URL: " + imageURL);
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}