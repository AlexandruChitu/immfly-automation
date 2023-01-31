package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpeedBirdCafe extends PageBase {

    @FindBy(id = "sorter")
    protected WebElement sortByDropdown;
    @FindBy(css = "select#sorter > option[value='position']")
    protected WebElement positionOption;
    @FindBy(css = "select#sorter > option[value='name']")
    protected WebElement nameOption;
    @FindBy(css = "select#sorter > option[value='price']")
    protected WebElement priceOption;
    @FindBy(css = "select#sorter > option[value='new']")
    protected WebElement newArrivalsOption;

    @Step("User verifies that Sort By dropdown is displayed within Product List page")
    public boolean verifySortByDropdown() {
        waitUntilElementIsVisible(sortByDropdown);
        return checkVisibilityOfElement(sortByDropdown);
    }

    @Step("User clicks on Sort By dropdown")
    public void clickSortByByDropdown() {
        waitUtilElementIsClickable(sortByDropdown);
        clickButton(sortByDropdown);
    }

    @Step("User selects position option")
    public void clickSelectPositionOption() {
        waitUtilElementIsClickable(positionOption);
        clickButton(positionOption);
    }

    @Step("User verifies position option")
    public boolean verifySelectPositionOption() {
        waitUntilElementIsVisible(positionOption);
        return checkVisibilityOfElement(positionOption);
    }

    @Step("User selects name option")
    public void clickNameOption() {
        waitUtilElementIsClickable(nameOption);
        clickButton(nameOption);
    }

    @Step("User verifies name option")
    public boolean verifySelectNameOption() {
        waitUntilElementIsVisible(nameOption);
        return checkVisibilityOfElement(nameOption);
    }

    @Step("User selects price option")
    public void clickPriceOption() {
        waitUtilElementIsClickable(priceOption);
        clickButton(priceOption);
    }

    @Step("User verifies price option")
    public boolean verifySelectPriceOption() {
        waitUntilElementIsVisible(priceOption);
        return checkVisibilityOfElement(priceOption);
    }

    @Step("User selects new arrivals option")
    public void clickNewArrivalsOption() {
        waitUtilElementIsClickable(newArrivalsOption);
        clickButton(newArrivalsOption);
    }

    @Step("User verifies new arrivals option")
    public boolean verifySelectNewArrivalsOption() {
        waitUntilElementIsVisible(newArrivalsOption);
        return checkVisibilityOfElement(newArrivalsOption);
    }

    public SpeedBirdCafe(WebDriver driver) {
        super(driver);
    }
}
