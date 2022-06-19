package factory;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import factory.ExtentManager;
import factory.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
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


    @BeforeSuite
    public synchronized void SetupExtentReport()
    {
        ExtentManager.createInstance();
    }


    @BeforeMethod
    @Parameters(value = {"browser"})
    public synchronized  void initDriver(@Optional("Chrome") String browser, Method test)
    {
        ExtentTestManager.createTest(test.getName()+"_"+browser);
        ExtentTestManager.getTest().assignCategory(browser);
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

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
            ExtentTestManager.getTest().fail(result.getThrowable());
        else if (result.getStatus() == ITestResult.SKIP)
            ExtentTestManager.getTest().skip(result.getThrowable());
        else
            ExtentTestManager.getTest().pass("Test passed");

      // ExtentManager.getInstance().flush();
    }

    @AfterSuite
    public void finalizeExtendReports()
    {
        //reports.flush();
        ExtentManager.getInstance().flush();
    }

    @DataProvider(name = "data")
    public Object[][] getDataFromExcelUsingFillo(Method test) throws FilloException {
        //Object[][] obj = new Object[1][1];
        Fillo file= new Fillo();
        Connection connection=file.getConnection(System.getProperty("user.dir") + "\\src\\data\\TestData.xlsx");
        Recordset rs= connection.executeQuery("Select * from Sheet1 where TestID='"+test.getName()+"' order by Iteration asc");
        int rowCnt=rs.getCount();
        int colCnt=rs.getFieldNames().size();

        Object[][] obj= new Object[rowCnt][1];
        int row=0;
        while(rs.next())
        {
            HashMap<String,String> dataMap=new HashMap<String, String>();
            for(int col=0;col<colCnt;col++)
            {

                //dataMap.put(rs.getField(col).name().toString(), rs.getField(col).value());
                dataMap.put(rs.getFieldNames().get(col), rs.getField(col).value());
//                rs.getField(col);
//                rs.getField(col).name();
            }
            System.out.println(dataMap);
            obj[row][0]=dataMap;
            row=row+1;
        }
        return obj;
    }

}
