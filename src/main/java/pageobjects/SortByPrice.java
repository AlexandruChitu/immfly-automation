package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testclasses.SortByTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByPrice {

    static WebDriver driver = SortByTests.driver;

    public static void verifySortingByPrices() throws InterruptedException {
    List<WebElement> beforeFilterPrice = driver.findElements(By.className("price"));
    List<Double> beforeFilterPriceList = new ArrayList<>();
    System.out.println(beforeFilterPriceList);

    for(WebElement p : beforeFilterPrice) {
        beforeFilterPriceList.add(Double.valueOf(p.getText().replace("£", "")));
        }

    driver.findElement(By.id("sorter")).click();
    driver.findElement(By.cssSelector("select#sorter > option[value='price']")).click();
    Thread.sleep(3000);

    List<WebElement> afterFilterPrice = driver.findElements(By.className("price"));
    List<Double> afterFilterPriceList = new ArrayList<>();

    for(WebElement p : afterFilterPrice) {
        afterFilterPriceList.add(Double.valueOf(p.getText().replace("£", "")));
        }

    Collections.sort(beforeFilterPriceList);
    Assert.assertEquals(beforeFilterPrice, afterFilterPriceList);
    }
}
