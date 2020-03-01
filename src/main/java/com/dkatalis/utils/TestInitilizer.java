package com.dkatalis.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**gradle
 * @author Jeevan.Nikam
 *
 */

public class TestInitilizer extends ObjectRepository{
	
	public static WebDriver driver;
	protected static final Logger log = Logger.getLogger(TestInitilizer.class);
	public static String currentMethodName;
	public static WebDriverWait wait = null;
	public static final PropertiesCache cache = PropertiesCache.getInstance();
	public static String url = "";
	public static String ScreenshotsPath = cache.getProperty("Screenshots");
	public static final String CurrentUserDirectory = System.getProperty("user.dir");

	protected static String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + File.separator + "config.properties";

	@BeforeSuite
	public void beforeSetup() throws InterruptedException, IOException {

		url = cache.getProperty("url");
		log.info("here is the URL :: " + url);
		log.info("--------------------------------------------------");
		log.info("Executing on ST ......");
	}

	@BeforeTest
	public void Setup() throws InterruptedException, IOException {

		launchWebdriver();
		launchApplication();
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		String currectClassNameToBeExecuted = this.getClass().getSimpleName();
		log.info("class Name " + currectClassNameToBeExecuted);
		driver.get(url);
	}

	@AfterClass
	public void tearDown() {
		log.info("Tests Ended from class :::::" + this.getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethodCalled(Method method) throws InterruptedException {

		currentMethodName = method.getName();
		log.info("Testcase name is :::::: " + method.getName());
		log.info(method.getName() + " Started ::::");

	}

	@AfterMethod()
	public void afterMethodCalled(Method method) throws InterruptedException, IOException {

		log.info("Testcase execution completed :::::: " + method.getName());
	}

	@AfterSuite
	public void suiteEndReached() throws IOException, InterruptedException {

		log.info("Logger Info:: Inside suiteEndReached Method");
		log.info("Suite ended");
		driver.quit();
	}

	public static void sendKeys(String text, WebElement we, String objectName) throws InterruptedException {

		/*
		 * we.sendKeys(Keys.CONTROL, "a"); we.sendKeys(Keys.DELETE);
		 */

		we.clear();
		we.sendKeys(text);
	}

	public static void sendKeysFromKeyBoard(Keys key, WebElement we, String objectName) throws InterruptedException {
		we.sendKeys(key);
	}

	public static void setApplicationLoginPage() throws InterruptedException {

		try {
			driver.navigate().to(url);
		} catch (NoSuchElementException e) {
			log.info("Unable to move login page. trying to logout application");
		}
	}

	private void launchWebdriver() {

		try {

			if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {

				System.setProperty("webdriver.chrome.driver", cache.getProperty("driverPath") + "chromedriver.exe");

			}

			else if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
				System.setProperty("webdriver.chrome.driver", cache.getProperty("driverPath") + "chromedriver_MAC");
			}
			
			else if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
				System.setProperty("webdriver.chrome.driver", cache.getProperty("driverPath") + "chromedriver");
			}

			log.info(" =================================================== ");
			log.info("Url is :: " + url);
			log.info(" =================================================== ");
			
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		} catch (WebDriverException e) {
			driver.close();
		}
	}

	public static void failTestCase(String reason) throws InterruptedException {
		Assert.fail(reason);
	}

	public void passTestCase(String reason) throws InterruptedException {

	}

	/**
	 * Method to check is element displayed
	 * 
	 * @param we
	 * @param webElementName
	 * @throws InterruptedException
	 */
	public void isDisplayed(WebElement we, String webElementName) throws InterruptedException {

		try {
			if (we.isDisplayed()) {

			} else {

				failTestCase(webElementName + " exist on webpage but it is not visible on webpage");

			}
		} catch (NoSuchElementException e) {
			failTestCase(webElementName + " is not found on webpage");

		}

	}

	/**
	 * Method to check is element displayed
	 * 
	 * @param we
	 * @param webElementName
	 * @throws InterruptedException
	 */
	public void isNotDisplayed(WebElement we, String webElementName) throws InterruptedException {

		try {
			if (!we.isDisplayed()) {

			} else {
				failTestCase(webElementName + "is visible on webpage");

			}
		} catch (NoSuchElementException e) {
			failTestCase(webElementName + " is not found on webpage");

		}

	}

	/**
	 * Method to launch application
	 * 
	 * @throws InterruptedException
	 */
	private void launchApplication() throws InterruptedException {

		System.out.println("Waiting for the page to load");
		wait = new WebDriverWait(driver, 120L);
	}


}
