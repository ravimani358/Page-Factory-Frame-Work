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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_03 {

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
        test = report.startTest("TestCase03");
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        reportSingleton.flushReport();
    }


    @Test(description = "Booking and cancellation flow", priority = 3,
            dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, groups="Booking and Cancellation Flow")
    public void TestCase03(String UserName, String Password, String CityName, String AdventureName,
            String GuestName, String Date, String count) throws InterruptedException, IOException {

        SoftAssert softAssert = new SoftAssert();
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
        home.searchCity(CityName);
        Thread.sleep(1000);
        home.isCityPresent(CityName);
        AdventurePage advPage = new AdventurePage(driver);
        advPage.searchAdv(AdventureName);
        Thread.sleep(3000);
        advPage.selectAdvRecord();
        AdventureDetailsPage advDetailPage= new AdventureDetailsPage(driver);
        advDetailPage.bookAdventure(GuestName, Date, count);
        Thread.sleep(3000);
        AdventureSuccessPage advSuccessPage= new AdventureSuccessPage(driver);
        softAssert.assertTrue(advSuccessPage.verifySuccessMessage(), "The reservation is not reserved");
        Thread.sleep(3000);
        advSuccessPage.goToReservation();
        Thread.sleep(3000);
        HistoryPage hp=new HistoryPage(driver);
        String trnsactionId= hp.getReservationId(AdventureName);
        System.out.println(trnsactionId);
        hp.cancelReservation(trnsactionId);
        driver.navigate().refresh();
        Thread.sleep(2000);
        softAssert.assertTrue(hp.isReservationIdVisible(), "Reservation id is present after cancelled");
        
        test.log(LogStatus.INFO, "Booking and cancellation flow");

        test.log(LogStatus.INFO,
                reportSingleton.getTest(test).addScreenCapture(reportSingleton.capture(driver)));

        // ReportSingleton.logPassTest("Booking and cancellation flow");
		// ReportSingleton.logPassTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)) + "Booking and cancellation flow");

		// try {
		// 	softAssert.assertTrue(hp.isReservationIdVisible(), "Reservation id is present after cancelled");
		// } catch (AssertionError e) {
		// 	// Log failed and take screenshot on assertion failure
		// 	ReportSingleton.logFailTest("Assertion Failed: Reservation id is present after cancelled");
		// 	ReportSingleton.logFailTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)));
		// 	throw e; // Re-throw the exception to mark the test as failed
		// }
        home.clicklogoutPage();
            
    }


}
