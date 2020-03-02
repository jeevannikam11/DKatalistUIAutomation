package com.dkatalis.utils;

/**
 * @author Jeevan.Nikam
 */
public class ObjectRepository {

    protected static class HomeScreen {

        public static final String buyNowButton_Xpath = "//a[@class='btn buy']";

        public static class ShoppingCartScreen {
            public static final String pillowAmount_XPath = "//input[@class='text-right']";
            public static final String customerName_Xpath = "//tr[1]//td[2]//input[1]";
            public static final String customerEmail_Xpath = "//tr[2]//td[2]//input[1]";
            public static final String customerPhoneNo_Xpath = "//tr[3]//td[2]//input[1]";
            public static final String customerCity_Xpath = "//tr[4]//td[2]//input[1]";
			public static final String customerAddress_Xpath = "//textarea[contains(text(),'MidPlaza 2, 4th Floor Jl.Jend.Sudirman Kav.10-11')]";
			public static final String customerPostalCode_Xpath = "//tr[6]//td[2]//input[1]";
            public static final String shoppingCartCheckoutButton_Xpath = "//div[@class='cart-checkout']";
            public static final String shoppingCartCancelButton_Xpath = "//div[@class='cancel-btn']";
        }

        public static class SampleStoreScreen {

            public static final String amount_XPath = "//*[@id=\"application\"]/div[3]/div/div/div/div[1]/div[1]/div[2]/span[2]";
            public static final String orderDetils_Xpath = "//*[@id=\"application\"]/div[3]/div/div/div/div[2]/div/ul/li[1]/a/span";
            public static final String continueButton_Xpath = "//*[@id=\"application\"]/div[1]/a";
            public static final String creditCard_Xpath = "//*[@id=\"payment-list\"]/div[2]/a/div[2]";

            public static final String cardNumber_Xpath = "//*[@name='cardnumber']";
            public static final String expiryDate_Xpath = "//*[@class='input-group col-xs-7']/child::input";
            public static final String cvv_Xpath = "//*[@class='input-group col-xs-5']/child::input";
            public static final String payNow_Xpath = "//*[@class='button-main show']/child::a";

            public static final String otp_Xpath = "//*[@id='PaRes']";

            public static final String okButton_Xpath = "//*[@name='ok']";

            public static final String message_Xpath = "//*[@id=’app’]/following-sibling::i[@class='main-container']/child::div[last()-1]";

        }
    }
}
