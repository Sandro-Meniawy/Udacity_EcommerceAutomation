package EcommerceStepDefinitions;

import PageClasses.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WebDriverMethods;

import java.util.Locale;
import java.util.Random;

public class EcommerceStepsDefinitions {
    WebDriverMethods webDriverMethods = new WebDriverMethods();
    WebDriver automationStepsWebDriver;
    WebDriverWait automationStepsWait;
    LoginPageComponents loginPage;
    RegistrationPageComponents regPage;
    ResetPasswordComponents forgotPasswordPage;
    ProductsPageComponents productsPage;
    CheckoutPageComponents checkoutPage;
    Random randomObj;
    int randomInt;
    String currentCurrency;
    String [] productCatAndSub;
    String selectedTag;

    @BeforeAll
    public static void setProperties() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\geckodriver.exe");
    }

    @Before
    public void initiateBrowserToUrl(){
        automationStepsWebDriver = webDriverMethods.openWebDriver("Chrome");
        automationStepsWait = new WebDriverWait(automationStepsWebDriver,20);
        automationStepsWebDriver.manage().window().maximize();
        regPage = new RegistrationPageComponents(automationStepsWebDriver,automationStepsWait);
        loginPage = new LoginPageComponents(automationStepsWebDriver,automationStepsWait);
        forgotPasswordPage = new ResetPasswordComponents(automationStepsWebDriver,automationStepsWait);
        productsPage = new ProductsPageComponents(automationStepsWebDriver,automationStepsWait);
        checkoutPage = new CheckoutPageComponents(automationStepsWebDriver,automationStepsWait);
        automationStepsWebDriver.navigate().to("https://demo.nopcommerce.com");
    }

    @After
    public void closeTheWebDriver() throws InterruptedException {
    webDriverMethods.closeWebDriver();
    }

    @When("user clicks on register link")
    public void clickHomePageRegisterLink(){
        regPage.waitForElement("Home_Page_Registration_Link");
        regPage.clickOnRegisterLink();
    }

    @And("user fills all required data fields")
    public void fillAllRequiredRegistrationData(){
        regPage.waitForElement("Registration_Page_First_Name_Field");
        randomObj = new Random();
        randomInt = randomObj.nextInt(1,1000);
        regPage.fillRegistrationForm("Test User_"+randomInt,"For Automation","testuser"+randomInt+"@automationtest.com","Test@123");
    }

    @And("user clicks on Register button")
    public void clickRegisterButton(){
        regPage.waitForElement("RegisterButton");
        regPage.clickOnRegisterButton();
    }

    @Then("A successful registration message should be displayed")
    public void validateRegistrationOutputMsg(){
        regPage.waitForElement("Registration_Output_Msg");
        Assert.assertTrue("The registration out Msg is different ("+regPage.getRegistrationOutputMsg()+")",regPage.getRegistrationOutputMsg().equals("Your registration completed"));
    }

    @Given("user should register first")
    public void registerNewAccount(){
        randomObj = new Random();
        randomInt = randomObj.nextInt(1,1000);
        regPage.waitForElement("Home_Page_Registration_Link");
        regPage.clickOnRegisterLink();
        regPage.waitForElement("Registration_Page_First_Name_Field");
        regPage.fillRegistrationForm("Test","User","testuser"+randomInt+"@automation.com","Pass@123");
        regPage.waitForElement("RegisterButton");
        regPage.clickOnRegisterButton();
        regPage.clickOnLogoutLink();
    }

    @When("user clicks on login link")
    public void clickHomePageLoginLink(){
        loginPage.waitForElement("Home_Page_Login_Link");
        loginPage.clickOnLoginLink();
    }

    @And("user clicks on forgot password link")
    public void clickForgotPasswordLink(){
        loginPage.waitForElement("Forgot_Password_Link");
        loginPage.clickOnForgotPasswordLink();
    }

    @And("user enters email address")
    public void enterForgotPasswordEmailInput(){
        forgotPasswordPage.waitForElement("Forgot_Password_Page_Email_Field");
        forgotPasswordPage.fillEmailField("testuser"+randomInt+"@automation.com");
    }

    @And("user clicks on Recover button")
    public void clickRecoverButton(){
        forgotPasswordPage.waitForElement("RecoverButton");
        forgotPasswordPage.clickOnRecoverButton();
    }

    @And("user enters valid username and password")
    public void fillLoginData(){
        loginPage.waitForElement("Login_Page_Email_Field");
        loginPage.fillLoginInputs("testuser"+randomInt+"@automation.com","Pass@123");
    }

    @And("user clicks on Login button")
    public void clickLoginButton(){
        loginPage.waitForElement("LoginButton");
        loginPage.clickOnLoginButton();
    }

    @Then("User should be logged in successfully")
    public void validateThatUserLoggedIn(){
        loginPage.waitForElement("My_Account_Link");
        Assert.assertTrue("User Is not able to login",loginPage.isMyAccountLinkVisible());
    }

    @Then("A successful reset password message should be displayed")
    public void validateForgotPasswordOutputMsg(){
        forgotPasswordPage.waitForElement("Forgot_Password_Output_Msg");
        Assert.assertTrue("The forgot password out Msg is different ("+forgotPasswordPage.getOutputMsg()+")",forgotPasswordPage.getOutputMsg().equals("Email with instructions has been sent to you."));
    }

    @Given("user should be registered and logged in")
    public void regAndLogin(){
        registerNewAccount();
        clickHomePageLoginLink();
        fillLoginData();
        clickLoginButton();
    }
    @When("user enter search text in search input field")
    public void enterProductSearchKey(){
        productsPage.waitForElement("Search_Field");
        productsPage.enterSearchKey("Apple");
    }

    @And("user clicks on search button")
    public void clickSearchButton(){
        productsPage.waitForElement("Search_Button");
        productsPage.clickOnSearchButton();
    }

    @Then("user should get search results")
    public void validateSearchStoreOutputs(){
        productsPage.waitForElement("Items_Grid");
        Assert.assertTrue("The is no search results returned",productsPage.isItemsBoxContainsProducts());
    }

    @When("user switch the currency between US-Euro")
    public void switchCurrency(){
        productsPage.waitForElement("Currency_Menu");
        currentCurrency = productsPage.switchCurrency();
    }

    @Then("the products currency should be changed")
    public void validateSwitchedCurrency(){
        productsPage.waitForElement("Price_Span");
        if(currentCurrency == "US Dollar"){
            Assert.assertTrue("The currency should be US Dollar but it found in Euro",productsPage.getCurrentCurrency().contains("$"));
        }else{
            Assert.assertTrue("The currency should be Euro but it found in US Dollar",productsPage.getCurrentCurrency().contains("€"));
        }
    }

    @When("user selects a category")
    public void selectRandomCategory(){
        productsPage.waitForElement("Product_Categories");
        productCatAndSub = productsPage.selectRandomProductCategory();
    }

    @Then("validate that correct category is selected")
    public void validateTheCurrentCategoryPage() throws InterruptedException {
        Thread.sleep(3000);
        if(productCatAndSub[1]!="NA"){
            Assert.assertTrue("Didn't navigate to the correct category or subcategory page",automationStepsWebDriver.getCurrentUrl().contains(productCatAndSub[1].replace(" & ","-").replace(" ","-").toLowerCase(Locale.ROOT)));
        }else {
            Assert.assertTrue("Didn't navigate to the correct category or subcategory page",automationStepsWebDriver.getCurrentUrl().contains(productCatAndSub[0].replace(" & ","-").replace(" ","-").toLowerCase(Locale.ROOT)));
        }
    }

    @When("user selects a category and subcategory")
    public void selectCategoryAndSubcategory(){
        productsPage.waitForElement("Product_Categories");
        productsPage.selectProductCatAndSubCat("Apparel","Shoes");
    }

    @And("user filters between products by color")
    public void filterByColor(){
        productsPage.filterByProductColor("Red");
    }

    @Then("validate that data shown after filter")
    public void validatedFiltrationOutput(){
        productsPage.waitForElement("Items_Grid");
        Assert.assertTrue("The is no filtration results returned",productsPage.isItemsBoxContainsProducts());
    }

    @When("user selects and clicks on one of available tags")
    public void selectFromTags(){
        selectedTag = "cool";
        productsPage.waitForElement("Tags_Component");
        productsPage.selectSpecificTag(selectedTag);
    }

    @Then("validate that correct tag is selected")
    public void validateTheCurrentTagPage() throws InterruptedException {
        Thread.sleep(3000);
            Assert.assertTrue("Didn't navigate to the correct Tag page",automationStepsWebDriver.getCurrentUrl().contains(selectedTag.replace(" & ","-").replace(" ","-").toLowerCase(Locale.ROOT)));
    }

    @When("^user adds products to \"(.*)\"$")
    public void addProductsToSpecificList(String listTypeKey){
        productsPage.waitForElement("Items_Grid");
        productsPage.addProductsToList(listTypeKey);
    }

    @Then("^validate adding product to \"(.*)\" success message displayed$")
    public void validateProductActionsOutMsg(String listTypeKey){
        productsPage.waitForElement("Product_Actions_Output_Msg");
        switch (listTypeKey){
            case "Shopping cart":
                Assert.assertTrue("The adding product to shopping cart message is different",productsPage.getProductActionsOutMsg().equals("The product has been added to your shopping cart"));
                break;
            case "Wishlist":
                Assert.assertTrue("The adding product to shopping cart message is different",productsPage.getProductActionsOutMsg().equals("The product has been added to your wishlist"));
                break;
            case"Compare list":
                Assert.assertTrue("The adding product to shopping cart message is different",productsPage.getProductActionsOutMsg().equals("The product has been added to your product comparison"));
                break;
        }
    }

    @And("^validate that the selected products added to \"(.*)\"$")
    public void validateThatSelectedProductAddedToRequiredList(String listTypeKey) throws InterruptedException {
        productsPage.closePushMsg();
        Assert.assertTrue("The product not exist in "+listTypeKey+" page",productsPage.checkIfProductAddedToTheList(listTypeKey));
    }

    @And("user Navigates to Shopping cart")
    public void navigateToShoppingCart() throws InterruptedException {
        productsPage.waitForElement("Product_Actions_Output_Msg");
        productsPage.closePushMsg();
        productsPage.checkIfProductAddedToTheList("Shopping cart");
    }

    @And("user accepts terms of service and then clicks checkout button")
    public void acceptTermsOfServiceAndClickCheckout(){
        checkoutPage.waitForElement("Checkout_Button");
        checkoutPage.acceptTermsOfService();
        checkoutPage.clickCheckOut();
    }

    @And("user fills checkout form")
    public void fillCheckoutForm() throws InterruptedException {
    checkoutPage.fillCheckoutForm();
    }

    @Then("validate completed order success message")
    public void validatedOrderCompletionSuccessMsg(){
        checkoutPage.waitForElement("Order_Completion_Msg");
        Assert.assertTrue("The Order completion msg is different",checkoutPage.getOrderCompletionOutMsg().equals("Your order has been successfully processed!"));
    }
}
