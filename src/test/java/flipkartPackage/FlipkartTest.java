package flipkartPackage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import driverInstance.DriverFactory;

public class FlipkartTest {
	
	@Test
	public void Test001_Flipkart_Pass() {
		DriverFactory.getInstance().getDriver().get("https://www.flipkart.com");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("washing machine");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).submit();
		
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);

		assertThat(currenturl, containsString("flipkart"));
	}
	
	@Test
	public void Test002_Flipkart_Fail() {
		
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);
		
		assertThat(currenturl, containsString("amazon"));
	
	}
	
	
}
