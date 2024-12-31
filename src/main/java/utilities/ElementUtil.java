package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Utility class to wrap WebElement instances with CustomWebElement for extended
 * functionality.
 */

public class ElementUtil {

	/**
	 * Returns a CustomWebElement instance for the given locator.
	 * 
	 * @param driver  The WebDriver instance.
	 * @param locator The locator to find the WebElement.
	 * @return A wrapped CustomWebElement instance.
	 */
	public static CustomWebElement getCustomElement(WebDriver driver, By locator) {
		return new CustomWebElement(driver.findElement(locator));
	}
}
