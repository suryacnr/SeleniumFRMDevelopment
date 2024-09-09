package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utilities.Commanutilites;

public class LoginPage extends Commanutilites {
    private final RemoteWebDriver driver;
    public Commanutilites utility;
    //public RemoteWebDriver driver;

    public LoginPage(RemoteWebDriver driver, ExtentTest test){
        super(driver,test);
        this.driver =driver;
        this.test=test;
        PageFactory.initElements(driver, this);


    }

    public static final By Username= By.xpath("//input[@placeholder='Username']");
    public  static final By Password= By.xpath("//input[@placeholder='Password']");
    public static final  By LoginButton= By.xpath("//button[normalize-space()='Login']");
    public static final  By PersonalDetails= By.xpath("//h6[normalize-space()='Personal Details']");


    public void ClickLoginButton() {
        Click(LoginButton,40,"Login button");

    }


    public void EnterUsername(){
        EnterText(Username,testdataload("TS_001","Username"),"Username",40);

    }
    public void EnterUsername(String testdata){
        EnterText(Username,testdata,"Username",40);

    }
    public void EnterPassword(){

        EnterText(Password,testdataload("TS_001","Pwd"),"Password",40);

    }
    public void EnterPassword(String testdata){

        EnterText(Password,testdata,"Password",40);

    }



}
