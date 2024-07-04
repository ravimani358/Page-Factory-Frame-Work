package qtriptest.tests;

import qtriptest.DP;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_02 {

    static RemoteWebDriver driver;
    // public static String lastGeneratedUserName;
    static ReportSingleton reportSingleton;
    static ExtentReports report;
    static ExtentTest test;


    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton singleton = DriverSingleton.getInstanceOfSigletonBrowserClass();
        driver = singleton.getDriver();
        reportSingleton = ReportSingleton.getInstanceOfSigletonReportClass();
        report = reportSingleton.getReport();
        test = report.startTest("TestCase02");
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        reportSingleton.flushReport();
    }


    @Test(description = "Verify that Search and filters works fine", priority = 2,
            dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true,
            groups = "Search and Filter flow")
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String ExpectedFilteredResults, String ExpectedUnFilteredResults)
            throws InterruptedException, IOException {

        SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(driver);
        home.goToHomePage();
        Thread.sleep(1000);
        home.searchCity(CityName);
        Thread.sleep(1000);
        softAssert.assertTrue(home.isCityPresent(CityName), "No City is found is displayed");
        Thread.sleep(3000);
        AdventurePage advPage = new AdventurePage(driver);
        advPage.selectCateogoryDD(Category_Filter);
        advPage.selectDurationFilter(DurationFilter);
        softAssert.assertEquals(advPage.verifyFilterRecords(), ExpectedFilteredResults,
                "The records are worngly showing");
        advPage.clearAllFilters();
        Thread.sleep(3000);
        softAssert.assertEquals(advPage.verifyFilterRecords(), ExpectedFilteredResults,
                "The records are worngly showing");

        test.log(LogStatus.INFO,
                reportSingleton.getTest(test).addScreenCapture(reportSingleton.capture(driver)));

        test.log(LogStatus.INFO, "Registration Flow successfull");
        // ReportSingleton.logPassTest("Registration Flow successfull");
        // ReportSingleton.logPassTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver))
        // + "Registration is successful");
        // try {
        // softAssert.assertEquals(advPage.verifyFilterRecords(), ExpectedFilteredResults,
        // "The records are worngly showing");
        // } catch (AssertionError e) {
        // // Log failed and take screenshot on assertion failure
        // ReportSingleton.logFailTest("Assertion Failed: The records are worngly showing");
        // ReportSingleton.logFailTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)));
        // throw e; // Re-throw the exception to mark the test as failed
        // }


    }
}
