package qtriptest.tests;

import qtriptest.DP;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_01 {

	static RemoteWebDriver driver;
	// public static String lastGeneratedUserName;
	static ReportSingleton reportSingleton;
	static ExtentReports report;
	static ExtentTest test;

	// Method to help us log our Unit Tests
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
		test = report.startTest("TestCase01");
	}

	@AfterSuite
	public static void quitDriver() {
		System.out.println("quit()");
		driver.quit();
		report.endTest(test);
		reportSingleton.flushReport();
	}



	@Test(description = "Verify registration flow", priority = 1, dataProvider = "data-provider",
			dataProviderClass = DP.class, enabled = true, groups = "Login Flow")
	public void TestCase01(String UserName, String Password)
			throws InterruptedException, IOException {
		try {

			// Boolean status;
			HomePage home = new HomePage(driver);
			home.goToHomePage();
			Thread.sleep(5000);
			home.clickRegisterButton();
			RegisterPage register = new RegisterPage(driver);
			register.registerUser(UserName, Password, Password, true);
			String oldUserName = register.lastGeneratedUserName;
			Thread.sleep(5000);
			LoginPage login = new LoginPage(driver);
			login.performLogin(oldUserName, Password);
			Thread.sleep(3000);
			home.clicklogoutPage();
			Thread.sleep(3000);

			test.log(LogStatus.INFO,
			reportSingleton.getTest(test).addScreenCapture(reportSingleton.capture(driver)));

			test.log(LogStatus.INFO,
			"Registration Flow successfull");


			// ReportSingleton.logPassTest("Registration Flow successfull");
			// ReportSingleton.logPassTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver))
			// + "Registration is successful");
			Assert.assertTrue(home.verifyUserLoggedIn(), "User is not logged out from the page");
			// try {
			// Assert.assertTrue(home.verifyUserLoggedOut(), "User is not logged out from the
			// page");
			// } catch (AssertionError e) {
			// // Log failed and take screenshot on assertion failure
			// ReportSingleton.logFailTest("Assertion Failed: User is not logged out from the
			// page");
			// ReportSingleton.logFailTest(ReportSingleton.getTest().addScreenCapture(ReportSingleton.capture(driver)));
			// throw e; // Re-throw the exception to mark the test as failed
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
