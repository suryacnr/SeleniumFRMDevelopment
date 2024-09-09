package TestCases;

import BaseClass.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class TS_002 extends BaseClass {
    @BeforeTest
    public void setValues() {

        testName = "TS_002";
        testDescription = "Verify whether user can't login with following combination" +
                "1.valid username and invalid password" +
                "2.invalid username and valid password" +
                "3.invalid username and invalid password";
        category = "Functional";
        testAuthor = "Surya";

    }

    @Test(dataProviderClass = pageObjects.DataProvider.class,dataProvider = "data-provider")
    public void tS_002(String username,String pswd){
        LoginPage page =new LoginPage(driver,test);
        page.EnterUsername(username);
        page.EnterPassword(pswd);
        page.ClickLoginButton();
        page.pageLoad(50);
        var ExpectedResult=page.testdataload("TS_002","DashboardURL");
        var ActualResult=page.getCurrentURL();
        if(ExpectedResult != ActualResult){

            reportStep("User has not logged into Dashboard","pass",driver);
        }else{
            reportStep("User has logged into Dashboard","fail",driver);

        }
        Assert.assertNotEquals(ExpectedResult,ActualResult);





    }
}
