package project;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class activity_6 {

    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://crm.local:3050");
    }

    @Test(priority = 1)
    public void verifyPage() {
        assertEquals(driver.getTitle(), "SuiteCRM");
    }

    @Test(priority = 2)
    public void enterCredentialsAndLogin() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement username = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Username']"))
        );
        username.sendKeys("admin");

        driver.findElement(By.xpath("//input[@name='password']"))
              .sendKeys("5Nx#I6BK%r3$8vz0ch");

        driver.findElement(By.id("login-button")).click();
    }


    @Test(priority = 3, dependsOnMethods = "enterCredentialsAndLogin")
    public void verifyLoginSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.urlContains("home"));

        // Verify login success
        assertTrue(driver.getCurrentUrl().contains("home"), "Login failed");
    }


    @Test(priority = 4, dependsOnMethods = "verifyLoginSuccess")
    public void locateAccountsMenu() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement accountsMenu = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a/span[text()='Accounts']")
            )
        );

        // Verify Accounts menu exists
        assertTrue(accountsMenu.isDisplayed(), "Accounts menu is not visible");
    }
    
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
