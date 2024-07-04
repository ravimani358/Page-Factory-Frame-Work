package qtriptest.tests;

import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCases{
    
    static RemoteWebDriver driver;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeClass(alwaysRun = true, enabled = false)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
	}

	@AfterClass(enabled = false)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}




	// TODO: Use the following to test --navigateToRegisterPage()-- method

	@Test(description = "Verify functionality of - navigate to register page", enabled = false)
	public static void testRegister_navigateToRegisterPage() {
		Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to register page", "started");
		try {
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			assertion.assertTrue(driver.getCurrentUrl().equals("https://crio-qkart-frontend-qa.vercel.app/register"));
			logStatus("Page test", "navigation to register page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to register page", "failed");
			e.printStackTrace();
		}
	}


	// TODO: Implement Rest of Unit Tests Here-

	@Test(description = "Verify functionality of - navigate to login page", enabled = false)
	public void testLogin_navigateToLoginPage() {
		Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to login page", "started");
		try {
			LoginPage login = new LoginPage(driver);
			login.navigaetToLogin();
			assertion.assertTrue(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/"));
			logStatus("Page test", "Navigation to Login page", "success");
		} catch (Exception e) {
			logStatus("Page test", "Navigation to Login page", "failed");
			e.printStackTrace();
		}
	}


}