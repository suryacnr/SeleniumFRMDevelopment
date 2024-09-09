package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Commanutilites;

public class HealthInsurancePage extends Commanutilites {
	private final RemoteWebDriver driver;
	public Commanutilites utility;
	//public RemoteWebDriver driver;

	public HealthInsurancePage(RemoteWebDriver driver, ExtentTest test){
        super(driver,test);
        this.driver =driver;
		this.test=test;
	    PageFactory.initElements(driver, this);

		
	}
	public static final By ReAssure= By.xpath("//span[text()='ReAssure 2.0']");
	public By You= By.xpath("//span[text()='You']");
	public  By Contine= By.xpath("//span[normalize-space()='Continue']");
	public  By YourAge= By.xpath("//label[text()='Your age']//parent::div//following-sibling::div/div/input");
	public  By pineCode= By.xpath("//label[text()='Where do you live?']//parent::div//following-sibling::div/div/input");
	public  By ReassurePlanType= By.xpath("//label[text()='Reassure Plan Type']//parent::div//following-sibling::div/div");
	public  By ReassurePlanTypeValues= By.xpath("//ul//li");
	public  By CalculatePremium= By.xpath("//span[normalize-space()='Calculate Premium']");
	public  By PremiumAmount= By.xpath("(//div[@class='jss320'])[1]");
	public  By BasePremium= By.xpath("//p[normalize-space()='Base Premium']//parent::div//following-sibling::div/p");
	public  By Totalpremium= By.xpath("//p[normalize-space()='Total premium']//parent::div//following-sibling::div/p");
	public  By GST= By.xpath("//p[normalize-space()='GST (18%)']//parent::div//following-sibling::div/p");

	public void ClickReAssure() {
		Click(ReAssure,40,"ReAssured 2.0");

	}
	public void ClickYou() {
		Click(You,40,"You");

	}
	public void ClickContine() {
		Click(Contine,40,"Continue button");

	}

	public void EnterYourAge(){
		EnterText(YourAge,testdataload("TS_001","Your Age"),"YourAge",40);

	}
	public void EnterpineCode(){

			EnterText(pineCode,testdataload("TS_001","Pine Code"),"Pine Code",40);

	}
	public void SelectReassurePlanType() {
		Click(ReassurePlanType,40,"Reassure Plan Type");
		SelectFromList(ReassurePlanTypeValues,testdataload("TS_001","ReassurePlanType"),"Reassure Plan Type Values",50);

	}
	public void ClickCoverage(){
		Click(PremiumAmount,40,"Coverage amount");

	}
	public void ClickCalculatePremium(){
		Click(CalculatePremium,40,"Calculate Premium");
		//waitForAjaxToComplete(60);

	}
	public double CaptureBasePremium(){
		try {
			String text = CaptureText(BasePremium,"Base Premium",50);
			String regex = "[₹,]";
			String baseText =text.replaceAll(regex, "");
			double basePremiumValue =Double.parseDouble(baseText);
			return basePremiumValue;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public double CaptureTotalpremium(){
		try {
			String text = CaptureText(Totalpremium,"Total Premium",50);
			String regex = "[₹,]";
			String totalPremium= text.replaceAll(regex, "");
			String GSTText = CaptureText(GST,"GST",50);
			String GST= GSTText.replaceAll(regex, "");
			double totalPremiumValue=Double.parseDouble(totalPremium);
			double GSTValue=Double.parseDouble(GST);
			double ridersDectedPremium =totalPremiumValue-GSTValue;
			return ridersDectedPremium;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




//	@FindBy(xpath="//input[@name='password']")
//	WebElement pass;
//	@FindBy(xpath="//input[@name='btnLogin']")
//	WebElement login;

}
