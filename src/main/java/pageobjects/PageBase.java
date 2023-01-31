package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;

import static java.time.Duration.*;

public class PageBase {

    protected JavascriptExecutor jse;
    protected Actions actions;
    protected WebDriverWait mediumWaitTime;
    protected WebDriverWait maximumWaitTime;
    protected WebDriverWait minimumWaitTime;
    protected Wait<WebDriver> waitFluently;
    protected WebDriverWait lessWaitTime;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        minimumWaitTime = new WebDriverWait(driver, 5);
        mediumWaitTime = new WebDriverWait(driver, 10);
        maximumWaitTime = new WebDriverWait(driver, 20);
        lessWaitTime = new WebDriverWait(driver, 3);

        jse = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        waitFluently = new FluentWait<>(driver)
                .withTimeout(ofSeconds(15))
                .pollingEvery(ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    @Step("Click on the button with click function")
    protected void clickButton(WebElement button) {
        try {
            minimumWaitTime.until(ExpectedConditions.elementToBeClickable(button));
            Thread.sleep(500);
            button.click();
        } catch (Exception e) {
            clickButtonJs(button) ;
        }
    }

    @Step("Click on the button with Js script")
    protected void clickButtonJs(WebElement Button) {
        jse.executeScript("arguments[0].click();", Button);
    }

    @Step("Set text on a text element field")
    public void setTextElementText(WebElement textElement, String value) {
        minimumWaitTime.until(ExpectedConditions.elementToBeClickable(textElement)).clear();
        textElement.sendKeys(value);
    }

    @Step("Check if element is visible or not")
    public boolean checkVisibilityOfElement(WebElement element) {
        try {
            mediumWaitTime.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Scroll to the bottom of the page with exact yCoordinate value ")
    public void scrollToBottom(long yCoordinate) {
        jse.executeScript("scrollBy(0," + yCoordinate + ")");
    }

    @Step("Scroll to the bottom of the page to the exact element ")
    public void scrollToExactElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Explicit wait until element is visible")
    public void waitUntilElementIsVisible(WebElement element) {
        mediumWaitTime.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Explicit wait until element is clickable")
    public void waitUtilElementIsClickable(WebElement element) {
        mediumWaitTime.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Check if element is visible or not")
    public boolean checkVisibilityRetrying(WebElement element) {
        try {
            lessWaitTime.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

