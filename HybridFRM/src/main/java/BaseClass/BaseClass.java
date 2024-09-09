package BaseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import utilities.ExtentReport;
import utilities.ReadProperites;


public class BaseClass extends ExtentReport {
	ReadProperites read =new ReadProperites();
	
	public String username =read.getUsername();
	public String Password =read.getPassword();
	public String baseUrl =read.getbaseUrl();
	public  RemoteWebDriver driver;
	public static Logger logger;

	@BeforeSuite
	public void Setup(){
		startReport();
		//report();

	}
	@BeforeClass
	public void rep(){

	}
	
	@BeforeMethod
	public void setup() {
		try {
			report();
			logger =Logger.getLogger("HybridFRM");
			PropertyConfigurator.configure("Log4j.properties");
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Driver//chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver",read.getchromePath());
			driver = new FirefoxDriver();
			driver.get(ReadProperites.getbaseUrl());
			//reportStep("User enter the URL: "+ReadProperites.getbaseUrl(),"Info",driver);
			driver.manage().window().maximize();
			//reportStep("Browser maximized","Info",driver);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterSuite
	public void TearDown(){
		stopReport();
	}
	

}
