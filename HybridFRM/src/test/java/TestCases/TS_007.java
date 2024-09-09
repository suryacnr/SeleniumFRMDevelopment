package TestCases;

import BaseClass.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.PIMPage;

public class TS_007 extends BaseClass {
    @BeforeTest
    public void setValues() {

        testName = "TS_007";
        testDescription = "Verify whether the error message is displayed as'Required', when user leave the Lastname field empty ";
        category = "Functional";
        testAuthor = "Surya";

    }

    @Test
    public void tS_007(){
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
        PIMPage page1 =new PIMPage(driver,test);
        page1.ClickPIM();
        page1.ClickAddEmployee();
        page1.pageLoad(50);
        page1.ClickSave();
        var actualResult=page1.CaptureLastNameError();
        var expectedResult =page1.testdataload("TS_006","ErrorMessage");

        if(actualResult.contains(expectedResult)){

            reportStep("The error message is displayed as'Required' in Lastname","pass",driver);
        }else{
            reportStep("The error message is not displayed as'Required' in lastname","fail",driver);

        }
        Assert.assertEquals(expectedResult,actualResult);





    }
}
