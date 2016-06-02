package com.catalystdevworks.mtidwell.timeclock.selenium.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.catalystdevworks.mtidwell.timeclock.selenium.State;
import com.catalystdevworks.mtidwell.timeclock.selenium.model.HomeStateModel;

public class HomeStateIT extends ShellState<HomeStateModel> {
	
	@Before
	public void startup() {
		super.startup();
		model.navigateTo(State.HOME);
	}
	
	@Override
	public HomeStateModel createStateModel() {
		return new HomeStateModel();
	}
	
	@Test
	public void testHomeMessageIsDisplayed() {
		String homeMessage = model.getHomeMessage().getText();
		
		assertEquals("Expected home message to match.", "Here is a value!", homeMessage);
	}

}
