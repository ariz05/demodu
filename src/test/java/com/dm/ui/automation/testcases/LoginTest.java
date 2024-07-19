package com.dm.ui.automation.testcases;

import com.dm.ui.automation.objectrepositories.HomePage;
import com.dm.ui.automation.objectrepositories.LoginPage;
import com.dm.ui.automation.objectrepositories.SubscriptionPage;
import com.dm.ui.automation.utilities.CommonUtils;
import com.dm.ui.automation.utilities.ReportListener;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

@Listeners({ReportListener.class})
public class LoginTest extends BaseClass {

    //Positive scenario - All fields are provided with valid values
    @Test(dataProvider = "ValidLogin")
    @Description("Test to validate Login Functionality")
    @Epic("EP002 - UI Testing Login Page")
    @Feature("Login page Validation")
    @Story("Story : Login Functionality is working using valid credentials")
    @Step("Verify Login functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testValidLogin(HashMap<String, String> hshMap) throws IOException {
        System.out.println("Testcase Description : " + hshMap.get("testcase description"));
        //Home page Login button is clicked.
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();

        //Provide credentials on Login page.
        LoginPage loginPage = new LoginPage(driver);
        String email = hshMap.get("email");
        String password = hshMap.get("password");
        loginPage.provideEmail(email);
        loginPage.clickContinueButton();
        loginPage.providePassword(password);
        loginPage.clickLoginButton();

        //Successful Login validation
        SubscriptionPage subscriptionPage = new SubscriptionPage(driver);
        CommonUtils.assertFields("boolean", "SubscriptionButton", "true", subscriptionPage.checkSubscriptionButtonPresence());

    }

    //Error scenario to check login using invalid credentials.
    @Test(dataProvider = "InvalidLogin")
    @Description("Test to validate Login Functionality")
    @Epic("EP002 - UI Testing Login Page")
    @Feature("Login page Validation")
    @Story("Story : Login Functionality throws error message using invalid credentials")
    @Step("Verify Login functionality error scenario")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidLogin(HashMap<String, String> hshMap) throws IOException {
        System.out.println("Testcase Description : " + hshMap.get("testcase description"));
        //Home page Login button is clicked.
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();

        //Provide credentials on Login page.
        LoginPage loginPage = new LoginPage(driver);
        String email = hshMap.get("email");
        String password = hshMap.get("password");
        loginPage.provideEmail(email);
        loginPage.clickContinueButton();
        loginPage.providePassword(password);
        loginPage.clickLoginButton();

        //Validate Error message.
        CommonUtils.assertFields("equals", "Login Error Message ", hshMap.get("validation"), loginPage.getErrorMessageText());
    }


    @DataProvider(name = "ValidLogin")
    Object[][] getValidLoginData() throws IOException {
        return xUtils.getTestData("Login", "TC_Login_ValidCredentials_01");
    }

    @DataProvider(name = "InvalidLogin")
    Object[][] getInvalidLoginData() throws IOException {
        return xUtils.getTestData("Login", "TC_Login_InvalidCredentials_02");
    }


}
