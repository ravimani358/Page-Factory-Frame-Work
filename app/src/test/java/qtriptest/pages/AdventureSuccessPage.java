package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureSuccessPage {

    @FindBy(id = "reserved-banner")
    WebElement reservationSuccess;

    @FindBy(xpath = "//*[text()='Reservations']")
    public WebElement reservationBtn;


    RemoteWebDriver driver;
    public AdventureSuccessPage(RemoteWebDriver driver) {
    
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);   
    }
    
    public void goToReservation() throws InterruptedException{
        
        // reservationBtn.click();\
        // WebDriverWait wait = new WebDriverWait(driver,30);
        // wait.until(ExpectedConditions.visibilityOfElementLocated(reservationBtn));
        Actions action= new Actions(driver);
        action.doubleClick(reservationBtn).perform();
        Thread.sleep(3000);
    }

    public boolean verifySuccessMessage(){

        reservationSuccess.getText().contains("adventure is successful");
        return true;
    }


 




}
