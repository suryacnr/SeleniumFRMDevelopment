package utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import BaseClass.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commanutilites extends BaseClass {
	
	private final RemoteWebDriver driver;
	public WebDriverWait wait;

public Commanutilites(RemoteWebDriver driver, ExtentTest test){
	this.driver =driver;
	this.test=test;

}

	public void waitUntilElementIsDisplayed(int waitTime, By locator) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public void Click(By locator, int waitTime,String msg) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator).click();
			reportStep("User clicked on "+msg,"Info",driver);
		} catch (Exception e) {
			reportStep("User can't click on "+msg+" Because of the execption "+e,"fail",driver);
			throw new RuntimeException(e);
		}

	}
	public void EnterText(By locator, String text, String labelName,int waitTime) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			//locateElement(locator).sendKeys(text);
			Actions act =  new Actions(driver);
			act.sendKeys(locateElement(locator),text).perform();
			reportStep("User Entered the "+text+" in the "+labelName,"Info",driver);
		} catch (Exception e) {
			reportStep("User can't enter the "+text+" in the "+labelName+" Because of the execption "+e,"fail",driver);
		}


	}
	public void SelectFromList(By locator, String text, String labelName,int waitTime){
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			List<WebElement> elements=locateElements(locator);
			for(WebElement element :elements){
				if(element.getText().contains(text)){
					element.click();
					break;
				}
			}
			reportStep("User Selected the "+text+"in the "+labelName,"Info",driver);
		} catch (Exception e) {
			reportStep("User can't select the "+text+"in the "+labelName+" Because of the execption "+e,"fail",driver);
		}

	}
	private WebElement locateElement(By locator){
		return driver.findElement(locator);
	}
	private List<WebElement> locateElements(By locator){
		return driver.findElements(locator);
	}
	public String getCurrentURL(){
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public String getTitle(){
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void waitUntilElementIsClickable(int waittime, By locator) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waittime));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public String CaptureText(By locator,  String labelName,int waitTime){

	try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			 String text =locateElement(locator).getText();
			reportStep("User Captured the "+text+" from the "+labelName,"Info",driver);
			return text;
		} catch (Exception e) {
			reportStep("User can't capture the text from the "+labelName+" Because of the execption "+e,"fail",driver);
		}

    return null;
	}
	public  void waitForAjaxToComplete( int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0").equals(true);
				}
			});
		} catch (Exception e) {
			System.out.println("Exception while waiting for AJAX to complete: " + e.getMessage());
		}
	}
	public void pageLoad(int sec){
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}
	
	public  void firstDroppown(By element,String visabletext) {
		WebElement stores= driver.findElement(element);
		stores.click();
		Select action =new Select(stores);
		action.selectByVisibleText(visabletext);
		
	}

	
	public  boolean isElementPresent(By element) {
		boolean bool =locateElement(element).isDisplayed();
		return bool;
		
	}
	public  boolean isElementNotPresent(By element) {
		try {
			WebElement webElement = driver.findElement(element);
			return !webElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return true;
		} catch (Exception e) {
			return true;
			//throw new RuntimeException("An unexpected error occurred while checking element presence", e);
		}


	}
	
	public  String gettextboxvalue(By element) {
		//By xpath = By.xpath("//id[text()=''");
			if(isElementPresent(element)) {
				WebElement elements =driver.findElement(element);
				String attribute = elements.getAttribute("value");
				System.out.println("Captured text: "+ attribute);
				return attribute;
			}
			return null;
		
		
	}
	public  void dropdownsingforloop(By element, String dropdownvalue) {
		List<WebElement> drop =driver.findElements(element);
		
		
		for(int i =0; i < drop.size(); i++) {
			String single =drop.get(i).getText(); 
			if(single.equals(dropdownvalue)) {
				drop.get(i).click();
				System.out.println("Successfully clicked on"+dropdownvalue );
				break;
			}else if(single.equals(dropdownvalue)) {
				
				
			}
		}

		
		
		
}


	public String testdataload(String testscriptID, String testdataname)  {
        String expected_value = "";
		ReadProperites read = new ReadProperites();
		try(XSSFWorkbook workbook = new XSSFWorkbook(read.getTestDataPath());) {

			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int lastRow = sheet.getLastRowNum();
			//System.out.println(lastRow+ " Before loop");
			for (int i = 0; i <= lastRow; i++) {
				var cell = sheet.getRow(i).getCell(0).toString();
				//System.out.println(cell+ " Number of Rows in the First "+lastRow);
				 if(testscriptID.equals(cell)){
					var lastCell= sheet.getRow(i).getLastCellNum();
					for(int j=0;j<=lastCell;j++){
						String column =sheet.getRow(0).getCell(j).toString();
						//System.out.println(column+ " Number of Cloums in the Selected Row "+lastCell);
						if(testdataname.equals(column)){
							expected_value=sheet.getRow(i).getCell(j).toString();
							//System.out.println("Expected "+expected_value);
							break;
						}
					}
				 }
			}
			
			return expected_value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
