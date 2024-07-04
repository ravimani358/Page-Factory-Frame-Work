package qtriptest;

import qtriptest.tests.DriverSingleton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReportSingleton {


    public static ExtentTest test;
    public static ExtentReports report;
    public static ReportSingleton instanceOfSingletonClass;


    private ReportSingleton() throws MalformedURLException{// creating a private constructor to avoid creation outside this class
	
        // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // String timestampString = String.valueOf(timestamp.getTime());
        // report = new ExtentReports(System.getProperty("user.dir")+"/OurExtentReport"+timestampString+".html",false);
        report = new ExtentReports(System.getProperty("user.dir") + "/OurExtentReport" + ".html",false);
        report.loadConfig(new File("/home/crio-user/workspace/addidmanis8988-ME_QTRIP_QA_V2/app/extent_customization_configs.xml"));
        // report.loadConfig(new File(System.getProperty("user.dir")+"extent_customization_configs.xml"));
    }

    public static ReportSingleton getInstanceOfSigletonReportClass() throws MalformedURLException{
        if(instanceOfSingletonClass==null){
            
            instanceOfSingletonClass = new ReportSingleton();
        }
        return instanceOfSingletonClass;
    }

    public ExtentReports getReport() {
        return report;
    }

    public ExtentTest getTest(ExtentTest test) {
        this.test = test;
        return test;
    }

    public void startTest(String testName) {
        test = report.startTest(testName);
    }

    // public void logPassTest(String details) {
    //      test.log(LogStatus.PASS, details);
    // }

    public static void logFailTest(String details) {
        test.log(LogStatus.FAIL, details);
   }

    public static void endTest() {
        report.endTest(test);
    }

    public void flushReport() {
        report.flush();
    }
    
    public String capture(RemoteWebDriver driver) throws IOException {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File Dest = new File(System.getProperty("user.dir")+"/QTRIPImages/" + System.currentTimeMillis() + ".png");
            String filePath = Dest.getAbsolutePath();
            FileUtils.copyFile(scrFile, Dest);
            return filePath;
            }  


}