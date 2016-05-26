package com.catalystdevworks.mtidwell.timeclock.selinum.state;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum State {
	HOME("home", "/"),
	USER("user", "/user"),
	USER_PROFILE("user-profile", "/user/profile/:userId"),
	USER_PROFILE_EDIT("user-profile-edit", "/user/edit/:userId"),
	USER_REGISTER("user_register", "/user/register");
	
	private static final Pattern PATH_PARAMETER = Pattern.compile("/:([a-zA-Z]+)");
	
	private String stateName;
	private String url;
	State(String stateName, String url) {
		this.stateName = stateName;
		this.url = url;
	}
	public String getStateName() {
		return stateName;
	}
	public String getUrl() {
		return url;
	}
	public boolean hasPathParameters() {
		return getUrl().contains(":");
	}
	public String getUrl(Map<String, String> pathParameters) {
		String url = getUrl();
		if (hasPathParameters()) {
			do {
				Matcher matcher = PATH_PARAMETER.matcher(url);
				if (!matcher.find()) {
					break;
				}
				String parameterName = matcher.group(1);
				String parameter = pathParameters.get(parameterName);
				if (parameter == null) {
					throw new IllegalArgumentException(String.format("Required path parmeter [%s] was not set.", parameterName));
				}
				url = url.replaceAll(matcher.group(), '/'+parameter);
			} while (true);
			return url;
		}
		return url;
	}
}
