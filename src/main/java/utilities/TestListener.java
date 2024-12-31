package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG listener to capture screenshots on test success or failure.
 */
public class TestListener implements ITestListener {

	/**
	 * Captures a screenshot when a test passes.
	 * 
	 * @param result The result of the executed test method.
	 */
	public void onTestSuccess(ITestResult result) {
		takeScreenshot(result, "PASS");
	}

	/**
	 * Captures a screenshot when a test fails.
	 * 
	 * @param result The result of the executed test method.
	 */
	public void onTestFailure(ITestResult result) {
		takeScreenshot(result, "FAIL");
	}

	/**
	 * Captures a screenshot for a given test result and status.
	 * 
	 * @param result The result of the executed test method.
	 * @param status The status of the test (e.g., PASS or FAIL).
	 */
	private void takeScreenshot(ITestResult result, String status) {
		WebDriver driver = BaseClass.driver;
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + status + "_" + result.getName() + "_"
				+ timestamp + ".png";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(screenshotPath));
			System.out.println("Screenshot captured for " + status + ": " + screenshotPath);
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}
}
