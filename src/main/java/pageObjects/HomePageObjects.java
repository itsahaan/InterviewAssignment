package pageObjects;

import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObjects {
	private WebDriver driver;

	// Constructor to initialize WebDriver
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	private By cartBadge = By.className("shopping_cart_badge");
	private By cartLink = By.className("shopping_cart_link");
	private By hamburgerMenuIcon = By.id("react-burger-menu-btn");
	private By hamburgerMenuItems = By.xpath("//nav[@class = 'bm-item-list']/a");
	private By hamburgerMenuItemContainer = By.xpath("//a[@tabindex='-1']");

	/**
	 * Adds a specific item to the cart based on the item name.
	 */
	public void addItemToCart(String itemName) {
		driver.findElement(By.xpath("//div[text()='" + itemName
				+ "']/ancestor::div[@class='inventory_item_description']//button[text() = 'Add to cart']")).click();
	}

	/**
	 * Removes a specific item from the cart based on the item name.
	 */
	public void removeItemFromCart(String itemName) {
		driver.findElement(By.xpath("//div[text()='" + itemName
				+ "']/ancestor::div[@class='inventory_item_description']//button[text() = 'Remove']")).click();
	}

	/**
	 * Returns the total count of items currently in the cart.
	 */
	public int getCartItemCount() {
		try {
			String cartCount = driver.findElement(cartBadge).getText();
			return Integer.parseInt(cartCount);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Navigates to the cart page.
	 */
	public void openCart() {
		driver.findElement(cartLink).click();
	}

	/**
	 * Verifies whether a specific item is present in the cart based on the item
	 * name.
	 */
	public boolean isItemInCart(String itemName) {
		return driver.findElements(By.xpath("//div[text()='" + itemName + "']")).size() > 0;
	}

	/**
	 * Clicks the hamburger menu and waits until the menu items are visible.
	 */
	public void clickHamburgerMenu() {
		driver.findElement(hamburgerMenuIcon).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(hamburgerMenuItemContainer));
	}

	/**
	 * Returns the total count of visible menu items in the hamburger menu.
	 */
	public int getMenuItemCount() {
		List<WebElement> items = driver.findElements(hamburgerMenuItems);
		return items.size();
	}

	/**
	 * Verifies if the expected menu items are visible and match the provided list.
	 */
	public boolean checkExpectedMenuItemsVisible(List<String> expectedMenuItems) {
		List<WebElement> actualItems = driver.findElements(hamburgerMenuItems);
		if (actualItems.size() != expectedMenuItems.size()) {
			return false;
		}

		for (int i = 0; i < expectedMenuItems.size(); i++) {
			String actualText = actualItems.get(i).getText().trim();
			if (!actualText.equals(expectedMenuItems.get(i))) {
				return false;
			}
		}
		return true;
	}
}
