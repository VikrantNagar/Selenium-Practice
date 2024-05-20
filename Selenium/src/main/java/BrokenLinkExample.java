import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrokenLinkExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Setting system property for firefox driver
		System.setProperty("webdriver.gecko.driver", "/Users/vikrantnagar/Documents/Drivers/geckodriver");
		
		//Invoking Driver
		WebDriver driver = new FirefoxDriver();
		
		//Maximize the window
		driver.manage().window().maximize();
		
		//Open URL
		driver.get("https://www.mobikwik.com/");
		
		//Creating list to store all elements that matches the given xpath
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link : links) {
			String url = link.getAttribute("href");
			System.out.println(url);
			VerifyLink(url);
		}
		
		//Closing the driver
		driver.quit();
	}
	
	//Method to verify the link
	public static void VerifyLink(String url) {
		try {
			URL checkLink = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) checkLink.openConnection();
			httpUrlConnection.setConnectTimeout(2000);
			httpUrlConnection.connect();
			
			if(httpUrlConnection.getResponseCode() == 200) {
				System.out.println(url + " " + httpUrlConnection.getResponseMessage());
			}else {
				System.out.println(url + " " + httpUrlConnection.getResponseMessage() + "It is a Broken Link");
			}
			
		}
		catch(Exception e){
			System.out.print(url + "It is a Broken Link");
		}
		
	}

}
