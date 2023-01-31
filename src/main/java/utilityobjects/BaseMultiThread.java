package utilityobjects;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseMultiThread
{

    public ThreadLocal<WebDriver> driver = null;
    public static String browser;
    public static String environment;
    public static String url;
    public Dotenv dotenv = Dotenv.load();
    final private DesiredCapabilities caps = new DesiredCapabilities();

    @Parameters({"browser", "environment"})
    @BeforeSuite(alwaysRun = true)
    public void setEnvData(String browser, String environment)
    {

        BaseMultiThread.browser = browser;
        BaseMultiThread.environment = environment;

        url = SetUrl.setEnvUrl(environment);
    }

    @BeforeClass(alwaysRun = true)
    protected void setUp() throws MalformedURLException
    {

         driver = new ThreadLocal<>();
         String remoteUrl;
         //Local Selenium Grid
         String gridURL = dotenv.get("GRID_URL");
         assert gridURL != null;
         System.out.println(gridURL);
         URL url = new URL(gridURL);
         driver.set(new RemoteWebDriver(url, desiredCaps(browser)));
    }

    public WebDriver getDriver()
    {
        return driver.get();
    }

    public DesiredCapabilities desiredCaps(String browser)
    {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            caps.setCapability(ChromeOptions.CAPABILITY, options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("pageLoadStrategy", "eager");
            caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        }
        caps.setCapability("browserName", browser);
        caps.setCapability("idleTimeout", 300);
        return caps;
    }

    // takes screenshot in case test case fails and adds it in the screenshot folder
    @AfterMethod(alwaysRun = true)
    public void screenShotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshotPNG(getDriver());
            System.out.println("Failed");
            System.out.println("Taking screenshot ..");
        }
    }

    //Screenshot for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver)
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

