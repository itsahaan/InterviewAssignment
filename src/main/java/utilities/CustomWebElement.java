package utilities;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class CustomWebElement implements WebElement {
	private final WebElement originalElement;

	/**
	 * Constructor to wrap an existing WebElement.
	 */
	public CustomWebElement(WebElement originalElement) {
		this.originalElement = originalElement;
	}

	/**
	 * Retrieves the text of the element. If the element has a "value" attribute, it
	 * is returned. Otherwise, "innerText" is returned.
	 */
	public String getText() {
		String value = originalElement.getAttribute("value");
		if (value != null && !value.isEmpty()) {
			return value;
		}
		return originalElement.getAttribute("innerText");
	}

	/**
	 * Clicks the element.
	 */
	public void click() {
		originalElement.click();
	}

	/**
	 * Submits the form containing this element.
	 */
	public void submit() {
		originalElement.submit();
	}

	/**
	 * Sends a sequence of characters to the element.
	 */
	public void sendKeys(CharSequence... keysToSend) {
		originalElement.sendKeys(keysToSend);
	}

	/**
	 * Clears the content of the element.
	 */
	public void clear() {
		originalElement.clear();
	}

	/**
	 * Retrieves the tag name of the element.
	 */
	public String getTagName() {
		return originalElement.getTagName();
	}

	/**
	 * Retrieves the value of the specified attribute.
	 */
	public String getAttribute(String name) {
		return originalElement.getAttribute(name);
	}

	/**
	 * Checks whether the element is selected.
	 */
	public boolean isSelected() {
		return originalElement.isSelected();
	}

	/**
	 * Checks whether the element is enabled.
	 */
	public boolean isEnabled() {
		return originalElement.isEnabled();
	}

	/**
	 * Retrieves the CSS value of the specified property.
	 */
	public String getCssValue(String propertyName) {
		return originalElement.getCssValue(propertyName);
	}

	/**
	 * Checks whether the element is displayed.
	 */
	public boolean isDisplayed() {
		return originalElement.isDisplayed();
	}

	/**
	 * Retrieves the location of the element on the page.
	 */
	public Point getLocation() {
		return originalElement.getLocation();
	}

	/**
	 * Retrieves the size of the element.
	 */
	public Dimension getSize() {
		return originalElement.getSize();
	}

	/**
	 * Retrieves the rectangular region of the element.
	 */
	public Rectangle getRect() {
		return originalElement.getRect();
	}

	/**
	 * Captures a screenshot of the element.
	 */
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return originalElement.getScreenshotAs(target);
	}

	/**
	 * Finds child elements of this element using the given locator.
	 */
	public List<WebElement> findElements(By by) {
		return originalElement.findElements(by);
	}

	/**
	 * Finds a child element of this element using the given locator.
	 */
	public WebElement findElement(By by) {
		return originalElement.findElement(by);
	}

}
