package com.catalystdevworks.mtidwell.timeclock.selenium.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.catalystdevworks.mtidwell.timeclock.selenium.BaseStateModel;
import com.catalystdevworks.mtidwell.timeclock.selenium.State;

public class HomeStateModel extends BaseStateModel {
	private WebElement homeMessage;
	
	@Override
	protected void findStateWebElements(State state) {
		homeMessage = webDriver.findElement(By.id("home-message"));
	}

	public WebElement getHomeMessage() {
		return homeMessage;
	}
}
