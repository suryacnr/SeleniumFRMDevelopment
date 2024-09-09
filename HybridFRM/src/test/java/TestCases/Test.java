package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import BaseClass.BaseClass;
import org.testng.annotations.DataProvider;
import pageObjects.HealthInsurancePage;

public class Test extends BaseClass {
	@BeforeTest
	public void setValues() {

		testName = "TS_001";
		testDescription = "Check the total premium is the sum of base premiums ";
		category = "Smoke";
		testAuthor = "Surya";

	}

	@org.testng.annotations.Test
	public void tS_008(){
		HealthInsurancePage health =new HealthInsurancePage(driver,test);
		health.ClickReAssure();
		health.ClickYou();
		health.ClickContine();
		health.EnterYourAge();
		health.EnterpineCode();
		health.SelectReassurePlanType();
		health.ClickCoverage();
		health.ClickCalculatePremium();
		int ExpectedResult = (int) health.CaptureBasePremium();
		int ActualResult =(int)health.CaptureTotalpremium();
        if(ExpectedResult == ActualResult){

			reportStep("Base Premium and Total Premium are same after dedicating the riders","pass",driver);
		}else{
			reportStep("Base Premium and Total Premium are not same after dedicating the riders","fail",driver);

		}
		Assert.assertEquals(ExpectedResult,ActualResult);





	}





//	@Test(priority=1)
//	public void Tc_IESP_001() {
//		driver.get(baseUrl);
//		HealthInsurancePage page =new HealthInsurancePage(driver);
//		try {
//			driver.findElement(HealthInsurancePage.uid).sendKeys(username);
//			//page.setuid(username);
//			logger.info("Username entered");
//			page.setpsd(Password);
//			page.setlogin();
//			Thread.sleep(20);
//			getScreenshot(driver,"Login");
//
//
//			if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
//				System.out.println("TestStepPass");
//			}else {
//				getScreenshot(driver,"Login");
//				System.out.println("TestStepFail");
//
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//
//		}
//
//
//	}

}
