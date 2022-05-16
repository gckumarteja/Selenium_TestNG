package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.amazonPages;

public class amazonTests extends baseTest{

    @Test
    public void searchOnepuls() throws InterruptedException {
        //amazonPages amazon = PageFactory.initElements(getDriver(), amazonPages.class);
        amazonPages amazon = new amazonPages(getDriver(),getTest());
        //getDriver().get("https://www.amazon.in/");
        amazon.openAmazon("https://www.amazon.in/");
        amazon.search("OnePlus");
        //Thread.sleep(1000);
    }

    @Test
    public void searchIphone() throws InterruptedException {
        //amazonPages amazon = PageFactory.initElements(getDriver(), amazonPages.class);
        amazonPages amazon = new amazonPages(getDriver(),getTest());
        //getDriver().get("https://www.amazon.in/");
        amazon.openAmazon("https://www.amazon.in/");
        amazon.search("Iphone");
        //Thread.sleep(1000);
    }
}
