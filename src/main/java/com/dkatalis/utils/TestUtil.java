package com.dkatalis.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeevan.Nikam
 *
 */
public class TestUtil extends TestInitilizer {

	/**
	 * Method to check is element is displayed
	 * @param we
	 * @param waitingTimeInSec
	 * @return
	 */
	public static boolean isElementDisplayed(WebElement we, int waitingTimeInSec) {

		boolean isVisible;
		driver.manage().timeouts().implicitlyWait(waitingTimeInSec, TimeUnit.SECONDS);
		try {
			if (we.isDisplayed()) {
				isVisible = true;
			} else {
				isVisible = false;
			}
		} catch (NoSuchElementException e) {
			isVisible = false;
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return isVisible;
	}
	
	/**
	 * Method to check is element is enabled
	 * @param we
	 * @param waitingTimeInSec
	 * @return
	 */
	public static boolean isElementEnabled(WebElement we, int waitingTimeInSec) {

		boolean isEnabled;
		driver.manage().timeouts().implicitlyWait(waitingTimeInSec, TimeUnit.SECONDS);
		try {
			if (we.isEnabled()) {
				isEnabled = true;
			} else {
				isEnabled = false;
			}
		} catch (NoSuchElementException e) {
			isEnabled = false;
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return isEnabled;
	}

	/**
	 * Method to check if Element is exist
	 * @param we
	 * @param elementName
	 * @return
	 */
	public static boolean isElementExist(WebElement we, String elementName) {
		// Throws NoSuchElementException in case of element is not visible on webpage.
		boolean isVisible;
		try {
			if (we.isDisplayed()) {
				isVisible = true;
			} else {
				isVisible = false;
			}
		} catch (NoSuchElementException e) {
			isVisible = false;
		}
		return isVisible;
	}
	
	/**
	 * Method to explicit wait
	 * @param we
	 * @param elementName
	 */
/*	public static void explicitWait(WebElement we, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(we));
		log.info("Element is displayed " + elementName);
	}*/

	/**
	 * Method to validate error message
	 * @param we
	 * @param msg
	 * @return
	 */
	public static boolean validateErrorMessage(WebElement we, String msg) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestInitilizer.log.info(" ");
		if (we.getText().trim().contentEquals(msg)) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			return true;

		}
		return false;
	}

	/**
	 * @param we
	 * @param attrubuteName
	 * @param expectedAttrValue
	 * @return false
	 */
	public static boolean validateAttribute(WebElement we, String attrubuteName, String expectedAttrValue) {

		String actualAttributeVal = we.getAttribute(attrubuteName);
		TestInitilizer.log.info("Validate an attribute name " + attrubuteName + " expected value " + expectedAttrValue
				+ " Actual attribute value " + actualAttributeVal);
		if (actualAttributeVal.contentEquals(expectedAttrValue)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to send key from keyboard
	 * @param key
	 * @param we
	 */
	public static void sendKeyFromKeyboard(Keys key, WebElement we) {

		TestInitilizer.log.info("Sending Key from keyboard " + key);
		we.sendKeys(key);
	}

	/**
	 * Method to clear
	 * @param we
	 */
	public static void clear(WebElement we) {
		TestInitilizer.log.info("Send CTRL+A and DELETE on element");
		we.sendKeys(Keys.CONTROL, "a");
		we.sendKeys(Keys.DELETE);
	}

	
	/**
	 * Method to capture current screenshots
	 * @throws InterruptedException
	 */
	public static void captureCurrentScreenshot() throws InterruptedException {

		Thread.sleep(2000); 
		Calendar cald = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String captureFileName = currentMethodName + formatter.format(cald.getTime()).toString() + ".jpg";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(ScreenshotsPath+captureFileName));
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot");
			log.info("Exception while taking screenshot");

			e.printStackTrace();
		}
	}
	 

	/**
	 * Method to get userData from local
	 * @return userData
	 */
	public static String getUserDataFromLocalStorage() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String userData = (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", "user"));
		log.info("Fetch user data from localstorage variable " + userData);
		return userData;
	}

	

	/**
	 * Method to refresh application
	 * @throws InterruptedException
	 */
	public static void refreshApplication() throws InterruptedException {

		String url = cache.getProperty("URL");
		driver.navigate().to(url);
	}

	/**
	 * Method to open application
	 * @throws InterruptedException
	 */
	public static void navigateToApplicationURL() throws InterruptedException {
		String url = cache.getProperty("URL");
		driver.navigate().to(url);
	}

	/**
	 * Method to Generate Random Email Id
	 * @return
	 */
	public static String getRandomEmail() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr + "@gmail.com";
	}
	
	/**
	 * Method to get random number
	 * @param aStart
	 * @param aEnd
	 * @return randomNumber
	 */
	public static int getRandomInteger(int aStart, int aEnd) {
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}
	
	/**
	 * Method to get todays date
	 * @return
	 */
	public static String todaysDate() {
		Date date = new Date();  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		return str;
	}
	
	/**
	 * Method to wait for sometime
	 * @param sec
	 * @throws InterruptedException
	 */
	public static void waitForSometime(double sec) throws InterruptedException {
		int time = (int) (sec*1000);
		Thread.sleep(time);
	}
	
	/**
	 * Method to scroll down on page
	 */
	public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	/**
	 *  Method to find last day of previous month
	 * @return lastDay
	 */
	public static int lastDayOfPreviousMonth() {
		final SimpleDateFormat format = new SimpleDateFormat("dd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);

		Date preMonthDate = cal.getTime();
		int lastDay = Integer.parseInt(format.format(preMonthDate));
		log.info("Last day of previous month :: " + lastDay);
		return lastDay;
	}
	
	/**
	 * Method to find date earlier than today
	 * @param previousDays
	 * @return day
	 */
	public static int findDayErlierToday(int previousDays) {
		final SimpleDateFormat format = new SimpleDateFormat("dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -previousDays);
		int day = Integer.parseInt(format.format(cal.getTime()));
		log.info(previousDays + " days back date is :: " + day);
		return day;
	}
	
	/**
	 * Method to find date earlier than today
	 * @param previousDays
	 * @return
	 */
	public static String getPrviousDate(int previousDays) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -previousDays);
		String date = format.format(cal.getTime());
		return date;
	}
	
	/**
	 * Method to explicit wait for element to be clickable
	 * @param we
	 * @param elementName
	 */
/*	public static void waitForElementToBeClickable(WebElement we, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(we));
		log.info("Element is displayed " + elementName);
	}*/
	
	public static void switchFrame(String frameNameOrId) {
		
		try {
			driver.switchTo().frame(frameNameOrId);
			log.info("Switched to frame : " + frameNameOrId);
		}catch (Exception e) {
			log.info("Exception occured while switching frame.");
		}
	}
}
