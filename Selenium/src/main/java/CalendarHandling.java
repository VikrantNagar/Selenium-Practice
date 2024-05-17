import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalendarHandling {

	public static void main(String[] args) throws InterruptedException {

		// Setting system property
		System.setProperty("webdriver.gecko.driver", "/Users/vikrantnagar/Documents/Drivers/geckodriver");
		
		//Invoking driver
		WebDriver driver = new FirefoxDriver();
		
		//Open url
		driver.get("https://www.expedia.com/");
		
		//Maximize the window
		driver.manage().window().maximize();
		
		//Click on Destination text field
		driver.findElement(By.xpath("//div[@class='uitk-field has-floatedLabel-label has-icon'][1]")).click();

		//Enter name of destination
		driver.findElement(By.id("destination_form_field")).sendKeys("Kashmir");

		//Press Enter
		driver.findElement(By.id("destination_form_field")).sendKeys(Keys.ENTER);

		//Click on Calendar field
		driver.findElement(By.name("EGDSDateRange-date-selector-trigger")).click();
		
		String date = "April 2025";
		String fromDay = "21";
		String toDay = "30";
		
		//Login to traverse to the given month
		while(true){
		
			String currMonth = driver.findElement(By.xpath("//span[@class = 'uitk-align-center uitk-month-label']")).getText();
			
			if(currMonth.equals(date)) {
				break;
			}
			else {
				driver.findElement(By.xpath("//div[@class='uitk-cal-controls-button uitk-cal-controls-button-inset-multi uitk-cal-controls-button-next']")).click();
			}
		}	
		
		//Select from date
		driver.findElement(By.xpath("//div[contains(text(),  " + fromDay +")]")).click();
		
		//Select to-Date
		driver.findElement(By.xpath("//div[contains(text(),  " + toDay +")]")).click();
		
		//Click on Done
		driver.findElement(By.xpath("//button[@data-stid='apply-date-selector']")).click();
		
		//Click on Search button
		driver.findElement(By.xpath("//button[@id = 'search_button']")).click();
		
		//Wait for the page to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='uitk-card-link'][1]")));
		
		//Quitting the driver
		driver.quit();
	}

}
