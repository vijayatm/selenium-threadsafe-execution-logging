package utilities;

import org.apache.log4j.Logger;
import extentlisteners.ExtentTestManager;
import org.apache.log4j.*;

public class Log{
	
	private static Logger Log = LogManager.getLogger(Log.class.getName());//

	public static void startTestCase(String sTestCaseName) {
		Log.info("****************************************************************************************");
		Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		Log.info("****************************************************************************************");
	}

	public static void endTestCase() {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		Log.info(" ");
	}

	public static void info(String message) {
		ExtentTestManager.getTest().info(message);
		Log.info(message);
	}

}