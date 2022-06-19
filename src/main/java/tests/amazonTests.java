package tests;

import com.aventstack.extentreports.ExtentTest;
import factory.ExtentTestManager;
import factory.baseTest;
import org.testng.annotations.Test;
import pages.amazonPages;

import java.util.HashMap;

public class amazonTests extends baseTest {

    ExtentTest log;
    @Test(dataProvider = "data")
    public void searchMobilePhones(HashMap<String,String> data) throws InterruptedException {
        //amazonPages amazon = PageFactory.initElements(getDriver(), amazonPages.class);
        amazonPages amazon = new amazonPages(getDriver(), ExtentTestManager.getTest());
        log=ExtentTestManager.getTest();
        //getDriver().get("https://www.amazon.in/");
        amazon.openAmazon("https://www.amazon.in/");
        amazon.search(data);
        log.info("Map Return : "+data.get("Status"));
    }

    @Test(dataProvider = "data")
    public void searchLaptops(HashMap<String,String> data) throws InterruptedException {
        //amazonPages amazon = PageFactory.initElements(getDriver(), amazonPages.class);
        amazonPages amazon = new amazonPages(getDriver(),ExtentTestManager.getTest());
        log=ExtentTestManager.getTest();
        //getDriver().get("https://www.amazon.in/");
        amazon.openAmazon("https://www.amazon.in/");
        amazon.search(data);
        log.info("Map Return : "+data.get("Status"));
    }
}
