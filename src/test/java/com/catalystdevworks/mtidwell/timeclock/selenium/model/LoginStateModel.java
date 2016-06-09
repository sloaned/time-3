package com.catalystdevworks.mtidwell.timeclock.selenium.model;

import com.catalystdevworks.mtidwell.timeclock.selenium.BaseStateModel;
import com.catalystdevworks.mtidwell.timeclock.selenium.State;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by dsloane on 6/9/2016.
 */
public class LoginStateModel extends BaseStateModel {

    private WebElement registerButtonMessage;

    @Override
    protected void findStateWebElements(State state) {
        registerButtonMessage = webDriver.findElement(By.id("registerButton"));
    }

    public WebElement getRegisterButtonMessage() {
        return registerButtonMessage;
    }
}
