package com.numadic.pom.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.numadic.pom.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SignupPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(driver, 10);
    public String expectedHeaderText="JOIN OUR CREW";
    public String actualHeaderText="//h1[text()='JOIN OUR CREW']";
    public String jobType="//select[@id='job_type']";
    public String internShip="//option[contains(text(),'Internship')]";
    public String expectedNoAvailableJob="There are no available job positions that match your filters.";
    public String actualNoAvailableJob="//td[contains(text(),'There are no available job positions that match your filters.')]";
    public String qaEngineer="//a[text()='QA Engineer']";
    public String visualDesigner="//a[text()='Visual Designer']";
    public String expectedTitle="https://numadic.com/careers/qa-engineer.php";
    public String actualTitle="";
    public String applyHereNow="//*[text()='Apply here now']";
    public String qaApply="(//button[text()='Apply'])[10]";
    public String firstName="//input[@id='firstName']";
    public String lastName="//input[@id='lastName']";
    public String email="//input[@id='email']";
    public String mobile="//input[@id='phone']";
    public String sports="//select[@id='sports']";
    public String dogsAndCats="//select[@id='pets']";
    public String currentCity="//input[@id='current_city']";
    public String homeTown="//input[@id='hometown']";
    public String dob="//input[@id='dob']";
    public Boolean expecteLastName=false;
    public Boolean expectedEmail=false;
    public Boolean expectedMobile=false;
    public Boolean expectedSports=false;
    public Boolean expectedDogsAndCats=false;
    public Boolean expectedCurrentCity=false;
    public Boolean expectedHomeTown=false;
    public Boolean expectedDateOfBirth=false;
    public Boolean expectedfirstName=false;
	public void signUp(ExtentTest test) throws Throwable {
		test.log(LogStatus.INFO, "Open Numadic Career Page");
		actualHeaderText=driver.findElement(By.xpath(actualHeaderText)).getText();
	}
	
	public void searchJob(ExtentTest test) throws Throwable {
		test.log(LogStatus.INFO, "Search Job");
		WebElement dropdownElement = driver.findElement(By.xpath(jobType));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(3);
		actualNoAvailableJob=driver.findElement(By.xpath(actualNoAvailableJob)).getText();
	}
	
	public void fullTimeJobSearch(ExtentTest test) throws Throwable {
		test.log(LogStatus.INFO, "Full Time Search Job");
		WebElement dropdownElement = driver.findElement(By.xpath(jobType));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(1);
		WebElement qAEngineer = driver.findElement(By.xpath((visualDesigner)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", qAEngineer);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(qaEngineer))).click();
		actualTitle=driver.getCurrentUrl();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)", "");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(applyHereNow))).click();
		
				
	}
	public void applyForQaEgineerJob(ExtentTest test) throws Throwable {
		test.log(LogStatus.INFO, "Apply For QA Engineer");
		WebElement qAEngineer = driver.findElement(By.xpath((visualDesigner)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", qAEngineer);		
		Actions action = new Actions(driver); 
		WebElement element = driver.findElement(By.xpath(qaApply));
		action.moveToElement(element).build().perform();
		driver.findElement(By.xpath(qaApply)).click();				
	}
	public void validateThePersonalDetails(ExtentTest test) throws Throwable {
		test.log(LogStatus.INFO, "Validate All Personal Details");
		waitForPresenceAndVisibilityOfElementry(firstName);
		expectedfirstName=driver.findElement(By.xpath(firstName)).isDisplayed();
		expecteLastName=driver.findElement(By.xpath(lastName)).isDisplayed();
		expectedEmail=driver.findElement(By.xpath(email)).isDisplayed();
		expectedMobile=driver.findElement(By.xpath(mobile)).isDisplayed();
		expectedSports=driver.findElement(By.xpath(sports)).isDisplayed();		
		expectedDogsAndCats=driver.findElement(By.xpath(dogsAndCats)).isDisplayed();
		expectedCurrentCity=driver.findElement(By.xpath(currentCity)).isDisplayed();
		expectedHomeTown=driver.findElement(By.xpath(homeTown)).isDisplayed();
		expectedDateOfBirth=driver.findElement(By.xpath(dob)).isDisplayed();
		
	}
	
}