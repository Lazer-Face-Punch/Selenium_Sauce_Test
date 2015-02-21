package com.baird.bairdWarnerMainTests;


/*import org.openqa.selenium.WebDriver;*/
import org.testng.Assert;
import org.testng.annotations.Test;

import com.baird.pageObject.AccountLoginTest;
import com.baird.pageObject.BaseTestSuperr;
import com.baird.pageObject.Driver;
import com.baird.pageObject.SauceSuper;



 public class BaseTest extends BaseTestSuperr{
		/* WebDriver driver; */
		/* AccountLoginTest objLogin; */
		/* BaseTestSub objSetup; */

		@Test
		public void test_Account_Login_Page_Appear_Correct() throws Exception {
			// Create login page object
			objLogin = new AccountLoginTest(Driver.Instance);
			

			objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
			
			//Returns welcome title and asserts true
			String loginWelcomeTitle = objLogin.getLoginWelcome();
			System.out.println(loginWelcomeTitle);
			Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberry"));

		}

	}
