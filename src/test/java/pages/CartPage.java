package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItemName = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getItemNameText() {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemName));
        return item.getText();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}