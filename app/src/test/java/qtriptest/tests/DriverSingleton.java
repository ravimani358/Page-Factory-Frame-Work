package qtriptest.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {

    public static DriverSingleton instanceOfSingletonClass;

    private RemoteWebDriver driver;

    private DriverSingleton() throws MalformedURLException{// creating a private constructor to avoid creation outside this class
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		driver.manage().window().maximize();

    }
    
    public static DriverSingleton getInstanceOfSigletonBrowserClass() throws MalformedURLException{
        if(instanceOfSingletonClass==null){
            
            instanceOfSingletonClass = new DriverSingleton();
        }
        return instanceOfSingletonClass;
    }

    public RemoteWebDriver getDriver(){
        return driver;
    }
}
