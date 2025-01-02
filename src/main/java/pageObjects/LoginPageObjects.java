package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CustomWebElement;
import utilities.ElementUtil;

public class LoginPageObjects {
	private WebDriver driver;

	// Constructor to initialize WebDriver
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By loginLogo = By.className("login_logo");
	private By errorMessageContainer = By.xpath("//*[contains(@class, 'error-message-container')]");

	/**
	 * Enters the username into the username field.
	 */
	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}

	/**
	 * Enters the password into the password field.
	 */
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	/**
	 * Clicks the login button to submit the login form.
	 */
	public void clickLoginButton() {
		CustomWebElement loginButtonElement = ElementUtil.getCustomElement(driver, loginButton);
		loginButtonElement.click();
	}

	/**
	 * Retrieves the text of the login logo.
	 */
	public String getLoginLogoText() {
		CustomWebElement loginLogoElement = ElementUtil.getCustomElement(driver, loginLogo);
		return loginLogoElement.getText();
	}

	/**
	 * Checks if the error message is displayed on the login page.
	 */
	public boolean isErrorMessageDisplayed() {
		return driver.findElement(errorMessageContainer).isDisplayed();
	}

	/**
	 * Gets the current page URL.
	 */
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Logs in using the provided username and password.
	 *
	 * @param username The username to log in.
	 * @param password The password to log in.
	 */
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}
}
