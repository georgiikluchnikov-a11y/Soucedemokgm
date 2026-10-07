package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getCompleteHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        return header.getText();
    }
}