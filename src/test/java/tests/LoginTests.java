package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import utilities.BaseClass;
import utilities.DataProviderUtil;

public class LoginTests extends BaseClass {

	/**
	 * Verifies login functionality using multiple sets of credentials and checks if
	 * the login behavior matches the expected outcome.
	 *
	 * @param username          The username to log in.
	 * @param password          The password to log in.
	 * @param isSuccessExpected Whether the login is expected to succeed.
	 */
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
	public void loginTest(String username, String password, boolean isSuccessExpected) {
		loginPageObjects.login(username, password);
		if (isSuccessExpected) {
			Assert.assertTrue(loginPageObjects.getCurrentPageUrl().contains("inventory.html"),
					"User should be redirected to the inventory page.");
		} else {
			Assert.assertTrue(loginPageObjects.isErrorMessageDisplayed(),
					"Error message should be displayed for invalid login.");
		}
	}

	/**
	 * Validates a successful login and verifies that the key is present in the
	 * browser's local storage.
	 */
	@Test
	public void positiveLoginTestWithLocalStorageCheck() {
		String logoText = loginPageObjects.getLoginLogoText();
		Assert.assertEquals(logoText, "Swag Labs");

		loginPageObjects.login(getProperty("username"), getProperty("password"));
		Assert.assertTrue(loginPageObjects.getCurrentPageUrl().contains("inventory.html"));

		String sessionValue = (String) ((JavascriptExecutor) driver)
				.executeScript("return localStorage.getItem('backtrace-guid');");
		Assert.assertNotNull(sessionValue, "Local Storage should contain 'backtrace-guid'.");
	}

	/**
	 * Verifies that the session cookie contains the correct username after a
	 * successful login.
	 */
	@Test
	public void validateSessionUsernameAfterLogin() {
		loginPageObjects.login(getProperty("username"), getProperty("password"));
		String sessionCookie = driver.manage().getCookieNamed("session-username").getValue();
		Assert.assertEquals(sessionCookie, "standard_user");
	}
}
