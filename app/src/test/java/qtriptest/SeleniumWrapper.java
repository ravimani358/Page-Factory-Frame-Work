package qtriptest;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.io.FileUtils;

public class SeleniumWrapper {

    RemoteWebDriver driver;

    public SeleniumWrapper(RemoteWebDriver driver) {
        this.driver= driver;
    }

    public static boolean click(WebElement elementToClick, RemoteWebDriver driver) throws InterruptedException {
        try {
            if (elementToClick.isDisplayed()) {
                scrollIntoView(elementToClick, driver);
                elementToClick.click();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend) {
        try {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean navigate(RemoteWebDriver driver, String url) {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(url)) {
                driver.navigate().to(url);
                return driver.getCurrentUrl().equals(url);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement findElementWithRetry(RemoteWebDriver driver, By by, int retryCount) throws Exception {
        int counter=0;
        Exception ex = new Exception();
        while (counter < retryCount) {
            try {
                return driver.findElement(by);
            } catch (Exception e) {
                counter++;
                ex=e;
            }
        }
        throw new Exception(ex.getCause());
    }

    private static void scrollIntoView(WebElement element, RemoteWebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // Handle or log the exception as needed
        }
    }

    // public static String capture(RemoteWebDriver driver) throws IOException {
    //     File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    //     File Dest = new File(System.getProperty("user.dir")+"/QTRIPImages/" + System.currentTimeMillis() + ".png");
    //     String filePath = Dest.getAbsolutePath();
    //     FileUtils.copyFile(scrFile, Dest);
    //     return filePath;
    //     }  
}
