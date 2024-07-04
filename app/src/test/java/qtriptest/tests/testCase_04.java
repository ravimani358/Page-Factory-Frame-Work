package qtriptest.tests;

import qtriptest.DP;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.AdventureSuccessPage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_04 {


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
        test = report.startTest("TestCase04");
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        reportSingleton.flushReport();
    }


    @Test(description = "Booking history flow", priority = 4,
            dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, groups="Reliability Flow")
    public void TestCase04(String UserName, String Password, String dataset1, String dataset2,
            String dataset3) throws InterruptedException, IOException {

        SoftAssert softAssert = new SoftAssert();
        String[] DS1 = dataset1.split(";");
        String[] DS2 = dataset2.split(";");
        String[] DS3 = dataset3.split(";");
        HomePage home = new HomePage(driver);
        home.goToHomePage();
        home.clickRegisterButton();
        RegisterPage register = new RegisterPage(driver);
        register.registerUser(UserName, Password, Password, true);
        String oldUserName = register.lastGeneratedUserName;
        Thread.sleep(5000);
        LoginPage login = new LoginPage(driver);
        login.performLogin(oldUserName, Password);
        Thread.sleep(1000);
        home.searchCity(DS1[0]);
        Thread.sleep(1000);
        home.isCityPresent(DS1[0]);
        AdventurePage advPage = new AdventurePage(driver);
        advPage.searchAdv(DS1[1]);
        Thread.sleep(3000);
        advPage.selectAdvRecord();
        AdventureDetailsPage advDetailPage= new AdventureDetailsPage(driver);
        advDetailPage.bookAdventure(DS1[2], DS1[3], DS1[4]);
        home.goToHomePage();
        home.searchCity(DS2[0]);
        Thread.sleep(1000);
        home.isCityPresent(DS2[0]);
        advPage.searchAdv(DS2[1]);
        Thread.sleep(3000);
        advPage.selectAdvRecord();
        advDetailPage.bookAdventure(DS2[2],DS2[3],DS2[4]);
        home.goToHomePage();
        home.searchCity(DS3[0]);
        Thread.sleep(1000);
        home.isCityPresent(DS3[0]);
        advPage.searchAdv(DS3[1]);
        Thread.sleep(3000);
        advPage.selectAdvRecord();
        advDetailPage.bookAdventure(DS3[2],DS3[3],DS3[4]);
        AdventureSuccessPage advSuccessPage= new AdventureSuccessPage(driver);
        advSuccessPage.goToReservation();
        Thread.sleep(3000);
        HistoryPage hp=new HistoryPage(driver);
        boolean status= hp.verifyReservationIdCount("3");
        softAssert.assertTrue(status, "The reservation count is wrong");
        
        test.log(LogStatus.INFO, "The reservation count is wrong");

        test.log(LogStatus.INFO,
                reportSingleton.getTest(test).addScreenCapture(reportSingleton.capture(driver)));
        
        // ReportSingleton.logPassTest("The reservation count is wrong");
		// ReportSingleton.logPassTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)) + "The reservation count is wrong");
		// try {
		// 	softAssert.assertTrue(status, "The reservation count is wrong");
		// } catch (AssertionError e) {
		// 	// Log failed and take screenshot on assertion failure
		// 	ReportSingleton.logFailTest("Assertion Failed: The reservation count is wrong");
		// 	ReportSingleton.logFailTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)));
		// 	throw e; // Re-throw the exception to mark the test as failed
		// }
        home.clicklogoutPage();         
    }


}
