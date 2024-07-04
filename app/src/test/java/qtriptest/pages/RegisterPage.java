package qtriptest.pages;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

    RemoteWebDriver driver;
    String url= "https://qtripdynamic-qa-frontend.vercel.app/";

    String registerUrl= "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    @FindBy(name = "email")
    WebElement registerUserName;

    @FindBy(name = "password")
    WebElement registerPassword;

    @FindBy(name = "confirmpassword")
    WebElement reTypePassword;

    @FindBy(xpath="//*[text()='Register Now']")
    WebElement registerNowButton;

    public String lastGeneratedUserName="";


    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }

    public boolean checkRegisterPageNavigation(){
        return driver.getCurrentUrl().contains("register");
    }

    public void navigateToRegisterPage() throws InterruptedException{
     
        driver.navigate().to(registerUrl);
        Thread.sleep(3000);
    }

    public void registerUser(String userName,String password, String confirmPassword,boolean generateRandomUsername){
    
        
        if (generateRandomUsername) 
            userName= userName.split("@")[0]+ UUID.randomUUID().toString()+ "@" +userName.split("@")[1];
            
            this.lastGeneratedUserName=userName;
    
        registerUserName.sendKeys(userName);
        registerPassword.sendKeys(password);
        reTypePassword.sendKeys(confirmPassword);

        try{

            registerNowButton.click();
        }
        catch(ElementClickInterceptedException e){
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", registerNowButton);
                
            };

        }
        
    }




