package com.catalystdevworks.mtidwell.timeclock.selenium.model;

import com.catalystdevworks.mtidwell.timeclock.selenium.BaseStateModel;
import com.catalystdevworks.mtidwell.timeclock.selenium.State;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by dsloane on 6/9/2016.
 */
public class RegisterStateModel extends BaseStateModel {

    private WebElement clearButton;

    @Override
    protected void findStateWebElements(State state) {
        clearButton = webDriver.findElement(By.id("clearButtonText"));
    }

    public WebElement getClearButton() {
        return clearButton;
    }
}
