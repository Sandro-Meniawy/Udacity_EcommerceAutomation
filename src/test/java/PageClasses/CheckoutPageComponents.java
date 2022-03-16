package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageComponents {
    WebDriver checkoutDriver;
    WebDriverWait checkoutDriverWait;

    public CheckoutPageComponents(WebDriver inputDriver, WebDriverWait inputWait){
        checkoutDriver = inputDriver;
        checkoutDriverWait = inputWait;
        PageFactory.initElements(inputDriver,this);
    }


    @FindBy (id = "checkout")
    WebElement checkoutBtn;

    @FindBy (id = "termsofservice")
    WebElement termsOfServiceChkBox;

    @FindBy (xpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[1]/strong")
    WebElement orderCompletionMsg;

    @FindBy (id = "BillingNewAddress_FirstName")
    WebElement firstNameInputTextField;

    @FindBy (id = "BillingNewAddress_CountryId")
    WebElement countrySelectField;

    @FindBy (id = "BillingNewAddress_StateProvinceId")
    WebElement stateProvinceSelectField;

    @FindBy (id = "BillingNewAddress_City")
    WebElement cityInputTextField;

    @FindBy (id = "BillingNewAddress_Address1")
    WebElement address1InputTextField;

    @FindBy (id = "BillingNewAddress_ZipPostalCode")
    WebElement postalCodeInputTextField;

    @FindBy (id = "BillingNewAddress_PhoneNumber")
    WebElement phoneNumberInputTextField;

    @FindBy (xpath = "//*[@id=\"billing-buttons-container\"]/button[4]")
    WebElement firstContinueBtn;

    @FindBy (id="shippingoption_0")
    WebElement groundShippingOptionRadioBtn;

    @FindBy (xpath = "//*[@id=\"shipping-method-buttons-container\"]/button")
    WebElement secondContinueBtn;

    @FindBy (id = "paymentmethod_0")
    WebElement checkMoneyPaymentMethodOptionRadioBtn;

    @FindBy (xpath = "//*[@id=\"payment-method-buttons-container\"]/button")
    WebElement thirdContinueBtn;

    @FindBy (xpath = "//*[@id=\"payment-info-buttons-container\"]/button")
    WebElement fourthContinueBtn;

    @FindBy (xpath = "//*[@id=\"confirm-order-buttons-container\"]/button")
    WebElement confirmBtn;

    public void acceptTermsOfService(){
        termsOfServiceChkBox.click();
    }

    public void clickCheckOut(){
        checkoutBtn.click();
    }

    public String getOrderCompletionOutMsg(){
       return orderCompletionMsg.getText();
    }

    public void fillCheckoutForm() throws InterruptedException {
        waitForElement("Firstname_Field");
        Select countrySelector = new Select(countrySelectField);
        countrySelector.selectByValue("1");

        Thread.sleep(4000);
        Select stateProvinceSelector = new Select(stateProvinceSelectField);
        stateProvinceSelector.selectByValue("53");

        cityInputTextField.sendKeys("Test City");
        address1InputTextField.sendKeys("Test Address");
        postalCodeInputTextField.sendKeys("2231");
        phoneNumberInputTextField.sendKeys("01223435443");
        firstContinueBtn.click();

        waitForElement("Shipping_Option");
        groundShippingOptionRadioBtn.click();
        secondContinueBtn.click();

        waitForElement("Payment_Option");
        checkMoneyPaymentMethodOptionRadioBtn.click();
        thirdContinueBtn.click();

        waitForElement("Payment_Info_Continue_Button");
        fourthContinueBtn.click();

        waitForElement("Confirm_Button");
        confirmBtn.click();
    }

    public void waitForElement(String elementToWait){
        switch (elementToWait) {

        case "Checkout_Button":
            checkoutDriverWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
            break;

        case "Order_Completion_Msg":
            checkoutDriverWait.until(ExpectedConditions.visibilityOf(orderCompletionMsg));
            break;

        case "Firstname_Field":
            checkoutDriverWait.until(ExpectedConditions.visibilityOf(firstNameInputTextField));
            break;

            case "Shipping_Option":
                checkoutDriverWait.until(ExpectedConditions.elementToBeClickable(groundShippingOptionRadioBtn));
                break;

            case "Payment_Option":
                checkoutDriverWait.until(ExpectedConditions.elementToBeClickable(checkMoneyPaymentMethodOptionRadioBtn));
                break;

            case "Payment_Info_Continue_Button":
                checkoutDriverWait.until(ExpectedConditions.elementToBeClickable(fourthContinueBtn));
                break;

            case "Confirm_Button":
                checkoutDriverWait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
                break;

       default:
            break;
        }
    }
}
