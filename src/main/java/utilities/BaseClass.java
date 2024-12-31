package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class BaseClass {
	public static WebDriver driver;
	public LoginPageObjects loginPageObjects;
	public HomePageObjects homePageObjects;
	private static Properties properties;

	/**
	 * Sets up the WebDriver instance and browser configuration before the test
	 * suite starts.
	 */
	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		loadProperties();
	}

	/**
	 * Navigates to the login page before each test method execution.
	 */
	@BeforeMethod
	public void navigateToLoginPage() {
		driver.get("https://www.saucedemo.com/");
	}

	/**
	 * Initializes the Page Object classes to be used in test cases.
	 */
	@BeforeClass
	public void initializePageObjectClasses() {
		loginPageObjects = new LoginPageObjects(driver);
		homePageObjects = new HomePageObjects(driver);
	}

	/**
	 * Closes the WebDriver instance and cleans up resources after the test suite
	 * ends.
	 */
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Loads the properties from the config.properties file.
	 */
	private void loadProperties() {
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/config.properties")) {
			properties = new Properties();
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
		}
	}

	/**
	 * Gets the value for a specific property key.
	 * 
	 * @param key The property key.
	 * @return The property value.
	 */
	public static String getProperty(String key) {
		if (properties == null) {
			throw new RuntimeException("Properties file not loaded. Ensure loadProperties() is called.");
		}
		return properties.getProperty(key);
	}
}
