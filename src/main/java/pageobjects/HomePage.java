package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

    @FindBy(css = "#amgdprcookie-form > div.amgdprcookie-policy-container > div.amgdprcookie-buttons-block > button.amgdprcookie-button.-allow")
    protected WebElement clickAllowAll;
    @FindBy(css = ".v-navigation__link--nav-1")
    protected WebElement accessSpeedBirdCafe;

    @Step("User click on allow all cookies")
    public void clickAllowAllCookies() {
        waitUtilElementIsClickable(clickAllowAll);
        clickButton(clickAllowAll);
    }

    @Step("User clicks on the Speedbird Cafe nav button")
    public void clickSpeedbirdCafe() {
        waitUtilElementIsClickable(accessSpeedBirdCafe);
        clickButton(accessSpeedBirdCafe);
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
