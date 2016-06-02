package com.catalystdevworks.mtidwell.timeclock.selenium;

import static org.junit.Assert.fail;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <p>Extend this class to create a StateModel for your state.</p>
 * 
 * <ol>
 * 		<li>Be sure to call {@link BaseStateModel#init()} during the {@link Before} of the junit test.</p>
 * 		<li>Be sure to call {@link BaseStateModel#shutdown()} during the {@link After} of the junit test.</p>
 * 		<li>Implement {@link BaseStateModel#findStateWebElements(State)} to select elements that are available once the
 *          angular-ui <code>$viewContentLoaded</code> event is fired</li>
 * </ol>
 */
public abstract class BaseStateModel {
	/**
	 * Name of the log4j logger to distinguish logs coming from the browser console.
	 */
	private static final String BROWSER_LOGGER_NAME = "BROWSER";
	/**
	 * Timestamp formatter for the logs coming from the browser console.
	 */
	private static final DateTimeFormatter BROWSER_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private static Logger logger = Logger.getLogger(BaseStateModel.class);
	private static Logger browserLogger = Logger.getLogger(BROWSER_LOGGER_NAME);
	
	/**
	 * <p>Script to interact with the selenim present in the angular application that will 
	 * execute a callback when {@link JavascriptExecutor#executeAsyncScript(String, Object...)} is called.</p>
	 * 
	 * <p>{@value}</p>
	 */
	private static final String WAIT_FOR_ANGULAR =
			"var selenium = window.selenium = window.selenium || {},"+
				"callback = arguments[arguments.length-1];"+
			"if (selenium.ready) {"+
				"callback();"+
			"} else {"+
				"if (!(selenium.waits instanceof Array)) {"+
					"selenium.waits = [];"+
				"}"+
				"selenium.waits.push(callback);"+
			"}";
	
	/**
	 * Converstion tool to be used to convert from a selenium {@link LogEntry#getLevel()} to 
	 * be passed to a {@link Logger#log(Priority, Object)}.
	 * 
	 * @param level JUL level from {@link LogEntry#getLevel()}
	 * @return {@link org.apache.log4j.Level} of appropriate level.
	 * @see <a href="https://logging.apache.org/log4j/log4j-2.4/log4j-jul/index.html">JUL to Log4J Conversions</a>
	 */
	public static Priority convert(Level level) {
		switch(level.getName()) {
			case "OFF":
				return org.apache.log4j.Level.OFF;
			case "SEVERE":
				return org.apache.log4j.Level.ERROR;
			case "WARNING":
				return org.apache.log4j.Level.WARN;
			default:
			case "INFO":
				return org.apache.log4j.Level.INFO;
			case "CONFIG":
			case "FINE":
				return org.apache.log4j.Level.DEBUG;
			case "FINER":
			case "FINEST":
				return org.apache.log4j.Level.TRACE;
			case "ALL":
				return org.apache.log4j.Level.ALL;
		}
	}
	
	/**
	 * Base url to test against.  This is expected to be configured as the system property <code>selenium.baseUrl</code>.
	 */
	private static String BASE_URL;
	static {
		BASE_URL = System.getProperty("selenium.baseUrl");
		logger.debug(String.format("Recieved baseurl [%s].", BASE_URL));
		if (StringUtils.isEmpty(BASE_URL)) {
			throw new IllegalArgumentException("Must provide system property 'selenium.baseUrl'.");
		}
	}
	
	protected WebDriver webDriver;
	
	private WebElement navHomeLink;
	private WebElement navUserLink;
	
	/**
	 * Call in a {@link Before} method during a test to initialize the browser based 
	 * on system configurations.
	 */
	public void init() {
		logger.debug("Start web driver.");
		
		// TODO : logic to determine web driver to use
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		
		LoggingPreferences loggingPreferences = new LoggingPreferences();
		loggingPreferences.enable(LogType.BROWSER, Level.ALL);
		
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		
		webDriver = new ChromeDriver(capabilities);
		
		webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.MINUTES);
		
		webDriver.manage().window().maximize();
	}
	
	/**
	 * Call in a {@link After} to shutdown the webDriver upon test completion.
	 */
	public void shutdown() {
		if (webDriver != null) {
			logger.debug("Shut down web driver.");
			webDriver.quit();
		}
	}
	
	/**
	 * <p>Call to add all browser logs to the log output.</p>
	 * <p>Currently will only log browser info if {@link Logger#isDebugEnabled()} is <code>true</code></p>
	 */
	public void logBrowserConsole() {
		logger.debug("Dumping Browser Logs: ");
		for (LogEntry logEntry: webDriver.manage().logs().get(LogType.BROWSER)) {
			browserLogger.log(
				convert(logEntry.getLevel()),
				String.format(
					"%s - %s", 
					BROWSER_TIMESTAMP_FORMATTER.format(
						ZonedDateTime.ofInstant(
							Instant.ofEpochMilli(logEntry.getTimestamp()),
							ZoneId.systemDefault()
						)
					),
					logEntry.getMessage()
				)
			);
		}
	}
	
	/**
	 * <p><strong>WARNING</strong> an exception will be thrown if the {@link State} is 
	 * expecting path parameters and none were provided.  Use {@link BaseStateModel#navigateTo(State, Map)} to pass 
	 * path parameters.</p>
	 * @param state State to navigate to.
	 * @see BaseStateModel#navigateTo(State, Map)
	 */
	public void navigateTo(State state) {
		navigateTo(state, null);
	}
	
	/**
	 * <p>Will navigate the defined state.</p>
	 * <p>Once the Angular announces it has completed the page load, then {@link #findWebElements(State)} will be 
	 * called.</p>
	 * @param state State to navigate to.
	 * @param pathParameters Parameters to replace in the defined path. Or <code>null</code> if no path parameters are needed.
	 * @see #findWebElements(State)
	 */
	public void navigateTo(State state, Map<String, String> pathParameters) {
		webDriver.get(getStateUrl(state, pathParameters));

		logger.debug("Wait for Angular");
		((JavascriptExecutor) webDriver).executeAsyncScript(WAIT_FOR_ANGULAR);
		logger.debug("Angular Ready");
		
		try {
			findWebElements(state);
		} catch (NoSuchElementException noElement) {
			logger.error("Could not find element.", noElement);
			fail("Could not select all required elements after page load.  See logs for more info.");
		}
	}
	
	/**
	 * <p>This method is meant to select each element that is available on the page once the 
	 * angular view is complete.</p>
	 * <p>The intention of this method is to select all elements that are common between states.
	 * Individual states models should override {@link #findStateWebElements(State)} to select elements 
	 * that only appear during that state.</p>
	 * @param state State currently navigated to.
	 * @see #findStateWebElements(State)
	 */
	protected void findWebElements(State state) {
		navHomeLink = webDriver.findElement(By.cssSelector("nav a[ui-sref=home]"));
		navUserLink = webDriver.findElement(By.cssSelector("nav a[ui-sref=user]"));
		
		findStateWebElements(state);
	}
	
	/**
	 * Extenders of this class are expected to select their elements in this method, which will 
	 * guarantee the availability of all elements once the angular-ui <code>$viewContentLoaded</code> event is fired.
	 * @param state State currently navigated to.
	 */
	protected abstract void findStateWebElements(State state);
	

	/**
	 * <p><strong>WARNING</strong> an exception will be thrown if the {@link State} is 
	 * expecting path parameters and none were provided.  Use {@link #getStateUrl(State, Map)} to pass 
	 * path parameters.</p>
	 * @param state State to get the URL for.
	 * @return Absolute URL for the state.
	 * @see #getStateUrl(State, Map)
	 */
	public String getStateUrl(State state) {
		return getStateUrl(state, null);
	}
	
	/**
	 * Will construct a URL for the supplied state and path parameters
	 * @param state State to get the URL for.
	 * @param pathParameters Parameters to replace in the defined path. Or <code>null</code> if no path parameters are needed.
	 * @return Absolute URL for the state.
	 */
	public String getStateUrl(State state, Map<String, String> pathParameters) {
		return new StringBuilder(BASE_URL)
				.append('#')
				.append(state.getUrl(pathParameters))
				.toString();
	}
	
	/* ***************************************
	 * WEB ELEMENT GETTERS
	 * ***************************************/
	
	public WebElement getNavHomeLink() {
		return navHomeLink;
	}

	public WebElement getNavUserLink() {
		return navUserLink;
	}
}
