package googlePackage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import driverInstance.DriverFactory;

public class GoogleTest {
	
	
	@Test
	public void Test001_Google_Pass() {
		DriverFactory.getInstance().getDriver().get("https://www.google.com");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@title='Search']")).sendKeys("washing machine");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@title='Search']")).submit();
		
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);

		assertThat(currenturl, containsString("google"));
	}
	
	@Test
	public void Test002_Google_Fail() {
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);
		
		assertThat(currenturl, containsString("flipkart"));
	}
	
	
}
