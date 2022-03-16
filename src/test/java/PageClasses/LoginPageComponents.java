package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class LoginPageComponents{
    WebDriver loginDriver;
    WebDriverWait loginDriverWait;

    public LoginPageComponents(WebDriver inputDriver, WebDriverWait inputWait){
        loginDriver = inputDriver;
        loginDriverWait = inputWait;
        PageFactory.initElements(inputDriver,this);
    }


    @FindBy (className = "ico-login")
    WebElement homePageLoginLink;

    @FindBy (id = "Email")
    WebElement emailInputTextField;

    @FindBy (id = "Password")
    WebElement passwordInputTextField;


    @FindBy (xpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button")
    WebElement loginButton;

    @FindBy (className = "ico-account")
    WebElement homePageMyAccountLink;

    @FindBy (className = "forgot-password")
    WebElement forgotPasswordLink;

    public void clickOnForgotPasswordLink(){
    forgotPasswordLink.click();
    }

    public void clickOnLoginLink(){
        homePageLoginLink.click();
    }

    public void fillLoginInputs(String email,String password){
        emailInputTextField.sendKeys(email);
        passwordInputTextField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public boolean isMyAccountLinkVisible(){
        return homePageMyAccountLink.isDisplayed();
    }

    public void waitForElement(String elementToWait){
        switch (elementToWait) {

        case "Home_Page_Login_Link":
            loginDriverWait.until(ExpectedConditions.elementToBeClickable(homePageLoginLink));
            break;

       case "Login_Page_Email_Field":
            loginDriverWait.until(ExpectedConditions.visibilityOf(emailInputTextField));
            break;

       case "LoginButton":
            loginDriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
            break;

       case "My_Account_Link":
           loginDriverWait.until(ExpectedConditions.visibilityOf(homePageMyAccountLink));
           break;

       case "LoginPageComponents":
           loginDriverWait.until(ExpectedConditions.visibilityOf(forgotPasswordLink));
           break;

       default:
            break;
        }
    }
}
