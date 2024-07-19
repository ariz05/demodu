package com.dm.ui.automation.testcases;

import com.dm.ui.automation.objectrepositories.HomePage;
import com.dm.ui.automation.objectrepositories.LoginPage;
import com.dm.ui.automation.utilities.CommonUtils;
import com.dm.ui.automation.utilities.ReportListener;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static com.dm.ui.automation.utilities.CommonUtils.getTimeZoneDate;


@Listeners({ReportListener.class})
public class HomePageTest extends BaseClass {

    //Positive scenario - All fields are provided with valid values
    @Test(dataProvider = "LoginValidation")
    @Description("Test to validate Home Page Login button.")
    @Epic("EP001 - UI Testing Homepage")
    @Feature("Home page Validation")
    @Story("Story : Login button is working as expected.")
    @Step("Verify Login page is opened on clicking login button")
    @Severity(SeverityLevel.NORMAL)
    public void openLoginPage(HashMap<String, String> hshMap) throws IOException {

        System.out.println("Testcase Description : " + hshMap.get("testcase description"));
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        //Validate login page is opened.
        LoginPage loginPage = new LoginPage(driver);
        CommonUtils.assertFields("equals", "LoginPageTitle", hshMap.get("validation"), loginPage.getLoginPageTitle());

    }

    @Test(dataProvider = "DateValidation")
    @Description("Test to validate today's date on homepage.")
    @Epic("EP001 - UI Testing Homepage")
    @Feature("Home page Validation")
    @Story("Story : Date is displayed as expected.")
    @Step("Verify today's date on home page.")
    @Severity(SeverityLevel.NORMAL)
    public void validateDate(HashMap<String, String> hshMap) throws IOException {
        System.out.println("Testcase Description : " + hshMap.get("testcase description"));
        HomePage homePage = new HomePage(driver);
        CommonUtils.assertFields("equals", "HomePageTitle", hshMap.get("validation"), homePage.getHomePageTitle());
        //Validate today's date.
        String pattern = "EEEE, MMMM dd, yyyy";
        String date = getTimeZoneDate(new Date(), pattern, "America/New_York");
        CommonUtils.assertFields("equals", "HomePageDate", date, homePage.getTodayDate());

    }

    @DataProvider(name = "LoginValidation")
    Object[][] getTestData() throws IOException {
        return xUtils.getTestData("Homepage", "TC_HomePage_LoginButton_01");
    }

    @DataProvider(name = "DateValidation")
    Object[][] getDateValidationData() throws IOException {
        return xUtils.getTestData("Homepage", "TC_HomePage_TodaysDateValidation_02");
    }


}
