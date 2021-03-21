
package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utilities.Utils;

public class ExtentManager{
	
	protected static ExtentReports extent;
	private static String reportFileName = "WebAutomation_"+Utils.milliseconds+".html";
	private static String reportFilepath = System.getProperty("user.dir") + Utils.fileSeperator +"test-output";
	protected static String reportFileLocation = reportFilepath + Utils.fileSeperator + reportFileName;

	public static String fileName = null;
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {
		fileName = Utils.createFolder(reportFilepath) +Utils.fileSeperator +reportFileName;
		
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		System.out.println("ReportLocationFirst: "+reportFileLocation);
		return extent;
	}

}