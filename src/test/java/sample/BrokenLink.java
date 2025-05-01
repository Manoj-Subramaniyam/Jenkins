package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class BrokenLink {
	@Test
	public void brokenLink() throws InterruptedException, IOException
	{
		WebDriver driver = new EdgeDriver();
		driver.get("https://leafground.com/link.xhtml");
		driver.manage().window().maximize();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for (WebElement webElement : elements) {
			String attribute = webElement.getAttribute("href");
			if(attribute != null && !attribute.isEmpty())
			{
				URL url =new URL (attribute);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.connect();
				
				if(connection.getResponseCode()>=400)
				{
					System.out.println("InValid Url :"+attribute);
				}
				else
				{
					System.out.println("Valid Url :"+attribute);
				}
			}
			
		}
		driver.quit();
	}
}
