package amazonPackage;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import driverInstance.DriverFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class AmazonTest {

	@Test
	public void Test001_Amazon_Pass() {
		DriverFactory.getInstance().getDriver().get("https://www.amazon.in");	
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("washing machine");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']")).submit();
		
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);

	assertThat(currenturl, containsString("amazon"));
	}
	
	@Test
	public void Test002_Amazon_Fail() {
		String currenturl = DriverFactory.getInstance().getDriver().getCurrentUrl();
		System.out.println("This is the current url: "+currenturl);
		assertThat(currenturl, containsString("google"));
	}
}
