package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {

    @FindBy(name="date")
    WebElement pickADate;

    @FindBy(name="name")
    WebElement nameTextBox;

    @FindBy(name="person")
    WebElement personField;

    @FindBy(className = "reserve-button")
    public static WebElement reserveBtn;


        RemoteWebDriver driver;

        public AdventureDetailsPage(RemoteWebDriver driver){
            this.driver = driver;
            AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
            PageFactory.initElements(factory, this);  
        }

        // public void enterNamefield(String name){
        //     nameTextBox.sendKeys(name);
        // }

        // public void enterPersonCountfield(String personCount){
        //     personField.clear();
        //     personField.sendKeys(personCount);
        // }

        // public void clickReserveBtn(){
        //     reserveBtn.click();
        // }

        // public void pickAdatfield(String date){
        //     pickADate.sendKeys(date);
        // }

        public void bookAdventure(String name, String date, String personCount) throws InterruptedException{
            Thread.sleep(2000);
            // nameTextBox.sendKeys(name);
            // pickADate.sendKeys(date);
            SeleniumWrapper.sendKeys(nameTextBox, name);
            SeleniumWrapper.sendKeys(pickADate, date);
            personField.clear();
            personField.sendKeys(personCount);
            // Thread.sleep(2000);
            // reserveBtn.click();
            SeleniumWrapper.click(reserveBtn, driver);
            Thread.sleep(2000);
        }



}