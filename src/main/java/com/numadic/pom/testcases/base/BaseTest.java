package com.numadic.pom.testcases.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.numadic.pom.base.BasePage;
import com.numadic.pom.util.Constants;
import com.numadic.pom.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest startExtent;
	public DesiredCapabilities cap = null;
	public BasePage utility;

	public static ChromeDriver driver;

	    @BeforeSuite
	    public void launchBrowser() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	    	driver.get("https://numadic.com/careers/");
			utility = new BasePage();

	    }

	public WebDriver init(String bType) {
		if (Constants.LOCAL_SINGLE_BROWSER_RUN) {
			if (bType.equals("Mozilla")) {
				try {
					// System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_EXE);
					System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_EXE);
					FirefoxProfile profile = new FirefoxProfile();
					WebDriver driver = new FirefoxDriver(profile);
				} catch (Exception e) {
					reportFailure(bType + " Browser NOT Launched");
				}
			} else if (bType.equals("Chrome")) {
				try {
					// System.setProperty("webdriver.chrome.driver",
					// Constants.CHROME_DRIVER_EXE_Linux);
					System.setProperty("webdriver.chrome.driver", "\\drivers\\chromedriver.exe");
					ChromeOptions coptions = new ChromeOptions();
					coptions.addArguments("--disable-extensions");
					// driver = new ChromeDriver(coptions);
					return driver;
				} catch (Exception e) {
					reportFailure(bType + " Browser NOT Launched");
				}
			} else if (bType.equals("IE")) {

				try {
					/*
					 * System.setProperty("webdriver.ie.driver",Constants.IE_DRIVER_EXE); driver=new
					 * InternetExplorerDriver();
					 */

					System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_EXE);
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
					capabilities.setCapability("requireWindowFocus", true);

					// driver = new InternetExplorerDriver(capabilities);
				} catch (Exception e) {
					reportFailure(bType + " Browser NOT Launched");
				}
			}

		} else if (!Constants.GRID_RUN) {
			if (bType.equals("Mozilla")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
			} else if (bType.equals("Chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
			} else if (bType.equals("IE")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability("requireWindowFocus", true);
				cap.setCapability("enablePersistentHover", true);
				cap.setCapability("nativeEvents", false);
				cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
			}
			try {
				// driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				reportFailure(bType + " Browser NOT Launched");
				e.printStackTrace();
			}
		} else {  
			// (Constants.GRID_RUN)
			// grid
			DesiredCapabilities cap = null;
			if (bType.equals("Mozilla")) {

				// System.setProperty("webdriver.gecko.driver",
				// Constants.FIREFOX_DRIVER_EXE_Linux);
				System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_EXE);
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			} else if (bType.equals("Chrome")) {
				// System.setProperty("webdriver.chrome.driver",
				// Constants.CHROME_DRIVER_EXE_Linux);
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(org.openqa.selenium.Platform.ANY);

			}
			try {
				// driver = new RemoteWebDriver(new URL("http://hub:4444/wd/hub"), cap);

			} catch (Exception e) {
				reportFailure(bType + " Browser NOT Launched");
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return null;
	}

	public static void reportFailure(String failureMessage) {
		startExtent.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}

	public void reportPass(String successMessage) {
		startExtent.log(LogStatus.PASS, successMessage);
		takeScreenShot();

	}

	/*
	 * public void takeScreenShot(){ Date d=new Date(); String
	 * screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	 * String filePath=Constants.REPORTS_PATH+"screenshots/"+screenshotFile; File
	 * scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * 
	 * try { FileUtils.copyFile(scrFile, new File(filePath));
	 * System.out.println("*********"+"../"+"test"+"******************"); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * test.log(LogStatus.INFO,test.addScreenCapture(filePath)); }
	 */

	public static void takeScreenShot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String filePath = "./screenshots/" + screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(Constants.REPORTS_PATH + "/screenshots", screenshotFile);

		try {
			// FileUtils.copyFile(scrFile, new File(filePath));
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		startExtent.log(LogStatus.INFO, startExtent.addScreenCapture(filePath));
	}	
	@AfterMethod
	public void quit() {

		if (extent != null) {
			extent.endTest(startExtent);
			extent.flush();
		}
		if (driver != null) {
			try {
				//driver.quit();
			} catch (Exception e) {
			//	driver.quit();
			}
		}

	}
}
