package project;
 
import java.io.File;
import java.time.Duration;
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
 
public class Activity_8{
 
    WebDriver driver;
    WebDriverWait wait;
 
    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("http://crm.local:3050/#/Login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("5Nx#I6BK%r3$8vz0ch");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.urlContains("home"));
        driver.findElement(By.xpath("//a[span[text()='Accounts']]")).click();
    }
 
    // switch to default content then into the import iframe
    private void switchToImportIframe() {
        driver.switchTo().defaultContent();
        WebElement iframe = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("iframe"))
        );
        driver.switchTo().frame(iframe);
        System.out.println("  [iframe] switched in.");
    }
 
    // wait for a specific form inside iframe, then click Next
    private void waitForFormAndClickNext(String formId) {
        System.out.println("  Waiting for form: " + formId);
        switchToImportIframe();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(formId)));
        System.out.println("  Form [" + formId + "] loaded.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gonext")));
        System.out.println("  Next button visible.");
        WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("gonext"))
        );
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        nextBtn.click();
        System.out.println("  Clicked Next > on form [" + formId + "].");
        driver.switchTo().defaultContent();
    }
 
    @Test(priority = 1)
    public void uploadCsvAndGoToNext() throws InterruptedException {
        driver.findElement(By.xpath("//a[span[text()='Accounts']]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Import Accounts']"))).click();
        wait.until(ExpectedConditions.urlContains("import"));
        System.out.println("Import page loaded: " + driver.getCurrentUrl());
 
        System.out.println("=== STEP 1: Upload CSV ===");
        switchToImportIframe();
 
        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("userfile"))
        );
        File csvFile = new File("src/test/resources/userfile.csv"
        );
        fileInput.sendKeys(csvFile.getAbsolutePath());
        System.out.println("  CSV path sent: " + csvFile.getAbsolutePath());
 
        wait.until(driver -> {
            String val = driver.findElement(By.id("userfile")).getAttribute("value");
            System.out.println("  [check] userfile value = " + val);
            return val != null && !val.isEmpty();
        });
        System.out.println("  File input confirmed non-empty.");
 
        waitForFormAndClickNext("importstep2");
    }
 
    @Test(priority = 2)
    public void confirmImportProperties() {
        System.out.println("=== STEP 2: Confirm Import File Properties ===");
        waitForFormAndClickNext("importconfirm");
    }
    @Test(priority = 3)
    public void fieldMappingClickNext() throws InterruptedException {
        System.out.println("=== STEP 3: Field Mapping ===");
 
        switchToImportIframe();
 
        // Wait for field mapping table to confirm Step 3 is fully loaded
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("importTable")));
        System.out.println("  Step 3 field mapping table loaded.");
 
        // ✅ Print actual form ID so we know what it is
        try {
            WebElement form = driver.findElement(By.xpath("//form"));
            System.out.println("  Step 3 form id = " + form.getAttribute("id"));
        } catch (Exception e) {
            System.out.println("  Could not find form: " + e.getMessage());
        }
 
        // ✅ Click Next directly by id — no form ID needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gonext")));
        WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("gonext"))
        );
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", nextBtn);
        nextBtn.click();
        System.out.println("  Clicked Next > on Step 3.");
 
        driver.switchTo().defaultContent();
        System.out.println("  Done. URL: " + driver.getCurrentUrl());
    
    }
    @Test(priority = 4)
    public void Page3ClickNext() throws InterruptedException {
        System.out.println("=== STEP 3: Field Mapping ===");
 
        switchToImportIframe();
 
        // Wait for form importstep3 to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("importstep3")));
        System.out.println("  Step 3 form loaded.");
 
        // Wait for importTable to confirm page is fully rendered
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("importTable")));
        System.out.println("  Field mapping table loaded.");
 
        // Wait for gonext to be present
        WebElement nextBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("gonext"))
        );
        System.out.println("  Next button found.");
 
        // ✅ Use JavaScript click — button has height:0 so normal click won't work
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", nextBtn);
        System.out.println("  Clicked Next > on Step 3 via JS.");
 
        driver.switchTo().defaultContent();
        System.out.println("  Done. URL: " + driver.getCurrentUrl());
    }
    // ═════════════════════════════════════════════════════════════════════════
    // VIEW ACCOUNTS: Print odd row details from popup
    // ═════════════════════════════════════════════════════════════════════════
 
    @Test(priority = 5)
    public void printOddRowDetails() throws InterruptedException {
 
        // Navigate to View Accounts after import completes
        driver.findElement(By.xpath("//a[span[text()='Accounts']]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='View Accounts']"))).click();
        wait.until(ExpectedConditions.urlContains("DetailView"));
        System.out.println("=== VIEW ACCOUNTS: Print Odd Row Details ===");
 
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
 
        for (int i = 0; i < rows.size(); i++) {
 
            // Odd rows → index 0, 2, 4 ...
            if (i % 2 == 0) {
 
                WebElement row = rows.get(i);
 
                // Click 3-dot button
                WebElement threeDots = row.findElement(
                        By.xpath(".//scrm-record-details-popup-button//button"));
                threeDots.click();
 
                // Wait for popover body
                WebElement popupBody = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//ngb-popover-window//div[contains(@class,'popover-body')]")
                        )
                );
 
                // Get all text rows inside popup
                List<WebElement> details = popupBody.findElements(
                        By.xpath(".//div[contains(@class,'row')]")
                );
 
                System.out.println("------ Row " + (i + 1) + " ------");
                for (WebElement detail : details) {
                    String text = detail.getText().trim();
                    if (!text.isEmpty()) {
                        System.out.println(text);
                    }
                }
 
                // Close popup
                driver.findElement(By.tagName("body")).click();
                Thread.sleep(1000);
            }
        }
    }
 
    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
 