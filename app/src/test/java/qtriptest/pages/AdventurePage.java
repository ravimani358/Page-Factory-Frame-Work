package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {

    @FindBy(name = "duration")
    WebElement durationfilter;

    @FindBy(id = "category-select")
    WebElement cateogoryDd;

    @FindBy(name = "search-adventures")
    public  WebElement searchAdv;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement durationClear;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement cateogoryClear;

    @FindBy(xpath = "//div[@onclick='resetAdventuresData()']")
    WebElement searchAdvClear;

    @FindBy(xpath = "//div[@class='activity-card']")
    public static WebElement seacrhRecord;

    @FindBy(xpath = "//div[@class='activity-card']")
    List <WebElement> seacrhRecordDetails;
    

    RemoteWebDriver driver;

    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);

    }

    public void selectDurationFilter(String hours) throws InterruptedException {
        Select select = new Select(durationfilter);
        durationfilter.click();
        Thread.sleep(2000);
        select.selectByVisibleText(hours);
    }

    public void selectCateogoryDD(String Category_Filter) throws InterruptedException {
        Select select = new Select(cateogoryDd);
        cateogoryDd.click();
        Thread.sleep(2000);
        select.selectByVisibleText(Category_Filter);
    }

    public void searchAdv(String searchAdventure) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(searchAdv));
        searchAdv.click();
        searchAdv.sendKeys(searchAdventure);
        Actions action= new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(3000);
    }

    public void clearAllFilters() throws InterruptedException {
        // durationClear.click();
        // cateogoryClear.click();
        // searchAdvClear.click();
        SeleniumWrapper.click(durationClear, driver);
        SeleniumWrapper.click(cateogoryClear, driver);
        SeleniumWrapper.click(searchAdvClear, driver);

    }

    public void selectAdvRecord() throws InterruptedException{

        // seacrhRecord.click();
        SeleniumWrapper.click(seacrhRecord, driver);
        Thread.sleep(2000);
    }

    public List<WebElement> verifyFilterRecords() {
        try {

            return seacrhRecordDetails;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return seacrhRecordDetails;
        }
    }
}


