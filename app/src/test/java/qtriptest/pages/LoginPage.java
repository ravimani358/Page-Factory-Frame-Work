package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


    String loginUrl="https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(xpath="//*[text()='Login Here']")
    WebElement loginHereButton;

    @FindBy(xpath="//*[@name='email']")
    WebElement emailTextBoxElement;

    @FindBy(xpath="//*[@name='password']")
    WebElement passwordTextBox;

    @FindBy(xpath="//*[text()='Login to QTrip']")
    WebElement loginToQtripBtn;

    RemoteWebDriver driver;
    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);

    }
    
    public void goToLoginPage(){
        
        if (!this.driver.getCurrentUrl().equals(this.loginUrl)) {
            this.driver.get(this.loginUrl);
        } 

    }

    public void navigaetToLogin(){
      
        try {
            // Wait for the loginHereButton to be clickable for a maximum of 10 seconds
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(loginHereButton));

            // Click the loginHereButton
            loginHereButton.click();
        } catch (Exception e) {
            // Handle the exception, e.g., log it or take appropriate action
            e.printStackTrace(); // This is a simple example; you may want to handle exceptions more gracefully
        }
           
    }

    public void performLogin(String userName, String PassWord) throws InterruptedException{
    //    emailTextBoxElement.sendKeys(userName);
    //    passwordTextBox.sendKeys(PassWord);
    //    loginToQtripBtn.click();
    SeleniumWrapper.sendKeys(emailTextBoxElement, userName);
    SeleniumWrapper.sendKeys(passwordTextBox, PassWord);
    SeleniumWrapper.click(loginToQtripBtn, driver);
    }
}
