package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	//DataProviders.java file is in another pkg,so have to mention class name
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void test_LoginDDT(String email, String pwd, String exp) {
		logger.info(" Starting TC_003_LoginDataDrivenTest ");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExists();// this method is created MyAccountPage

			//This exp value is coming from Excel sheet
			if (exp.equals("Valid")) 
			{
				
				//If data is valid and target page is valid
				if (targetpage == true) 
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				} 
				else 
				{
				//If data is valid and target page is invalid
					Assert.assertTrue(false);
				}
			}
			
			if (exp.equals("Invalid")) 
			{
				if (targetpage == true) 
				{
					//If data is invalid and target page is valid
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				} 
				else 
					
				{
					//If data is invalid and target page is invalid
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info(" Finished TC_003_LoginDataDrivenTest");

	}

}