package com.numadic.pom.testcases;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import java.sql.SQLException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.numadic.pom.pages.SignupPage;
import com.numadic.pom.testcases.base.BaseTest;
import com.numadic.pom.util.Log;

public class Signup extends BaseTest {
	public SignupPage signupTest;
		
	@BeforeMethod
	public void setupTest() throws SQLException {
		init("Chrome");
		signupTest = new SignupPage();
	}

	@Test(priority = 0)
	public void signUp() throws Throwable {
		startExtent = extent.startTest("End To End Scenario");
		Log.startLog("inventorySearchzz");
		signupTest.signUp(startExtent);	
		assertEquals(signupTest.actualHeaderText,signupTest.expectedHeaderText);
		Log.endLog("inventorySearch");
	}
	@Test(priority = 1)
	public void searchJob() throws Throwable {
		startExtent = extent.startTest("Job Search");
		Log.startLog("inventorySearchzz");
		signupTest.searchJob(startExtent);	
		assertEquals(signupTest.actualNoAvailableJob,signupTest.expectedNoAvailableJob);
		Log.endLog("inventorySearch");
	}
	
	@Test(priority = 2)
	public void fullTimeJobSearch() throws Throwable {
		startExtent = extent.startTest("Full Time Job Search");
		Log.startLog("inventorySearchzz");
		signupTest.fullTimeJobSearch(startExtent);	
		assertEquals(signupTest.actualTitle,signupTest.expectedTitle);
		Log.endLog("inventorySearch");
	}
	@Test(priority = 3)
	public void applyForQaEgineerJob() throws Throwable {
		startExtent = extent.startTest("Apply For QA Engineer");
		Log.startLog("inventorySearchzz");
		signupTest.applyForQaEgineerJob(startExtent);
		Log.endLog("inventorySearch");
	}
	@Test(priority = 4)
	public void validateThePersonalDetails() throws Throwable {
		startExtent = extent.startTest("Validate All Personal Details");
		Log.startLog("inventorySearchzz");
		signupTest.validateThePersonalDetails(startExtent);
		assertTrue(signupTest.expectedfirstName);
		assertTrue(signupTest.expecteLastName);
		assertTrue(signupTest.expectedEmail);
		assertTrue(signupTest.expectedMobile);
		assertTrue(signupTest.expectedSports);
		assertTrue(signupTest.expectedDogsAndCats);
		assertTrue(signupTest.expectedCurrentCity);		
		assertTrue(signupTest.expectedHomeTown);
		assertTrue(signupTest.expectedDateOfBirth);
		Log.endLog("inventorySearch");
	}


}
