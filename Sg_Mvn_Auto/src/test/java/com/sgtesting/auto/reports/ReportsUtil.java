
package com.sgtesting.auto.reports;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sgtesting.auto.driver.DriverScript;

public class ReportsUtil extends DriverScript {
	/*****************************************
	 * Method Name	: 
	 * Purpose		:
	 * Arguments	:
	 * Return Type	:
	 * Author		:
	 ****************************************/
	public ExtentReports startReport(String fileName, String testCaseID, String buildName)
	{
		String strPath = null;
		File resLocation = null;
		File resScreenshot = null;
		try {
			strPath = System.getProperty("user.dir")+"\\Results\\"+buildName;
			
			resLocation = new File(strPath + "\\" +testCaseID);
			if(!resLocation.exists()) {
				resLocation.mkdir();
			}
			
			resScreenshot = new File(resLocation +"\\screenshot");
			if(!resScreenshot.exists()) {
				resScreenshot.mkdir();
			}
			
			extent = new ExtentReports(resLocation +"\\"+ fileName +appInd.getDateTime("ddMMyyyy_hhmmss")+".html", true);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			//extent.addSystemInfo("Environment", appInd.readConfigData("Environment"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
		}catch(Exception e)
		{
			System.out.println("Exception in startTest() method. "+e.getMessage());
			return null;
		}
		finally {
			strPath = null;
			resLocation = null;
			resScreenshot = null;
		}
	}

	/********************************************************
	 * Method Name : endExtentReport() Purpose :
	 * 
	 * 
	 ********************************************************/
	public void endExtentReport(ExtentTest test) {
		try {
			extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			System.out.println("Exception in endExtentReport() method. " + e);
		}
	}

	/********************************************************
	 * Method Name : captureScreenshot() Purpose :
	 * 
	 * 
	 ********************************************************/
	public String captureScreenshot(WebDriver oBrowser) {
		File objSource = null;
		String strDestination = null;
		File objDestination = null;
		String screnshotLocation = null;
		try {
			strDestination = screnshotLocation + "\\" + "ScreenShot_" + appInd.getDateTime("ddMMYYYY_hhmmss") + ".png";
			TakesScreenshot ts = (TakesScreenshot) oBrowser;
			objSource = ts.getScreenshotAs(OutputType.FILE);
			objDestination = new File(strDestination);
			FileHandler.copy(objSource, objDestination);
			return strDestination;
		} catch (Exception e) {
			System.out.println("Exception in captureScreenshot() method. " + e);
			return null;
		} finally {
			objSource = null;
			objDestination = null;
		}
	}

	/********************************************************
	 * Method Name : writeResult() Purpose :
	 * 
	 * 
	 ********************************************************/
	public void writeResult(WebDriver oBrowser, String status, String strDescription, ExtentTest test,
			boolean blnScreenShotReqd) {
		try {
			if (blnScreenShotReqd) {
				switch (status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS,
							strDescription + " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "fail":
					test.log(LogStatus.FAIL,
							strDescription + " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "warning":
					test.log(LogStatus.WARNING,
							strDescription + " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "info":
					test.log(LogStatus.INFO, strDescription);
					break;
				case "exception":
					test.log(LogStatus.FATAL,
							strDescription + " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "screenshot":
					test.log(LogStatus.PASS,
							strDescription + " : " + test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				default:
					System.out.println(
							"Invalid status '" + status + "' for the result. please provide the appropriate status");
				}
			} else {
				switch (status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS, strDescription);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strDescription);
					break;
				case "warning":
					test.log(LogStatus.WARNING, strDescription);
					break;
				case "info":
					test.log(LogStatus.INFO, strDescription);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strDescription);
					break;
				case "screenshot":
					test.log(LogStatus.PASS, strDescription);
					break;
				default:
					System.out.println(
							"Invalid status '" + status + "' for the result. please provide the appropriate status");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in writeResult() method. " + e);
		}
	}

}