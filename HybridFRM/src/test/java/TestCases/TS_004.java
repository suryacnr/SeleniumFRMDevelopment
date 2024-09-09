package TestCases;

import BaseClass.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.PIMPage;

import static pageObjects.LoginPage.PersonalDetails;

public class TS_004 extends BaseClass {
    @BeforeTest
    public void setValues() {

        testName = "TS_004";
        testDescription = "Verify whether user can create new employee by entering mandatory details";
        category = "Functional";
        testAuthor = "Surya";

    }

    @Test
    public void tS_004(){
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
        page1.EnterFirstname();
        page1.EnterLastName();
        page1.ClickSave();

        //page1.waitForAjaxToComplete(60);
        var result= page.isElementPresent(PersonalDetails);
        if(result){

            reportStep("User has created the new User with mandatory details","pass",driver);
        }else{
            reportStep("User can't created the new User mandatory details","fail",driver);

        }
        Assert.assertTrue(result);





    }
}
