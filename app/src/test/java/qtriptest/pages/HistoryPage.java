
package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {


    @FindBy(xpath = "//tbody[@id='reservation-table']//child::tr")
    public List<WebElement> reservationId;

    @FindBy(xpath = "//th[@scope='row']")
    WebElement transactionID;

    String transactionEleId;

    RemoteWebDriver driver;
    public HistoryPage(RemoteWebDriver driver) {
    
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);   
    }

    public boolean verifyReservationIdCount(String Count){  

        Integer ResCount= reservationId.size();
        if(ResCount.toString().equals(Count)){
            return true;
        }
        return false;

    }

    public List<WebElement> getReservations(){  

        List<WebElement> recervations= new ArrayList<>();
        recervations = driver.findElements(By.xpath("//tbody[@id='reservation-table']//child::tr"));
        return recervations;

    }

    public String getReservationId(String adventureName) throws InterruptedException {
        // Your code to retrieve the transaction ID from the UI
        Thread.sleep(2000);
        List<WebElement> reservationsCount = this.getReservations();

        if (reservationsCount.size() != 0) {
            try {
                for (WebElement reservationId : reservationsCount) {
                    WebElement advElement = reservationId.findElement(By.xpath("child::td[text()='" + adventureName + "']"));
                    if (advElement.getText().equals(adventureName)) {
                        transactionEleId = reservationId.findElement(By.tagName("th")).getText();
                        System.out.println(transactionEleId);
                        return transactionEleId;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null; // Return null if the adventureName is not found or if there's an exception
    }


    public boolean isReservationIdVisible() {
        // Your code to check if the transaction ID is visible on the UI
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + transactionEleId + "')]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean cancelReservation(String transactionEleId){
       if(transactionEleId!=null){
        try{

            WebElement cancelbtn= driver.findElement(By.xpath("//*[@id='" + transactionEleId + "']"));
            cancelbtn.click();
        }  
        catch(NoSuchElementException e){
            System.out.println(e);
        }
        return true;
     }else{
        return false;
       }
    }

}