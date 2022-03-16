package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class RegistrationPageComponents{
    WebDriver registrationDriver;
    WebDriverWait registrationDriverWait;

    public RegistrationPageComponents(WebDriver inputDriver, WebDriverWait inputWait){
        registrationDriver = inputDriver;
        registrationDriverWait = inputWait;
        PageFactory.initElements(inputDriver,this);
    }


    @FindBy (className = "ico-register")
    WebElement homePageRegisterLink;

    @FindBy (id = "FirstName")
    WebElement firstNameInputTextField;

    @FindBy (id = "LastName")
    WebElement lastNameInputTextField;

    @FindBy (id = "Email")
    WebElement emailInputTextField;

    @FindBy (id = "Password")
    WebElement passwordInputTextField;

    @FindBy (id = "ConfirmPassword")
    WebElement confirmPasswordInputTextField;

    @FindBy (id = "register-button")
    WebElement registerButton;

    @FindBy (className = "result")
    WebElement registrationOutputMsgDiv;

    @FindBy (className = "ico-logout")
    WebElement homePageLogoutLink;

    public void clickOnRegisterLink(){
        homePageRegisterLink.click();
    }

    public void fillRegistrationForm(String firstName,String lastName,String email,String password){
        firstNameInputTextField.sendKeys(firstName);
        lastNameInputTextField.sendKeys(lastName);
        emailInputTextField.sendKeys(email);
        passwordInputTextField.sendKeys(password);
        confirmPasswordInputTextField.sendKeys(password);
    }

    public void clickOnRegisterButton(){
        registerButton.click();
    }

    public String getRegistrationOutputMsg(){
        return registrationOutputMsgDiv.getText();
    }

    public void clickOnLogoutLink(){
        registrationDriverWait.until(ExpectedConditions.elementToBeClickable(homePageLogoutLink));
         homePageLogoutLink.click();
    }

    public void waitForElement(String elementToWait){
        switch (elementToWait) {

        case "Home_Page_Registration_Link":
            registrationDriverWait.until(ExpectedConditions.elementToBeClickable(homePageRegisterLink));
            break;

       case "Registration_Page_First_Name_Field":
            registrationDriverWait.until(ExpectedConditions.visibilityOf(firstNameInputTextField));
            break;

       case "RegisterButton":
            registrationDriverWait.until(ExpectedConditions.elementToBeClickable(registerButton));
            break;

       case "Registration_Output_Msg":
           registrationDriverWait.until(ExpectedConditions.visibilityOf(registrationOutputMsgDiv));
           break;

       default:
            break;
        }
    }
}
