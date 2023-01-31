package testclasses;

import io.qameta.allure.Description;
import Listeners.AnnotationTransformer;
import Listeners.Listener;
import Listeners.RetryFailedTests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.HomePage;
import pageobjects.SortByPrice;
import pageobjects.SpeedBirdCafe;
import utilityobjects.BaseMultiThread;

@Listeners({Listener.class, AnnotationTransformer.class})
public class SortByTests extends BaseMultiThread {

    public static WebDriver driver;

    private HomePage homePageObject;
    private SpeedBirdCafe speedBirdCafeObject;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

        driver = getDriver();
        homePageObject = new HomePage(driver);
        speedBirdCafeObject = new SpeedBirdCafe(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        driver.manage().deleteAllCookies();
        driver.navigate().to(BaseMultiThread.url);
    }

    @Test(description = "The user will access Speedbird Cafe", retryAnalyzer = RetryFailedTests.class, groups = {"PRODUCTION"})
    @Description("The user will access Speedbird Cafe and check if the Sort by feature is displayed")
    public void verifySortingDisplayed() {

        homePageObject.clickSpeedbirdCafe();
        Assert.assertTrue(speedBirdCafeObject.verifySortByDropdown());
    }

    @Test(description = "The user will check all available sorting options", retryAnalyzer = RetryFailedTests.class, groups = {"PRODUCTION"})
    @Description("The user will check all available sorting options. Position, name, price and new arrivals")
    public void verifySortingOptions() {

        homePageObject.clickSpeedbirdCafe();
        speedBirdCafeObject.clickSortByByDropdown();
        Assert.assertTrue(speedBirdCafeObject.verifySelectNameOption()&& speedBirdCafeObject.verifySelectPriceOption()&&speedBirdCafeObject.verifySelectNewArrivalsOption()&&speedBirdCafeObject.verifySelectPositionOption());
    }

    @Test(description = "The user will check sorting by prices on the first page", retryAnalyzer = RetryFailedTests.class, groups = {"PRODUCTION"})
    @Description("The user will check sorting by prices on the first page")
    public void verifySortingByPrices() throws InterruptedException {

        homePageObject.clickSpeedbirdCafe();
        homePageObject.clickAllowAllCookies();
        speedBirdCafeObject.clickSortByByDropdown();
        SortByPrice.verifySortingByPrices();
    }

    @AfterClass(alwaysRun = true)
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
