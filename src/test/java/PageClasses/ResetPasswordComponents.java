package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResetPasswordComponents {
    WebDriver resPassDriver;
    WebDriverWait resPassDriverWait;

    public ResetPasswordComponents(WebDriver inputDriver, WebDriverWait inputWait){
        resPassDriver = inputDriver;
        resPassDriverWait = inputWait;
        PageFactory.initElements(inputDriver,this);
    }


    @FindBy (id = "Email")
    WebElement emailInputTextField;

    @FindBy (id = "Password")
    WebElement passwordInputTextField;


    @FindBy (name = "send-email")
    WebElement recoverButton;

    @FindBy (className = "content")
    WebElement outPutMsg;


    public void fillEmailField(String email){
        emailInputTextField.sendKeys(email);
    }

    public void clickOnRecoverButton(){
        recoverButton.click();
    }

    public String getOutputMsg(){
        return outPutMsg.getText();
    }

    public void waitForElement(String elementToWait){
        switch (elementToWait) {

       case "Forgot_Password_Page_Email_Field":
           resPassDriverWait.until(ExpectedConditions.visibilityOf(emailInputTextField));
            break;

       case "RecoverButton":
           resPassDriverWait.until(ExpectedConditions.elementToBeClickable(recoverButton));
            break;

       case "Forgot_Password_Output_Msg":
           resPassDriverWait.until(ExpectedConditions.visibilityOf(outPutMsg));
           break;

       default:
            break;
        }
    }
}
