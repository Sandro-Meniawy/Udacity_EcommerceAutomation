package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverMethods {
    WebDriver projectWebDriver;
    public WebDriver openWebDriver(String browserType){
        switch (browserType){
            case "Chrome":
                projectWebDriver = new ChromeDriver();
                break;
            case "Firefox":
                projectWebDriver = new FirefoxDriver();
                break;
            default:
                break;
        }
        return projectWebDriver;
    }

    public void closeWebDriver() throws InterruptedException {
        Thread.sleep(5000);
        projectWebDriver.close();
    }
}