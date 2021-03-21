package masterPackage;

import java.io.IOException;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import driverInstance.BrowserFactory;
import driverInstance.DriverFactory;
import extentlisteners.ExtentManager;
import utilities.Log;

public class DriverSetupAndKill {
	public WebDriver driver = null;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		if(OS.isFamilyMac()) {
			System.out.println("Terminating existing chromedriver in mac os");
			Runtime.getRuntime().exec("killall chromedriver");
		}else {
			System.out.println("Terminating existing chromedriver in mac os");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		}
		System.out.println("Cleared all chromedriver instances");
	}
	
	@BeforeTest
	public void driverSetup() {
		driver = BrowserFactory.createInstance("chrome", "true");
		DriverFactory.getInstance().setDriver(driver);
		driver = DriverFactory.getInstance().getDriver();
		driver.manage().window().maximize();

	}
	
	@BeforeMethod
	public void beforeTestCaseBegins() {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.startTestCase(methodName);

	}
	
	@AfterMethod
	public void afterTestCaseEnds() {
		Log.endTestCase();
	}
	
	
	@AfterTest
	public void driverRemove() {
		DriverFactory.getInstance().removeDriver();
	}
	
	
	@AfterSuite
	public void endTest() {
		System.out.println("ReportLocation >> " + ExtentManager.fileName);
	}
	
	
	
}
