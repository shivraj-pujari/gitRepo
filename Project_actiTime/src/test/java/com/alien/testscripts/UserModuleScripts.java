package com.alien.testscripts;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.alien.driver.DriverScript;


public class UserModuleScripts extends DriverScript{
	/****************************************************
	 * Script Name		: TS_Loging_Logout()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean TS_Loging_Logout()
	{
		WebDriver oBrowser = null;
		Map<String, String> objData = null;
		String strStatus = null;
		try {
			test = extent.startTest("TS_Loging_Logout");
			objData = datatable.getDataFromExcel("TestData", "User_01", test);
			
			oBrowser = appInd.launchBrowser(objData.get("BrowserName"), test);
			if(oBrowser!=null) {
				strStatus+= appDep.navigateURL(oBrowser, appInd.readConfigData("URL"), test);
				strStatus+= appDep.loginToApp(oBrowser, objData.get("UN"), objData.get("PWD"), test);
				strStatus+= appDep.logoutFromApp(oBrowser, test);
				
				
				if(strStatus.contains("false"))
				{
					reports.writeResult(oBrowser, "Fail", "The test script 'TS_Loging_Logout()' was failed.", test);
					return false;
				}else {
					reports.writeResult(oBrowser, "Pass", "The test script 'TS_Loging_Logout()' was Passed.", test);
					return true;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to launch the browser", test);
				return false;
			}
			
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exceptoin in the script 'TS_Loging_Logout()'. "+e.getMessage(), test);
			return false;
		}
		finally {
			appInd.closeBrowser(oBrowser, test);
			reports.endExtentReport(test);
		}
	}
	
	
	
	
	/****************************************************
	 * Script Name		: TS_Create_DeleteUser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean TS_Create_DeleteUser()
	{
		WebDriver oBrowser = null;
		Map<String, String> objData = null;
		String userName = null;
		String strStatus = null;
		try {
			test = extent.startTest("TS_Create_DeleteUser");
			objData = datatable.getDataFromExcel("TestData", "User_02", test);
			
			oBrowser = appInd.launchBrowser(objData.get("BrowserName"), test);
			if(oBrowser!=null) {
				strStatus+= appDep.navigateURL(oBrowser, appInd.readConfigData("URL"), test);
				strStatus+= appDep.loginToApp(oBrowser, objData.get("UN"), objData.get("PWD"), test);
				userName = userMethods.createUser(oBrowser, objData, test);
				strStatus+= userMethods.deleteUser(oBrowser, userName, test);
				strStatus+= appDep.logoutFromApp(oBrowser, test);
				
				if(strStatus.contains("false"))
				{
					reports.writeResult(oBrowser, "Fail", "The test script 'TS_Create_DeleteUser()' was failed.", test);
					return false;
				}else {
					reports.writeResult(oBrowser, "Pass", "The test script 'TS_Create_DeleteUser()' was Passed.", test);
					return true;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to launch the browser", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exceptoin in the script 'TS_Create_DeleteUser()'. "+e.getMessage(), test);
			return false;
		}
		finally {
			appInd.closeBrowser(oBrowser, test);
			reports.endExtentReport(test);
		}
	}
	
	
	
	
	/****************************************************
	 * Script Name		: TS_CreateUser_LoginWithNewUser_DeleteUser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean TS_CreateUser_LoginWithNewUser_DeleteUser()
	{
		WebDriver oBrowser1 = null;
		WebDriver oBrowser2 = null;
		Map<String, String> objData = null;
		String userName = null;
		String strStatus = null;
		try {
			test = extent.startTest("TS_CreateUser_LoginWithNewUser_DeleteUser");
			objData = datatable.getDataFromExcel("TestData", "User_03_01", test);
			
			oBrowser1 = appInd.launchBrowser(objData.get("BrowserName"), test);
			if(oBrowser1!=null) {
				strStatus+= appDep.navigateURL(oBrowser1, appInd.readConfigData("URL"), test);
				strStatus+= appDep.loginToApp(oBrowser1, objData.get("UN"), objData.get("PWD"), test);
				userName = userMethods.createUser(oBrowser1, objData, test);
				
				
				//Login with newly created user.
				objData = datatable.getDataFromExcel("TestData", "User_03_02", test);
				oBrowser2 = appInd.launchBrowser(objData.get("BrowserName"), test);
				if(oBrowser2!=null) {
					strStatus+= appDep.navigateURL(oBrowser2, appInd.readConfigData("URL"), test);
					boolean blnRes = appDep.loginToApp(oBrowser2, objData.get("UN"), objData.get("PWD"), test);
					
					if(blnRes) {
						reports.writeResult(oBrowser2, "Pass", "Login with new user '"+objData.get("UN")+"' was successful", test);
						strStatus+= true;
					}
					else {
						reports.writeResult(oBrowser2, "Fail", "Failed to login with new user '"+objData.get("UN")+"'", test);
						strStatus+= false;
					}
					oBrowser2.close();
				}else {
					reports.writeResult(oBrowser2, "Fail", "Failed to launch the browser", test);
					return false;
				}
				
				
				strStatus+= userMethods.deleteUser(oBrowser1, userName, test);
				strStatus+= appDep.logoutFromApp(oBrowser1, test);
				
				if(strStatus.contains("false"))
				{
					reports.writeResult(oBrowser1, "Fail", "The test script 'TS_CreateUser_LoginWithNewUser_DeleteUser()' was failed.", test);
					return false;
				}else {
					reports.writeResult(oBrowser1, "Pass", "The test script 'TS_CreateUser_LoginWithNewUser_DeleteUser()' was passed.", test);
					return true;
				}
			}else {
				reports.writeResult(oBrowser1, "Fail", "Failed to launch the browser", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser1, "Exception", "Exceptoin in the script 'TS_CreateUser_LoginWithNewUser_DeleteUser()'. "+e.getMessage(), test);
			return false;
		}
		finally {
			appInd.closeBrowser(oBrowser1, test);
			reports.endExtentReport(test);
		}
	}
	
}
