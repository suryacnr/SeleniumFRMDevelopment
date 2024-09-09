package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//import ImplementationBase.WebImplementationBase;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class ExtentReport extends TestListenerAdapter {

	public  ExtentTest test;

	public WebDriverWait wait;
	//RemoteWebDriver driver;
	public static ExtentReports extent;
	public  String testName, testDescription, category, testAuthor, testNodes;
	public static String  executedBy;
	public static String folderName = "";
	public static String fileName = "result.html";
	public  PrintStream originalSysOut;
	public ByteArrayOutputStream outputStream;
	public  PrintStream customSysOut;
	public  List<String> logMessages;



	public static void startReport()  {
		try {
			//basepage = new WebImplementationBase(driver);
			//executedBy = basepage.getConfigurations("executedBy");
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			folderName = "reports/" + timeStamp;

			File folder = new File("./" + folderName);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			//basepage.readLocator(basepage.getConfigurations("locatorpath"), "Sheet1");

			extent = new ExtentReports();
			ExtentSparkReporter reporter = new ExtentSparkReporter("./" + folderName + "/" + fileName);
			//reporter.loadXMLConfig(basepage.getConfigurations("xmlconfigpath"));
			ReadProperites read =new ReadProperites();
			reporter.loadXMLConfig(read.getReportPath());
			extent.attachReporter(reporter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static String takeSnap(WebDriver driver) {
		String destination = null;
		try {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

//			Screenshot source = new AShot()
//					.shootingStrategy(ShootingStrategies.viewportPasting(100))
//					.takeScreenshot(driver);
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Save the screenshot to the specified file path

			//destination = System.getProperty("user.dir") +"/"+ folderName+"/Screenshot/" + data + ".png";
			String screenshotDir = System.getProperty("user.dir") + "/" + folderName + "/Screenshot/";
			File dir = new File(screenshotDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// Define the file path and save the screenshot
			destination = screenshotDir + timestamp + ".png";
			FileHandler.copy(source, new File(destination));
			//ImageIO.write(source.getImage(), "PNG", new File(destination));



		} catch (IOException e) {
			e.printStackTrace();
		}

		//return destination;

		return destination;
	}


	public  void report()  {
		try {
			test = extent.createTest(testName, testDescription);
			test.assignAuthor(testAuthor);
			test.assignCategory(category);
			//test.assignCategory(executedBy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	public  void reportStep(String msg, String status,WebDriver driver)  {

		try {
			System.out.println(msg);

			//String screenshot = takeSnap(driver);
			if (status.equalsIgnoreCase("pass")) {
				//test.log
				//node.log(Status.PASS,msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
				test.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(takeSnap(driver)).build());

			} else if (status.equalsIgnoreCase("fail")) {
				//node.log(Status.FAIL,msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
				[.length - 1]		test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(takeSnap(driver)).build());

			} else if (status.equalsIgnoreCase("info")) {
				//node.log(Status.INFO,msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
				test.info(msg, MediaEntityBuilder.createScreenCaptureFromPath(takeSnap(driver)).build());

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	public  void stopReport() {
		extent.flush();

	}

	private static class DualPrintStream extends PrintStream {
		private final PrintStream second;

		public DualPrintStream(PrintStream first, PrintStream second) {
			super(first);
			this.second = second;
		}

		@Override
		public void write(byte[] buf, int off, int len) {
			super.write(buf, off, len);
			second.write(buf, off, len);
		}

		@Override
		public void flush() {
			super.flush();
			second.flush();
		}

		@Override
		public void close() {
			super.close();
			second.close();
		}
	}






















	
//	public void onStart(ITestContext testContext) {
//		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//		String repName = timestamp+".html";
//		spark =new ExtentSparkReporter(System.getProperty("user.dir")+"./target/"+repName);
//		try {
//			spark.loadXMLConfig(System.getProperty("user.dir")+"/extent-report.xml");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		spark.config().setDocumentTitle("Automation Testing");
//		spark.config().setReportName("Functional Testing");
//		spark.config().setTheme(Theme.DARK);
//
//		report =new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("HostName", "LocalHost");
//		report.setSystemInfo("Port", "4444");
//		report.setSystemInfo("OS", "Windows10");
//
//
//	}
//
//	public void onTestSuccess(ITestResult tr) {
//		test =report.createTest(tr.getName());
//		test.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
//
//	}
//
//	public void onTestFailure(ITestResult tr) {
//		test =report.createTest(tr.getName());
//		test.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
//		String ScrenShot =System.getProperty("user.dir")+"\\ScreenShot\\"+tr.getName()+".png";
//		File finalDestination =new File(ScrenShot);
//		if(finalDestination.exists()) {
//			try {
//				test.fail("ScreenShot Below: "+test.addScreenCaptureFromPath(ScrenShot));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	public void onTestSkipped(ITestResult tr) {
//		test =report.createTest(tr.getName());
//		test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
//	}
//
//
//	public void onFinish(ITestContext testContext) {
//		report.flush();
//	}
//
//	@AfterMethod
//	public void teardown(ITestResult result) throws IOException {
//		if(result.getStatus()==ITestResult.FAILURE) {
//			test.log(Status.FAIL, "Test Case Failed"+result.getName());
//			test.log(Status.FAIL, "Test Case Failed"+result.getThrowable());
//			String ScrenShot =ExtentReport.getScreenshot(driver, result.getName());
//			test.addScreenCaptureFromPath(ScrenShot);
//			
//		}
//		else if(result.getStatus()==ITestResult.SKIP) {
//			test.log(Status.SKIP, "Test Case Skiped"+result.getName());
//		}else if(result.getStatus()==ITestResult.SUCCESS)
//		{
//			test.log(Status.PASS, "Test Case Passed"+result.getName());
//		}
//		
//	}
	
//	public static String getScreenshot(WebDriver driver,String ScreenShotName) throws IOException {
//		String data = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
//		TakesScreenshot  tc= (TakesScreenshot) driver;
//		File source =tc.getScreenshotAs(OutputType.FILE);
//		String destination = System.getProperty("user.dir"+"/ScreenShot/"+ScreenShotName+data+".png");
//		File finalDestination =new File(destination);
//		FileUtils.copyFile(source, finalDestination);
//		return destination;
//		
//		
//		
//	}
//	
	
}
