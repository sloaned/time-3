package com.catalystdevworks.mtidwell.timeclock.selenium.test;

import com.catalystdevworks.mtidwell.timeclock.selenium.State;
import com.catalystdevworks.mtidwell.timeclock.selenium.model.LoginStateModel;
import com.catalystdevworks.mtidwell.timeclock.selenium.model.RegisterStateModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dsloane on 6/9/2016.
 */
public class RegisterStateIT extends ShellState<RegisterStateModel> {

    @Before
    public void startup() {
        super.startup();
        model.navigateTo(State.REGISTER);
    }

    @Override
    public RegisterStateModel createStateModel() {
        return new RegisterStateModel();
    }
/*
    @Test
    public void testClearButtonTextIsDisplayed() {
        String clearButtonText = model.getClearButton().getText();

        assertEquals("Expected clear button text to match.", "Clear", clearButtonText);
    }  */
}