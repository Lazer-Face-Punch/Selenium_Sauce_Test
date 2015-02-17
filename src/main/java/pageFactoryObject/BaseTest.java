package pageFactoryObject;


/*import org.openqa.selenium.WebDriver;*/
import org.testng.Assert;
import org.testng.annotations.Test;

import com.baird.pageObject.AccountLoginTest;
import com.baird.pageObject.SauceBase;



 public class BaseTest extends SauceBase{
		/* WebDriver driver; */
		/* AccountLoginTest objLogin; */
		/* BaseTestSub objSetup; */

		@Testt
		public void test_Account_Login_Page_Appear_Correct() throws Exception {
			// Create login page object
			objLogin = new AccountLoginTest(driver);

			objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
			
			//Returns welcome title and asserts true
			String loginWelcomeTitle = objLogin.getLoginWelcome();
			System.out.println(loginWelcomeTitle);
			Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberry"));

		}

	}
