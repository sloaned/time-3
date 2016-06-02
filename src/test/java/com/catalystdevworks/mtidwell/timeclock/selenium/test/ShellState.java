package com.catalystdevworks.mtidwell.timeclock.selenium.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.catalystdevworks.mtidwell.timeclock.selenium.BaseStateModel;
import com.catalystdevworks.mtidwell.timeclock.selenium.State;

/**
 * Base test class for all pages.  This will use {@link ShellState#startup()} to initialize the webdriver
 * and will handle shutting down the webdirer in {@link ShellState#teardown()}.
 *
 * @param <Model> Implementation of the State Model specific for these tests.
 */
public abstract class ShellState<Model extends BaseStateModel> {
	
	/**
	 * Will be created using {@link #createStateModel()} in the {@link #startup()} method.
	 */
	protected Model model;
	
	/**
	 * Implementers are expected to construct their state model here.  Do not call {@link BaseStateModel#init()}
	 * @return Constructed state model for this set off selenium tests.
	 */
	public abstract Model createStateModel();
	
	@Before
	public void startup() {
		model = createStateModel();
		model.init();
	}
	
	@After
	public void teardown() {
		model.logBrowserConsole();
		model.shutdown();
	}
	
	@Test
	public void testHomeLinkIsPresent() {
		WebElement navHomeLink = model.getNavHomeLink();
		String actualHomeUrl = navHomeLink.getAttribute("href");
		
		assertEquals("Wrong href assigned to the home link.", model.getStateUrl(State.HOME), actualHomeUrl);
	}
	
	@Test
	public void testUserLinkIsPresent() {
		WebElement navUserLink = model.getNavUserLink();
		String actualUserUrl = navUserLink.getAttribute("href");
		
		assertEquals("Wrong href assigned to the user link.", model.getStateUrl(State.USER), actualUserUrl);
	}
	
}
