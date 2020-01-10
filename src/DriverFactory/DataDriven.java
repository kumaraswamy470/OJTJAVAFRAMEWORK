package DriverFactory;


import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;
public class DataDriven {
WebDriver driver;
@BeforeTest
public void setUp()throws Throwable
{
	String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
	Reporter.log(launch, true);
	String login=ERP_Functions.verifyLogin("admin", "master");
}
@org.testng.annotations.Test
public void supplierCreation()throws Throwable
{
	//accessing excel util methods
	ExcelFileUtil xl=new ExcelFileUtil();
	//row count
	int rc=xl.rowCount("supplier");
	int cc=xl.colCount("supplier");
	Reporter.log("no.of rows are::"+rc+"   "+"no.of columns are::"+cc, true);
	for(int i=1;i<=rc;i++)
	{
		String sname=xl.getCellData("supplier", i, 0);
		String address=xl.getCellData("supplier", i, 1);
		String city=xl.getCellData("supplier", i, 2);
		String country=xl.getCellData("supplier", i, 3);
		String cperson=xl.getCellData("supplier", i, 4);
		String pnumber=xl.getCellData("supplier", i, 5);
		String mail=xl.getCellData("supplier", i, 6);
		String mnumber=xl.getCellData("supplier", i, 7);
		String note=xl.getCellData("supplier", i, 8);
		//call supplier creation method
		String Results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, note);
		Reporter.log(Results, true);
		xl.setCellData("supplier", i, 9, Results);
	}
}
	
@AfterTest
public void tearDown()
{
	ERP_Functions.verifylogout();
}
	
	
	
	
	
}
