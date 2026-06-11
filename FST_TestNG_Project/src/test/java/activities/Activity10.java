package activities;

import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Activity10 {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	  public void setUp() {
		//Driver instance for fire-fox driver
		driver = new FirefoxDriver();
		
		//Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//visiting the page
		driver.get("https://training-support.net/webelements/simple-form");
	}
	
	
  @DataProvider(name="csvDataProvider")
  public Object[][] inputData() throws IOException, CsvException {
	  //Create csv reader
	  CSVReader reader = new CSVReader(new FileReader("src/test/resources/input.csv"));
	  
	  //Make the reader skip the first line of the csv file
	  reader.skip(1);
	 
	  //Read the data from csv file
	  List<String[]> inputData = reader.readAll();
	  reader.close();
	  
	  //Create the input data set
	  //This is the data set that is passed to the test functions
	  Object[][] data = new Object[inputData.size()][];
	  for (int i = 0; i < inputData.size(); i++) {
		  data[i] = inputData.get(i);
	  }
	  
//we use above method instead of the below to be more reusable and cleaner code
//	  return new Object[][] {
//		  inputData.get(1);
//		  inputData.get(2);
//		  inputData.get(3);
	  
	  //Return the dataset
	  return data;
  }
	  
  
  
  @Test(priority = 1)
  public void verifyPageTitle() {
	  
	  //Assert the page title
	  assertEquals(driver.getTitle(), "Selenium: Simple Form");
  }
  
  
  @Test(priority = 2, dataProvider = "csvDataProvider")
  public void formTest(String[] rows) {
	  
	  //Find the full name field and add the full name from the rows
	  driver.findElement(By.id("full-name")).sendKeys(rows[0]);
	  driver.findElement(By.id("email")).sendKeys(rows[1]);
	  driver.findElement(By.name("event-date")).sendKeys(rows[2]);
	  driver.findElement(By.id("additional-details")).sendKeys(rows[3]);
	  
	  //Click submit
	  driver.findElement(By.xpath("//button[text() = 'Submit']")).click();
	  
	  //Confirm booking
	  assertEquals(driver.findElement(By.id("action-confirmation")).getText(), "Your event has been scheduled!");
	  
	  //Refresh the page
	  driver.navigate().refresh();
  }
  
  
  @AfterClass
  public void tearDown() {
	  driver.quit();
  }
}
