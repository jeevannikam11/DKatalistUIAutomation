package com.dkatalis.tests;

import com.dkatalis.pages.HomePage;
import com.dkatalis.utils.TestInitilizer;
import com.dkatalis.utils.TestUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FailedPaymentTest extends TestInitilizer {

    @Test
    public void testBuyNowButtonFunctionality() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnBuyNowButton();
        homePage.enterPillowAmount("200");
        homePage.enterName("Jeevan");
        homePage.enterEmail("jeevannikam1991@gmail.com");
        homePage.enterPhoneNo("8149034452");
        homePage.enterCity("Pune");
        homePage.enterAddress("Pashan");
        homePage.enterPostalCode("411008");
        homePage.clickOnCheckoutButton();
        Thread.sleep(2000);
        TestUtil.switchFrame("snap-midtrans");
        homePage.clickOnContinueButton();
        homePage.clickOnCreditCardLink();
        homePage.enterCardNumber("4911111111111113");
        homePage.enterExpiryDate("03/20");
        homePage.enterCvvNumber("123");
        homePage.clickOnPayNowButton();
        Thread.sleep(10000);
        //TestUtil.switchFrame("0");
        driver.switchTo().frame(0);
        homePage.enterOTP("112233");
        homePage.clickOnOkButton();
        Thread.sleep(2000);
        String str = "";
        str = homePage.getMessage();
        Assert.assertNotEquals(str, "Transaction successful");
        log.info("Test sucessful");
    }
}
