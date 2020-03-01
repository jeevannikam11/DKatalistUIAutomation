package com.dkatalis.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dkatalis.utils.PropertiesCache;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;

import java.io.File;

/**
 * @author Jeevan.Nikam
 * This class is for managing extent report properties and UI
 */
public class ExtentManager {

	public static final PropertiesCache cache = PropertiesCache.getInstance();
	private static ExtentReports extent;
	private static Platform platform;
	private static String reportFileName = cache.getProperty("ExtentReportName")+".html";
	private static String macPath = System.getProperty("user.dir") + "/TestReport";
	private static String windowsPath = System.getProperty("user.dir") + "\\TestReport";
	private static String linuxPath = System.getProperty("user.dir") + "/TestReport";
	private static String macReportFileLoc = macPath + "/" + reportFileName;
	private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
	private static String linuxReportFileLoc = linuxPath + "/" + reportFileName;
	private static final Logger log = Logger.getLogger(ExtentManager.class);
	
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		
		htmlReporter.config().setAutoCreateRelativePathMedia(true);
		htmlReporter.config().setLevel();
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		//htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
		//htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setReportName(cache.getProperty("ExtentReportName"));
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", cache.getProperty("HostName"));
		extent.setSystemInfo("Environment",cache.getProperty("Environment"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("BuildVersion", cache.getProperty("BuildVersion"));
		extent.setSystemInfo("Platform",getCurrentPlatform().toString());
		return extent;
	}

	// Select the extent report file location based on platform
	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			log.info("ExtentReport Path for MAC: " + macPath + "\n");
			break;
		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			log.info("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		case LINUX:
			reportFileLocation = linuxReportFileLoc;
			createReportPath(linuxPath);
			log.info("ExtentReport Path for LINUX: " + linuxPath + "\n");
			break;
		default:
			log.info("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}

	// Create the report path if it does not exist
	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				log.info("Directory: " + path + " is created!");
			} else {
				log.info("Failed to create directory: " + path);
			}
		} else {
			log.info("Directory already exists: " + path);
		}
	}

	// Get current platform
	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}
}
