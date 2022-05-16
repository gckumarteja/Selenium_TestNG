package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.baseTest;

public class amazonPages {

    WebDriver driver;
    ExtentTest logger;
    public amazonPages(WebDriver driver,ExtentTest log)
    {
        this.driver=driver;
        this.logger= log;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchBox;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement search;
    public void openAmazon(String url)
    {
        driver.get(url);
        logger.log(Status.PASS,"Navigated to the specified URL :"+url);

    }

    public void search(String seachwith)
    {
        searchBox.sendKeys(seachwith);
        logger.log(Status.PASS, "Searched with :" +seachwith );
        search.click();
        logger.log(Status.PASS, "Clicked on Search icon");
    }


}
