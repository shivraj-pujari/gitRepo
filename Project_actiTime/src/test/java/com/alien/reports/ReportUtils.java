package com.alien.reports;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.alien.driver.DriverScript;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportUtils extends DriverScript{

	/****************************************************
	 * Method Name		: startExtentReport()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public ExtentReports startExtentReport(String resultFileName, String buildName)
	{
		String resultPath = null;
		File objResPath = null;
		File objScreenShot = null;
		File objArchive = null;
		File objResultFile = null;
		try {
			resultPath = System.getProperty("user.dir")+"\\Results\\"+buildName;
			objResPath = new File(resultPath);
			if(!objResPath.exists()) {
				objResPath.mkdirs();
			}
			
			screenshotLocation = resultPath+"\\screenshot";
			objScreenShot = new File(screenshotLocation);
			if(!objScreenShot.exists())
			{
				objScreenShot.mkdirs();
			}
			
			objArchive = new File(objResPath + "\\Archive");
			if(!objArchive.exists()) {
				objArchive.mkdir();
			}
			
			
			objResultFile = new File(objResPath + "\\" +resultFileName+ ".html");
			if(objResultFile.exists())
			{
				objResultFile.renameTo(new File(objArchive + "\\" + buildName + "_" +resultFileName + appInd.getDateTime("ddMMYYYY_hhmmss") + ".html"));				
			}
			
			extent = new ExtentReports(objResPath + "\\" + resultFileName + ".html", false);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			extent.addSystemInfo("Environment", appInd.readConfigData("Environment"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'startExtentReport()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			resultPath = null;
			objResPath = null;
			objScreenShot = null;
			objArchive = null;
			objResultFile = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: endExtentReport()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public void endExtentReport(ExtentTest test)
	{
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'startExtentReport()' method. "+e.getMessage());
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: getScreenshot()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public String getScreenshot(WebDriver oBrowser)
	{
		File objSource = null;
		String strDestination = null;
		File objDestination = null;
		try {
			strDestination = screenshotLocation +"\\" + "screenshot_" + appInd.getDateTime("ddMMYYYY_hhmmss")+".png";
			TakesScreenshot ts = (TakesScreenshot) oBrowser;
			objSource = ts.getScreenshotAs(OutputType.FILE);
			
			objDestination = new File(strDestination);
			
			FileHandler.copy(objSource, objDestination);
			
			return strDestination;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'getScreenshot()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			objSource = null;
			objDestination = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: writeResult()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public void writeResult(WebDriver oBrowser, String status, String strDescriptoin, ExtentTest test)
	{
		try {
			switch(status.toLowerCase())
			{
				case "pass":
					test.log(LogStatus.PASS, strDescriptoin);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strDescriptoin + " : " 
							+ test.addScreenCapture(reports.getScreenshot(oBrowser)));
					break;
				case "warning":
					test.log(LogStatus.WARNING, strDescriptoin);
					break;
				case "info":
					test.log(LogStatus.INFO, strDescriptoin);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strDescriptoin+ " : " 
							+ test.addScreenCapture(reports.getScreenshot(oBrowser)));
					break;
				case "screenshot":
					test.log(LogStatus.PASS, strDescriptoin + " : " 
							+ test.addScreenCapture(reports.getScreenshot(oBrowser)));
					break;
				default:
					System.out.println("Invalid result status '"+status+"'. Provide the appropriate status");
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'writeResult()' method. "+e.getMessage());
		}
	}
}
