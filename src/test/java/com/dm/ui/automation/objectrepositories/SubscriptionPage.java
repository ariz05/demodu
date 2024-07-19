package com.dm.ui.automation.objectrepositories;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubscriptionPage {

    WebDriver lDriver;

    @FindBy(xpath = "//*[@data-testid = 'subscribe-button']")
    WebElement btnSubscription;

    public SubscriptionPage(WebDriver lDriver) {
        this.lDriver = lDriver;
        PageFactory.initElements(lDriver, this);
    }

    @Step("check subscription button presence.")
    public boolean checkSubscriptionButtonPresence() {
        if (btnSubscription.getText().equalsIgnoreCase("Subscribe now")) {
            System.out.println("Subscribe Now button is present");
            return true;
        }
        return false;
    }
}
