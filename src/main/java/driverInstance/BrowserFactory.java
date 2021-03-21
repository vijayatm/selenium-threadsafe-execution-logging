package driverInstance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	public static WebDriver createInstance(String browser, String headless) {
		WebDriver driver = null;
		try {
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				System.out.println("Using Chromedriver: " + WebDriverManager.chromedriver().getDriverVersions());
				ChromeOptions options = new ChromeOptions();

				if (headless.equalsIgnoreCase("true")) {
					System.out.println("Setting the 'window-size=1600,900'");
					options.addArguments("window-size=1600,900");
					System.out.println("isHeadless: True");
					options.setHeadless(true);
				}
				driver = new ChromeDriver(options);

			} else if (browser.equals("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				if (headless.equalsIgnoreCase("true")) {
					System.out.println("Setting the 'window-size=1600,900'");
					options.addArguments("window-size=1600,900");
					System.out.println("isHeadless: True");
					options.setHeadless(true);
				}
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(options);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return driver;
		}
		return driver;
	}

}
