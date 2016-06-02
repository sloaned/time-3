package com.catalystdevworks.mtidwell.timeclock.selenium;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is expected to be kept up to date with the states assigned to the angular-ui <code>$stateProvider</code> object.
 */
public enum State {
	HOME("home", "/"),
	USER("user", "/user"),
	USER_PROFILE("user-profile", "/user/profile/:userId"),
	USER_PROFILE_EDIT("user-profile-edit", "/user/edit/:userId"),
	USER_REGISTER("user-register", "/user/register");
	
	private static final Pattern PATH_PARAMETER = Pattern.compile("/:([a-zA-Z]+)");
	
	private String stateName;
	private String url;
	
	/**
	 * @param stateName Name of the state used to identify the angular-ui state.
	 * @param url URL assigned to the state, with path parameters.
	 */
	State(String stateName, String url) {
		this.stateName = stateName;
		this.url = url;
	}
	
	/**
	 * @return Name assigned to the state.
	 */
	public String getStateName() {
		return stateName;
	}
	
	/**
	 * Determines if the configured URL contains path parameters that need to be 
	 * replaced in order to create a complete URL. 
	 * @return True if path parameters are present.
	 */
	public boolean hasPathParameters() {
		return url.contains(":");
	}
	
	/**
	 * @return URL assigned to the State.
	 * @throws IllegalArgumentException If {@link #hasPathParameters()} returns true.
	 */
	public String getUrl() {
		if (!hasPathParameters()) {
			return url;
		} else {
			throw new IllegalArgumentException(String.format("Path contains parameters.  Use State#getUrl(Map) instead.  Provided url [%s]", url));
		}
	}
	
	/**
	 * @param pathParameters Map of values to place in the URL.
	 * @return URL assigned to the State, with path parameters in the URL.
	 * @throws IllegalArgumentException If one of the path parameters was not found in the pathParameters map.
	 * @throws NullPointerException If pathParameters is null, and the URL has path parameters to replace.
	 */
	public String getUrl(Map<String, String> pathParameters) {
		String formattedUrl = url;
		if (hasPathParameters()) {
			do {
				Matcher matcher = PATH_PARAMETER.matcher(formattedUrl);
				if (!matcher.find()) {
					break;
				}
				String parameterName = matcher.group(1);
				String parameter = pathParameters.get(parameterName);
				if (parameter == null) {
					throw new IllegalArgumentException(String.format("Required path parmeter [%s] was not set in url [%s].", parameterName, url));
				}
				formattedUrl = formattedUrl.replaceAll(matcher.group(), '/'+parameter);
			} while (true);
			return formattedUrl;
		}
		return formattedUrl;
	}
}
