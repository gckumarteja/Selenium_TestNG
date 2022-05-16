package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class baseTest {
    private ThreadLocal<WebDriver> driver =new ThreadLocal<WebDriver>();
    public void setDriver(WebDriver driver)
    {
        this.driver.set(driver);
    }
    public WebDriver getDriver()
    {
        return this.driver.get();
    }


    ExtentReports reports;
    ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();


    @BeforeTest
    @Parameters(value = {"browser"})
    public void setupExtentReport(String browser)
    {
        ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReport"+browser+".html");
        reports = new ExtentReports();
        reports.attachReporter(htmlreport);
    }
    public synchronized ExtentTest getTest() {
        return extent.get();
    }

    public synchronized void setTest(ExtentTest test)
    {
        extent.set(test);
    }



    @BeforeMethod
    @Parameters(value = {"browser"})
    public synchronized  void initDriver(String browser, Method test)
    {
        ExtentTest extentTest =reports.createTest(test.getName()+"_"+browser);
        setTest(extentTest);
        //getTest();
        setupDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

   /* @BeforeMethod
    //@Parameters(value = {"browser"})
    public void initDriver(Method test) {
        logger=extent.createTest(test.getName());
        setupDriver("Chrome");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }*/

    public void setupDriver(String browser)
    {
        System.out.println(browser);
        if(browser.equalsIgnoreCase("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver","D:\\Career\\Selenium_TestNG\\drivers\\chromedriver.exe");
            setDriver(new ChromeDriver());
        }
        else if(browser.equalsIgnoreCase("Edge"))
        {
            System.setProperty("webdriver.edge.driver","D:\\Career\\Selenium_TestNG\\drivers\\msedgedriver.exe");
            setDriver(new EdgeDriver());
        }
        else
        {
            System.setProperty("webdriver.chrome.driver","D:\\Career\\Selenium_TestNG\\drivers\\chromedriver.exe");
            setDriver(new ChromeDriver());
        }

    }

    @AfterMethod
    public void closeDriver(ITestResult result)
    {
        getDriver().close();
        getDriver().quit();
        //reports.removeTest(getTest());
    }

    @AfterTest
    public void finalizeExtendReports()
    {
        reports.flush();
    }

}
