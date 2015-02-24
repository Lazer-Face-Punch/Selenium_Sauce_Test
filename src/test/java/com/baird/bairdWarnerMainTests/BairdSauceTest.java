package com.baird.bairdWarnerMainTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baird.pageObject.SauceAccountLoginTest;
import com.baird.pageObject.SauceSuper;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.testng.SauceOnDemandTestListener;


@Listeners ({SauceOnDemandTestListener.class})
 public class BairdSauceTest extends SauceSuper{
		/* WebDriver driver; 
		 SauceAccountLoginTest objLogin;*/ 
		/* BaseTestSub objSetup; */
	 
	 
	 

		@Test(/*dataProvider = "hardCodedBrowsers", dataProviderClass = com.baird.pageObject.Sauce.class*/)
		public void test_Account_Login_Page_Appear_Correct() throws Exception {
			
			// Create login page object
			objLogin = new SauceAccountLoginTest(driver);

			objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
			
			//Returns welcome title and asserts true
			String loginWelcomeTitle = objLogin.getLoginWelcome();
			System.out.println(loginWelcomeTitle);
			Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberry"));

		}
		
		/*@AfterMethod
		public void tearDownAfterTestClass()throws Exception{
	Sauce.tearDown();
		}*/

	}
