package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class XmlUtilities {
	WebDriver driver;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//add Test annotaion @Test(DataProvider ="logindetails)
//	@Test
//	public void login(String user, String pass, String validation) {
//		driver.get("");
//		WebElement sendKe = driver.findElement(By.xpath("//td"));
//		sendKe.clear();
//		sendKe.sendKeys(user);
//		WebElement pass1 = driver.findElement(By.xpath("//td"));
//		pass1.clear();
//		pass1.sendKeys(user);
//		driver.findElement(By.xpath("login")).click();
//		String expected_value ="expected tiltal";
//		String actual_value =driver.getTitle();
//		if(validation.equals("valid")) {
//			if(actual_value.equalsIgnoreCase(expected_value)) {
//				driver.findElement(By.xpath("logout")).click();
//				System.out.println("Test Step pass");
//			}else {
//				System.out.println("Test Step Fail");
//			}
//		}else if (validation.equals("invalid")){
//			if(!actual_value.equalsIgnoreCase(expected_value)) {
//				
//				System.out.println("Test Step pass");
//			}else {
//				driver.findElement(By.xpath("logout")).click();
//				System.out.println("Test Step Fail");
//			}
//			
//			
//		}
//		
//		
//	}
//	
//	
//	// add dataprovider annotaion @DataProvider(name="logindetails")
//	public Object [][] getData() throws IOException{
//		//Hardcorded data
////		String [][] logindetails = {
////				{"admin","admin","valid"},
////				{"admin1","admin","invalid"},
////				{"admin2","admin","invalid"},
////				{"admin3","admin","invalid"},
////		};
//		//get data from excel
//		String Xls =System.getProperty("User.dir")+"\\Data\\testdata.xlsx";
//		int totalrows=getRowCount(Xls,"Sheet1");
//		int totalcolms=getCellCount(Xls,"Sheet1",1);
//		
//		Object logindetails[][]=new Object[totalrows][totalcolms]; 
//		//must be equals to total rows&colms size The same number mst be in excel and 2Dimanstion arrays
//		for (int i=1; i<=totalrows; i++) //1
//		{
//				
//			for(int j =0; i<=totalcolms; j++)//0
//				{
//				logindetails[i-1][j]=getCellData(Xls,"Sheet1",i,j); // in sheet there will be header element so we need to ignore in 2Dimanstion array so we are using i-1
//				
//				}
//		}
//		
//		
//		return logindetails;
//		
//	}
//	
//	//How to read from xml file
//
//	public static void Readfile() throws Exception {
//
//		String excel = System.getProperty("user.dir")+"\\Data\\Poitest.xlsx";
//		FileInputStream stream =new FileInputStream(excel);
//		XSSFWorkbook book = new XSSFWorkbook(stream);
//		//XSSFSheet sheet =book.getSheet("Sheet1");
//		XSSFSheet sheet =book.getSheetAt(0);
//		int rows = sheet.getLastRowNum();
//		int cols = sheet.getRow(1).getLastCellNum();
		//Using for loop
//		for(int i=0;i<=rows;i++) {
//			XSSFRow r= sheet.getRow(i);
//			for(int j=0;j<=cols;j++) {
//				XSSFCell cell =r.getCell(j);
//				switch(cell.getCellType()) {
//				case STRING :System.out.print(cell.getStringCellValue());
//				break;
//				case NUMERIC:System.out.print(cell.getNumericCellValue());
//				break;
//				case BOOLEAN: System.out.print(cell.getBooleanCellValue());
//				}
//				System.out.print(" | ");
//				
//			}
//			System.out.println();
//		}
//		
//	
	////USING Iterator method
//		Iterator iterator =sheet.iterator();
//		while(iterator.hasNext()) {  
//			XSSFRow row = (XSSFRow) iterator.next();
//			Iterator celliterator =row.cellIterator();
//			while(celliterator.hasNext()) {
//				XSSFCell cells = (XSSFCell) celliterator.next();
//				switch(cells.getCellType()) {
//				case STRING :System.out.print(cells.getStringCellValue());
//				break;
//				case NUMERIC:System.out.print(cells.getNumericCellValue());
//				break;
//				case BOOLEAN: System.out.print(cells.getBooleanCellValue());
//				}
//				System.out.print(" | ");
//		} 
//			System.out.println();
//			
//	}
//	
//
//	}
//	
//	public static void writefile() throws Exception {
//		
//		XSSFWorkbook book = new XSSFWorkbook();
//		//XSSFSheet sheet =book.getSheet("Sheet1");
//		XSSFSheet sheet =book.createSheet("Shipment ");
//		 Object employee[][]= {{"EmpId","JOb", "name"},
//				 			   {101,"QA","Xyz"},
//				 			   {102,"QA","abc"},
//				 			  {103,"QA","abc"},
//		 };
//		//using For loop
//		/* int rows=employee.length;
//		 int colms=employee[0].length;
//		 for(int r=0;r<=rows;r++) {
//			XSSFRow row= sheet.createRow(r);
//			 for(int c=0;c<=colms;c++) {
//				XSSFCell cell= row.createCell(c);
//				Object value=employee[r][c];
//				if(value instanceof String) {
//					cell.setCellValue((String)value);
//				}
//				if(value instanceof Integer) {
//					cell.setCellValue((Integer)value);
//				}
//				if(value instanceof Boolean) {
//					cell.setCellValue((Boolean)value);
//				}
//			 }
//		 }*/
//		 
//		 //using for each loop
//		 int rows=0;
//		 
//		 for(Object r[]:employee) {
//			XSSFRow row= sheet.createRow(rows++);//row++ is poste incremant first row will created and then second time it will incremant
//			int colm =0;
//			 for(Object value:r) {
//				XSSFCell cell= row.createCell(colm);
//				if(value instanceof String) {
//					cell.setCellValue((String)value);
//				}
//				if(value instanceof Integer) {
//					cell.setCellValue((Integer)value);
//				}
//				if(value instanceof Boolean) {
//					cell.setCellValue((Boolean)value);
//				}
//				 
//			 }
//			 
//		 }
//		 
//		 
//		 String Filepath= System.getProperty("user.dir")+"\\Data\\employee.xlsx";
//		 FileOutputStream output =new FileOutputStream(Filepath);
//		   book.write(output);
//		   output.close();
//		   
//		   System.out.println("Employee Sheet got created");
//		 
//	}
//	
}

