package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By titleElement = By.className("title");
    private By addToCartBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitleText() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleElement));
        return title.getText();
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackButton)).click();
    }

    public String getCartBadgeText() {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return badge.getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}