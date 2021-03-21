package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.compress.utils.IOUtils;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class Utils {

	public static String milliseconds = String.valueOf(System.currentTimeMillis());
	public static String fileSeperator = System.getProperty("file.separator"); 
	
	public static String createFolder(String directory) {
		File dir = new File(directory);
		if (!dir.exists())
			dir.mkdirs();
		return directory;
	}
	
	public static String captureAndReturnScreenshotPath(WebDriver driver, String screenshotName) throws Exception {
		String file_location = null;
		try{
			file_location = Utils.createFolder(
					System.getProperty("user.dir")+Utils.fileSeperator+
					"test-output"+Utils.fileSeperator+
					"ScreenCaptures"+Utils.fileSeperator+
					Utils.milliseconds+Utils.fileSeperator+
					"FailedTestsScreenshots")+Utils.fileSeperator;

			Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).withName(screenshotName).save(file_location);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return file_location+screenshotName+".png";
	}

	public static String pathToBase64(String screenshotPath) throws IOException {
		InputStream in = new FileInputStream(screenshotPath);
		byte[] imageBytes = IOUtils.toByteArray(in);
		return Base64.getEncoder().encodeToString(imageBytes);
	}
}
