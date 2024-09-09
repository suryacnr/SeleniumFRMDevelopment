package TestCases;

import BaseClass.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HealthInsurancePage;
import pageObjects.LoginPage;

public class TS_001 extends BaseClass {

    @BeforeTest
    public void setValues() {

        testName = "TS_001";
        testDescription = "Verify whether user can login with valid username and password";
        category = "Functional";
        testAuthor = "Surya";

    }

    @Test
    public void tS_001(){
        LoginPage page =new LoginPage(driver,test);
        page.EnterUsername();
        page.EnterPassword();
        page.ClickLoginButton();
        page.pageLoad(60);
        var ExpectedResult=page.testdataload("TS_001","DashboardURL");
        var ActualResult=page.getCurrentURL();
        if(ExpectedResult == ActualResult){

            reportStep("User has logged into Dashboard Successfully","pass",driver);
        }else{
            reportStep("User has not logged into Dashboard","fail",driver);

        }
        Assert.assertEquals(ExpectedResult,ActualResult);





    }
}
