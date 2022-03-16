package PageClasses;

import io.cucumber.java.nl.Stel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsPageComponents {
    WebDriver productsDriver;
    WebDriverWait productsDriverWait;
    Random randomObj;
    int randomInt;

    public ProductsPageComponents(WebDriver inputDriver, WebDriverWait inputWait){
        productsDriver = inputDriver;
        productsDriverWait = inputWait;
        PageFactory.initElements(inputDriver,this);
    }

    @FindBy(id ="small-searchterms")
    WebElement searchStoreInputTextField;

    @FindBy(xpath ="//*[@id=\"small-search-box-form\"]/button")
    WebElement searchButton;

    @FindBy(className = "item-grid")
    WebElement itemsGrid;

    @FindBy (id="customerCurrency")
    WebElement currencyMenu;

    @FindBy (xpath = "/html/body/div[6]/div[3]/div/div/div/div/div[4]/div[2]/div[1]/div/div[2]/div[3]/div[1]/span")
    WebElement priceSpan;

    @FindBy (xpath = "/html/body/div[6]/div[2]/ul[1]")
    WebElement productsCategoryOptionsContainer;

    @FindBy (xpath = "/html/body/div[6]/div[3]/div/div[2]/div[1]/div/div[2]/ul")
    WebElement colorFilterContainer;

    @FindBy (className = "tags")
    WebElement tagsContainer;

    @FindBy (className = "buttons")
    WebElement productButtons;

    @FindBy (id = "bar-notification")
    WebElement outPutMsgContainer;

    @FindBy (className = "ico-cart")
    WebElement shoppingCartLink;

    @FindBy (className = "ico-wishlist")
    WebElement wishlistLink;

    @FindBy (xpath = "/html/body/div[6]/div[4]/div[1]/div[2]/ul/li[5]/a")
    WebElement compareProductLink;

    @FindBy (className = "sku")
    WebElement shoppingCartProductNum;

    @FindBy (className = "sku-number")
    WebElement wishlistProductNum;

    @FindBy (className = "compare-products-table")
    WebElement compareProductTable;

    @FindBy (className = "close")
    WebElement pushMsgCloseButton;

    public String switchCurrency(){
        Select currencySelector = new Select(currencyMenu);
        if(currencySelector.getFirstSelectedOption().getText().equals("US Dollar")){
            currencySelector.selectByVisibleText("Euro");
            return "Euro";
        }
        else{currencySelector.selectByVisibleText("US Dollar");
        return "US Dollar";
        }
    }

    public String getCurrentCurrency(){
        return priceSpan.getText();
    }

    public String[] selectRandomProductCategory(){
        String categoryName = "";
        String subCategoryName = "";
        List<WebElement> productCategories = productsCategoryOptionsContainer.findElements(By.tagName("li"));
        List<WebElement> filteredProductCategories = new ArrayList<>();
        for (WebElement element:productCategories) {
            if(!element.getText().isEmpty() && element.getText() != ""){
                filteredProductCategories.add(element);
            }
        }
        randomObj = new Random();
        randomInt = randomObj.nextInt(0,filteredProductCategories.size()-1);
        WebElement selectedProductCategory = filteredProductCategories.get(randomInt);
        categoryName = selectedProductCategory.getText();
        productsDriverWait.until(ExpectedConditions.elementToBeClickable(selectedProductCategory));
        try {
            WebElement subCategoriesContainer = selectedProductCategory.findElement(By.tagName("ul"));
            Actions productsActions = new Actions(productsDriver);
            productsActions.moveToElement(selectedProductCategory).perform();
            List<WebElement> productSubCategories = subCategoriesContainer.findElements(By.tagName("li"));
            randomInt = randomObj.nextInt(0,productSubCategories.size()-1);
            WebElement selectedProductSubCategory = productSubCategories.get(randomInt);
            subCategoryName = selectedProductSubCategory.getText();
            selectedProductSubCategory.click();
        }catch (Exception categoryException){
            subCategoryName = "NA";
            selectedProductCategory.click();
        }
        String[]  outPutCategories = {categoryName,subCategoryName};
        return outPutCategories;
    }

    public void enterSearchKey(String searchKey){
        searchStoreInputTextField.sendKeys(searchKey);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

    public boolean isItemsBoxContainsProducts(){
        if(itemsGrid.findElements(By.xpath(".//*")).size()>0) {
            return true;
        }else {return false;}
    }

    public void selectProductCatAndSubCat(String productCategory,String productSubCategory){
        List<WebElement> productCategories = productsCategoryOptionsContainer.findElements(By.tagName("li"));
        WebElement selectedProductCategory;
        for (WebElement element:productCategories) {
            if (element.getText().equals(productCategory)) {
                selectedProductCategory = element;
                Actions productsActions = new Actions(productsDriver);
                productsActions.moveToElement(selectedProductCategory).perform();
                WebElement subCategoriesContainer = selectedProductCategory.findElement(By.tagName("ul"));
                List<WebElement> productSubCategories = subCategoriesContainer.findElements(By.tagName("li"));
                for (WebElement subElement:productSubCategories) {
                    if(subElement.getText().equals(productSubCategory)){
                        subElement.click();
                        break;
                    }
                }
                break;
            }
        }
    }

    public void filterByProductColor(String color){
        List<WebElement> colorOptions = colorFilterContainer.findElements(By.tagName("li"));
        for (WebElement colorOption:colorOptions) {
            try {
                WebElement colorOptionLabel =colorOption.findElement(By.tagName("label"));
                if (colorOptionLabel.getText().trim().equals(color)){
                    colorOptionLabel.click();
                    break;
                }
            }catch (Exception elementNotFoundExcep){}
        }
    }

    public void selectSpecificTag(String tagKey){
        List<WebElement> tagOptions = tagsContainer.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement tagOption:tagOptions){
            if(tagOption.findElement(By.tagName("a")).getText().equals(tagKey)){
                tagOption.findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    public void addProductsToList(String listTypeKey){
        switch (listTypeKey){
            case "Shopping cart":
                productButtons.findElements(By.tagName("button")).get(0).click();
                break;
            case "Wishlist":
                productButtons.findElements(By.tagName("button")).get(2).click();
                break;
            case"Compare list":
                productButtons.findElements(By.tagName("button")).get(1).click();
                break;
        }
    }

    public boolean checkIfProductAddedToTheList(String listTypeKey){
        boolean productExistenceFlag = false;
        switch (listTypeKey){
            case "Shopping cart":
                shoppingCartLink.click();
                productExistenceFlag = shoppingCartProductNum.isDisplayed();
                break;
            case "Wishlist":
                wishlistLink.click();
                productExistenceFlag =  wishlistProductNum.isDisplayed();
                break;
            case"Compare list":
                compareProductLink.click();
                productExistenceFlag = compareProductTable.isDisplayed();
                break;
        }

        return productExistenceFlag;
    }


    public String getProductActionsOutMsg(){
        return outPutMsgContainer.findElement(By.tagName("div")).findElement(By.tagName("p")).getText();
    }

    public void closePushMsg() throws InterruptedException {
        pushMsgCloseButton.click();
        Thread.sleep(2000);
    }
    public void waitForElement(String elementToWait){
        switch (elementToWait) {

            case "Search_Field":
                productsDriverWait.until(ExpectedConditions.visibilityOf(searchStoreInputTextField));
                break;

            case "Search_Button":
                productsDriverWait.until(ExpectedConditions.elementToBeClickable(searchButton));
                break;

            case "Items_Grid":
                productsDriverWait.until(ExpectedConditions.visibilityOf(itemsGrid));
                break;

            case "Currency_Menu":
                productsDriverWait.until(ExpectedConditions.visibilityOf(currencyMenu));
                break;

            case "Price_Span":
                productsDriverWait.until(ExpectedConditions.visibilityOf(priceSpan));
                break;

            case "Product_Categories":
                productsDriverWait.until(ExpectedConditions.visibilityOf(productsCategoryOptionsContainer));
                break;

            case "Tags_Component":
                productsDriverWait.until(ExpectedConditions.visibilityOf(tagsContainer));
                break;

            case "Product_Actions_Output_Msg":
                productsDriverWait.until(ExpectedConditions.visibilityOf(outPutMsgContainer));
                break;
            default:
                break;
        }}}
