package extentlisteners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import driverInstance.DriverFactory;
import utilities.Utils;

public class TestListener implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("**Test Suite > " + context.getName() + " started **");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("** Test Suite > " + context.getName() + " ending **"));

		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("** Running test method > " + result.getMethod().getMethodName()));
		String testPlanName = result.getTestContext().getName().toLowerCase();
		String methodName = result.getMethod().getMethodName();
		ExtentTestManager.startTest(methodName, "General");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("** Executed > " + result.getMethod().getMethodName() + " test successfully");
		ExtentTestManager.getTest().pass("Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("** Test execution >" + result.getMethod().getMethodName() + " failed");
		ExtentTestManager.getTest().fail(MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));

		// attach screenshot
		ExtentTestManager.getTest().fail(result.getThrowable());
		try {
			String screenShotName = result.getName() + Utils.milliseconds;
			String screenShotPath = Utils.captureAndReturnScreenshotPath(DriverFactory.getInstance().getDriver(),screenShotName);
			
			// attaching the screenshot in the below line
			String base64ScreenshotPath = Utils.pathToBase64(screenShotPath);
			ExtentTestManager.getTest().fail("Failed screenshot",MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenshotPath).build());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("** Test >" + result.getMethod().getMethodName() + " skipped");
		try {
			ExtentTestManager.getTest().skip("Test Skipped > " + result.getName());
			System.out.println("Skipped Throwable here: " + result.getThrowable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
}