package com.alien.methods;

import org.openqa.selenium.WebDriver;
import com.alien.driver.DriverScript;
import com.alien.locators.ObjectLocators;
import com.relevantcodes.extentreports.ExtentTest;

public class AppDependentMethods extends DriverScript implements ObjectLocators{
	
	/****************************************************
	 * Method Name		: navigateURL()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean navigateURL(WebDriver oBrowser, String URL, ExtentTest test)
	{
		try {
			oBrowser.navigate().to(URL);
			appInd.waitFor(oBrowser, obj_LoginHeader_Label, "Text", "Please identify yourself", 5);
			
			if(appInd.compareValue(oBrowser, oBrowser.getTitle(), "actiTIME - Login", test))
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'navigateURL()' method. "+ e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: navigateURL()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean loginToApp(WebDriver oBrowser, String UN, String PWD, ExtentTest test)
	{
		String strStatus = null;
		try {
			strStatus+= appInd.setObject(oBrowser, obj_UserName_Edit, UN, test);
			strStatus+= appInd.setObject(oBrowser, obj_Password_Edit, PWD, test);
			strStatus+= appInd.clickObject(oBrowser, obj_Login_Btn, test);
			//appInd.waitFor(oBrowser, obj_Login_Btn, "invisibility", "", 5);
			
			//Validate for new user login
			if(appInd.verifyOptionalElement(oBrowser, obj_StartExploringActiTime_Btn, test)) {
				strStatus+= appInd.clickObject(oBrowser, obj_StartExploringActiTime_Btn, test);
			}
			
			
			strStatus+= appInd.verifyElementExist(oBrowser, obj_defaultLogo_Img, test);
						
			//short cut a optional element hence better use findElements()		
			if(appInd.verifyOptionalElement(oBrowser, obj_Shortcut_Wnd, test))
			{
				strStatus+= appInd.clickObject(oBrowser, obj_Shortcut_Close_btn, test);
			}
			
			if(strStatus.contains("false")) {
				reports.writeResult(oBrowser, "Fail", "Failed to login to actiTime.", test);
				return false;
			}else {
				reports.writeResult(oBrowser, "Pass", "Login to actiTime is successful", test);
				reports.writeResult(oBrowser, "screenshot", "Login successful", test);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'loginToApp()' method. "+ e.getMessage(), test);
			return false;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: logoutFromApp()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean logoutFromApp(WebDriver oBrowser, ExtentTest test)
	{
		String strStatus = null;
		try {
			strStatus+= appInd.clickObject(oBrowser, obj_Logout_Btn, test);
			appInd.waitFor(oBrowser, obj_LoginHeader_Label, "Text", "Please identify yourself", 5);
			
			strStatus+= appInd.verifyText(oBrowser, obj_LoginHeader_Label, "Text", "Please identify yourself", test);
			
			
			if(strStatus.contains("false"))
			{
				reports.writeResult(oBrowser, "Fail", "Failed to logout from actiTime", test);
				return false;
			}else {
				reports.writeResult(oBrowser, "Pass", "Logout from actiTime is successful", test);
				reports.writeResult(oBrowser, "screenshot", "Logout successful", test);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'logoutFromApp()' method. "+ e.getMessage(), test);
			return false;
		}
	}
}
