package com.catalystdevworks.mtidwell.timeclock.selenium.test;

import static org.junit.Assert.assertEquals;

import com.catalystdevworks.mtidwell.timeclock.selenium.State;
import com.catalystdevworks.mtidwell.timeclock.selenium.model.LoginStateModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dsloane on 6/9/2016.
 */
public class LoginStateIT extends ShellState<LoginStateModel> {

    @Before
    public void startup() {
            super.startup();
            model.navigateTo(State.LOGIN);
            }

    @Override
    public LoginStateModel createStateModel() {
            return new LoginStateModel();
        }

    @Test
    public void testRegisterButtonMessageIsDisplayed() {
            String registerMessage = model.getRegisterButtonMessage().getText();

            assertEquals("Expected register button message to match.", "Register an account", registerMessage);
        }
}
