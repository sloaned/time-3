package com.catalystdevworks.mtidwell.timeclock.selinum;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.catalystdevworks.mtidwell.timeclock.selinum.state.State;


public class BaseStateModel {
	private static Logger logger = Logger.getLogger(BaseStateModel.class);
	
	private static String BASE_URL;
	{
		BASE_URL = System.getProperty("selenium.baseUrl");
		if (StringUtils.isEmpty(BASE_URL)) {
			throw new IllegalArgumentException("Must provide system property 'selenium.baseUrl'.");
		}
	}
	
	protected WebDriver webDriver;
	
	public void init() {
		// TODO : logic to determine web driver to use
		webDriver = new FirefoxDriver();
		
		webDriver.manage().window().maximize();
		webDriver.get(BASE_URL);
	}
	
	public void navigateTo(State state) {
		webDriver.navigate().to(getStateUrl(state));
	}
	
	public String getStateUrl(State state) {
		return new StringBuilder(BASE_URL)
				.append('#')
				.append(state.getUrl())
				.toString();
	}
}
