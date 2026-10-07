package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessageContainer = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        userField.clear();
        userField.sendKeys(username);

        WebElement passField = driver.findElement(passwordInput);
        passField.clear();
        passField.sendKeys(password);

        driver.findElement(loginButton).click();
    }

    public String getErrorMessageText() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer));
        return error.getText();
    }
}