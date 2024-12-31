package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import utilities.BaseClass;

public class HomePageTests extends BaseClass {

	/**
	 * Verifies that an item can be added to the cart and is displayed correctly in
	 * the cart page.
	 */
	@Test
	public void addItemToCart() {
		loginPageObjects.login(getProperty("username"), getProperty("password"));
		homePageObjects.addItemToCart(getProperty("product_backpack"));
		int cartCount = homePageObjects.getCartItemCount();
		Assert.assertEquals(cartCount, 1, "Cart count should be 1 after adding an item.");
		homePageObjects.openCart();
		Assert.assertTrue(homePageObjects.isItemInCart(getProperty("product_backpack")),
				"Sauce Labs Backpack should be present in the cart.");
	}

	/**
	 * Verifies that an item can be removed from the cart and is no longer displayed
	 * in the cart page.
	 */
	@Test
	public void removeItemFromCart() {
		loginPageObjects.login(getProperty("username"), getProperty("password"));
		homePageObjects.addItemToCart(getProperty("product_fleece_jacket"));
		homePageObjects.removeItemFromCart(getProperty("product_fleece_jacket"));
		homePageObjects.openCart();
		Assert.assertFalse(homePageObjects.isItemInCart(getProperty("product_fleece_jacket")),
				"Sauce Labs Fleece Jacket should not be present in the cart.");
	}

	/**
	 * Verifies that the hamburger menu displays exactly four menu items with the
	 * correct names.
	 */
	@Test
	public void verifyHamburgerMenuItems() {
		loginPageObjects.login(getProperty("username"), getProperty("password"));
		homePageObjects.clickHamburgerMenu();
		int visibleMenuItems = homePageObjects.getMenuItemCount();
		Assert.assertEquals(visibleMenuItems, 4, "There should be exactly 4 menu items visible.");
		List<String> expectedMenuItems = Arrays.asList(getProperty("menu_item_1"), getProperty("menu_item_2"),
				getProperty("menu_item_3"), getProperty("menu_item_4"));
		boolean areMenuItemsCorrect = homePageObjects.checkExpectedMenuItemsVisible(expectedMenuItems);
		Assert.assertTrue(areMenuItemsCorrect, "The menu items do not match the expected names.");
	}
}
