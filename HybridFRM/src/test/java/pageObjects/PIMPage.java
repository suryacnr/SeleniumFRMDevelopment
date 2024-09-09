package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Commanutilites;

public class PIMPage extends Commanutilites {
    private final RemoteWebDriver driver;
    public Faker fake;
    public Commanutilites utility;
    //public RemoteWebDriver driver;

    public PIMPage(RemoteWebDriver driver, ExtentTest test){
        super(driver,test);
        this.driver =driver;
        this.test=test;
        PageFactory.initElements(driver, this);


    }

    public static final By PIM = By.xpath("(//span[normalize-space()='PIM'])[1]");
    public  static final By AddEmployee= By.xpath("//a[normalize-space()='Add Employee']");
    public static final  By FirstName= By.xpath("//input[@placeholder='First Name']");
    public static final  By MiddleName= By.xpath("//input[@placeholder='Middle Name']");
    public static final  By LastName= By.xpath("//input[@placeholder='Last Name']");
    public static final  By Save= By.xpath("//button[normalize-space()='Save']");
    public static final  By FirstNameError= By.xpath("//div[@class='oxd-input-group']//div[1]/span[1]");
    public static final  By LastNameError= By.xpath("//div[@class='oxd-input-group']//div[1]/span[1]");


    public void ClickPIM() {
        Click(PIM,40,"PIM");

    }
    public void ClickAddEmployee() {
        Click(AddEmployee,40,"AddEmployee Tab");


    }
    public void ClickSave()  {
        try {
            Thread.sleep(8000);
            Click(Save,40,"Save Button");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public void EnterFirstname(){
        fake = new Faker();
        waitUntilElementIsDisplayed(60,FirstName);
        EnterText(FirstName,fake.name().firstName(),"Firstname",40);

    }
    public void EnterMiddleName(){
        EnterText(MiddleName,fake.name().nameWithMiddle(),"MiddleName",40);

    }
    public void EnterLastName(){

        EnterText(LastName,fake.name().lastName(),"LastName",40);

    }
    public String CaptureFirstNameError(){
       return  CaptureText(FirstNameError,"FirstNameError",50);
    }
    public String CaptureLastNameError(){
        return  CaptureText(LastNameError,"LastNameError",50);
    }

}
