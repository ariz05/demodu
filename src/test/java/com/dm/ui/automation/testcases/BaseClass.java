package com.dm.ui.automation.testcases;

import com.dm.ui.automation.utilities.ReadConfig;
import com.dm.ui.automation.utilities.XLUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();
    public String browserName = readconfig.getBrowserName();
    public XLUtils xUtils = new XLUtils();
    public WebDriver driver;

    @BeforeSuite
    public void start() {
        System.out.println("Starting Suite Execution.");
    }

    @BeforeMethod
    public void startMethod() {
        System.out.println("Test Execution started .....");
        System.out.println("Browser set to : "+browserName);
        if (browserName.equalsIgnoreCase("CHROME")) {
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("FIREFOX")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("EDGE")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("SAFARI")) {
            driver = new SafariDriver();
        } else {
            throw new Error("Browser name is not provided correctly. Please mention correct browser name");

        }
        System.out.println("Website URL is : "+baseURL);
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod
    public void endTestMethod() {
        System.out.println("Test execution Ended....");
        driver.quit();
    }

    @AfterSuite
    public void endTest() {
        System.out.println("Ending Suite Execution");
    }

}


