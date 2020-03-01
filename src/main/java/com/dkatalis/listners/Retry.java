package com.dkatalis.listners;

import com.dkatalis.utils.PropertiesCache;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jeevan.Nikam
 *
 */
public class Retry implements IRetryAnalyzer {

	private static final Logger log = Logger.getLogger(Retry.class);
	public static final PropertiesCache cache = PropertiesCache.getInstance();
	public static final int maxTry = Integer.parseInt(cache.getProperty("MaxRetryCount"));

	AtomicInteger count = new AtomicInteger(maxTry);

	public boolean isRetryAvailable() {
		return (count.intValue() > 0);
	}

	@Override
	public boolean retry(ITestResult iTestResult) {
		boolean retry = false;
		if (isRetryAvailable()) {
			log.info("Going to retry test case: " + iTestResult.getMethod() + ", " + (maxTry - count.intValue() + 1)
					+ " out of " + maxTry);
			retry = true;
			count.decrementAndGet();
		}
		return retry;
	}
}
