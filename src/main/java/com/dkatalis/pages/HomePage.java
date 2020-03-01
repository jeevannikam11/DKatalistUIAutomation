package com.dkatalis.pages;

import com.dkatalis.utils.TestInitilizer;
import com.dkatalis.utils.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestInitilizer {

    static WebDriver driver;

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = HomeScreen.buyNowButton_Xpath)
    public WebElement buyNowButton;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.pillowAmount_XPath)
    public WebElement pillowAmount;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerName_Xpath)
    public WebElement customerName;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerEmail_Xpath)
    public WebElement customerEmail;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerPhoneNo_Xpath)
    public WebElement customerPhoneNo;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerCity_Xpath)
    public WebElement customerCity;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerAddress_Xpath)
    public WebElement customerAddress;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.customerPostalCode_Xpath)
    public WebElement customerPostalCode;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.shoppingCartCheckoutButton_Xpath)
    public WebElement shoppingCartCheckoutButton;

    @FindBy(how = How.XPATH, using = HomeScreen.ShoppingCartScreen.shoppingCartCancelButton_Xpath)
    public WebElement shoppingCartCancelButton;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.amount_XPath)
    public WebElement amount;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.orderDetils_Xpath)
    public WebElement orderDetils;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.continueButton_Xpath)
    public WebElement continueButton;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.creditCard_Xpath)
    public WebElement creditCard;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.cardNumber_Xpath)
    public WebElement cardNumber;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.expiryDate_Xpath)
    public WebElement expiryDate;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.cvv_Xpath)
    public WebElement cvv;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.payNow_Xpath)
    public WebElement payNow;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.otp_Xpath)
    public WebElement otp;

    @FindBy(how = How.XPATH, using = HomeScreen.SampleStoreScreen.okButton_Xpath)
    public WebElement okButton;

    public void clickOnBuyNowButton() {

        try {
            buyNowButton.click();
            log.info("Clicked on Buy now button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on Buy now button : {}", e);
        }
    }

    public void clickOnCheckoutButton() {

        try {
            shoppingCartCheckoutButton.click();
            log.info("Clicked on checkout button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on checkout button : {}", e);
        }
    }

    public void clickOnCancelButton() {

        try {
            shoppingCartCancelButton.click();
            log.info("Clicked on cancel button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on cancel button : {}", e);
        }
    }

    public void enterPillowAmount(String amount) {

        try {
            sendKeys(amount, pillowAmount, "amount");
            log.info("Entered city : " + amount );
        } catch (Exception e) {
            log.error("Exception occured in enterPillowAmount method : {}", e);
        }
    }


    public void enterName(String name) {

        try {
            sendKeys(name, customerName, "Name");
            log.info("Entered customer Name : " + name );
        } catch (Exception e) {
            log.error("Exception occured in enterName method : {}", e);
        }
    }

    public void enterEmail(String email) {

        try {
            sendKeys(email, customerEmail, "Email");
            log.info("Entered customer Name : " + email );
        } catch (Exception e) {
            log.error("Exception occured in enterEmail method : {}", e);
        }
    }

    public void enterPhoneNo(String phoneNumber) {

        try {
            sendKeys(phoneNumber, customerPhoneNo, "Email");
            log.info("Entered customer Phone no : " + phoneNumber );
        } catch (Exception e) {
            log.error("Exception occured in enterPhoneNo method : {}", e);
        }
    }

    public void enterCity(String city) {

        try {
            sendKeys(city, customerCity, "City");
            log.info("Entered customer City : " + city );
        } catch (Exception e) {
            log.error("Exception occured in enterCity method : {}", e);
        }
    }

    public void enterAddress(String address) {

        try {
            sendKeys(address, customerAddress, "Address");
            log.info("Entered customer Address : " + address );
        } catch (Exception e) {
            log.error("Exception occured in enterAddress method : {}", e);
        }
    }

    public void enterPostalCode(String postalCode) {

        try {
            sendKeys(postalCode, customerPostalCode, "Postal Code");
            log.info("Entered customer Postal code : " + postalCode );
        } catch (Exception e) {
            log.error("Exception occured in enterPostalCode method : {}", e);
        }
    }

    public void enterCardNumber(String ccNumber) {

        try {
            sendKeys(ccNumber, cardNumber, "Card Number");
            log.info("Entered Card Number : " + ccNumber);
        } catch (Exception e){
            log.error("Exception occured in enterCardNumber method : {}", e);
        }
    }

    public void enterExpiryDate(String expiry) {

        try {
            sendKeys(expiry, expiryDate, "Expiry Date");
            log.info("Entered Card Number : " + expiry);
        } catch (Exception e){
            log.error("Exception occured in enterExpiryDate method : {}", e);
        }
    }

    public void enterCvvNumber(String cvvNumber) {

        try {
            sendKeys(cvvNumber, cvv, "CVV");
            log.info("Entered Card Number : " + cvvNumber);
        } catch (Exception e){
            log.error("Exception occured in enterCvvNumber method : {}", e);
        }
    }

    public void enterOTP(String otpPassword) {

        try {
            sendKeys(otpPassword, otp, "Password");
            log.info("Entered Card Number : " + otpPassword);
        } catch (Exception e){
            log.error("Exception occured in enterOTP method : {}", e);
        }
    }

    public void clickOnContinueButton() {

        try {
            continueButton.click();
            log.info("Clicked on cancel button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on Continue button : {}", e);
        }
    }

    public void clickOnPayNowButton() {

        try {
            payNow.click();
            log.info("Clicked on Pay now button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on Pay Now button : {}", e);
        }
    }

    public void clickOnCreditCardLink() {

        try {
            creditCard.click();
            log.info("Clicked on Credit card link");
        } catch (Exception e) {
            log.error("Exception occured while clicking on Credit card link : {}", e);
        }
    }

    public void clickOnOkButton() {

        try {
            okButton.click();
            log.info("Clicked on OK button");
        } catch (Exception e) {
            log.error("Exception occured while clicking on OK button : {}", e);
        }
    }
}
