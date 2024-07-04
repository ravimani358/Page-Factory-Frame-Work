package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    String homePageURL = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//*[text()='Register']")
    WebElement registerButton;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//*[text()='Login Here']")
    WebElement loginHere;

    @FindBy(xpath = "//*[@placeholder='Search a City ']")
    public  WebElement searchBox;

    @FindBy(xpath = "//ul[@id='results']//*")
    public  List<WebElement> searchBoxSuggestion;

    @FindBy(xpath = "//*[text()='No City found']")
    public  WebElement noResultFound;



    static RemoteWebDriver driver;

    public HomePage(RemoteWebDriver driver) {
        HomePage.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);

    }

    public void goToHomePage() throws InterruptedException {
        // driver.navigate().to(homePageURL);
        SeleniumWrapper.navigate(driver, homePageURL);
        Thread.sleep(3000);
    }

    public boolean clickRegisterButton() throws InterruptedException {
        // registerButton.click();
        return SeleniumWrapper.click(registerButton, driver);
    }

    public boolean verifyUserLoggedIn() {
        return loginHere.isDisplayed();
    }

    public boolean clicklogoutPage() throws InterruptedException {
        // if (logoutButton.isDisplayed()) {
        //     logoutButton.click();
        // } else {
        //     System.out.println("Element is not displayed");
        // }
       return SeleniumWrapper.click(logoutButton, driver);
    }

    public boolean verifyUserLoggedOut() {

        return logoutButton.isDisplayed();

    }

    public boolean searchCity(String cityName) throws InterruptedException {
        
        Thread.sleep(2000);
        // searchBox.sendKeys(cityName);
        return SeleniumWrapper.sendKeys(searchBox, cityName);
    
        // Thread.sleep(2000);
        
    }


    public boolean isCityPresent(String cityName) {

        // Wait for the elements to be present in the DOM
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(searchBoxSuggestion));

        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                for (WebElement searchedCity : searchBoxSuggestion) {
                    if (searchedCity.getText().equalsIgnoreCase(cityName)) {
                        searchedCity.click();
                        return true;
                    }
                }

                // Check if the "No City found" message is displayed
                return noResultFound.isDisplayed();
            } catch (StaleElementReferenceException e) {
                // Log or handle the exception if needed
                e.printStackTrace();

                // Wait for the elements to be present in the DOM again
                wait.until(ExpectedConditions.visibilityOfAllElements(searchBoxSuggestion));
            }
        }

        // If after 3 attempts the element is still not found, return false
        return false;
    }
}



