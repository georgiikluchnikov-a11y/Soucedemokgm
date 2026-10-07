package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SoucedemokgmTest extends TestBase {

    @Test(description = "1. Позитивный тест: Авторизация под стандартным пользователем")
    public void testPositiveLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getTitleText(), "Products", "Не удалось войти в систему или открыть каталог");
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "URL должен содержать /inventory.html");
    }

    @Test(description = "2. Негативный тест: Авторизация заблокированного пользователя")
    public void testNegativeLoginLockedUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        String actualError = loginPage.getErrorMessageText();
        Assert.assertTrue(actualError.contains("Epic sadface: Sorry, this user has been locked out."),
                "Ожидалось сообщение о блокировке пользователя");
    }

    @Test(description = "3. Позитивный тест: Добавление товара в корзину и проверка её состава")
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1", "Счётчик корзины должен быть равен 1");

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getItemNameText(), "Sauce Labs Backpack", "Наименование товара в корзине не совпадает");
    }

    @Test(description = "4. Позитивный тест: Полное сквозное оформление заказа (Checkout)")
    public void testCompleteCheckoutProcess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInformation("Maxim", "Vladykin", "101000");
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getCompleteHeaderText(), "Thank you for your order!",
                "Заказ не был успешно оформлен");
    }
}