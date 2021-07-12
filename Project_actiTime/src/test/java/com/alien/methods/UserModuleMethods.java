package com.alien.methods;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.alien.driver.DriverScript;
import com.alien.locators.ObjectLocators;
import com.relevantcodes.extentreports.ExtentTest;

public class UserModuleMethods extends DriverScript implements ObjectLocators{
	/****************************************************
	 * Method Name		: createUser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public String createUser(WebDriver oBrowser, Map<String, String> data, ExtentTest test)
	{
		String userName = null;
		String strStatus = null;
		try {
			strStatus+= appInd.clickObject(oBrowser, obj_Users_Menu, test);
			appInd.waitFor(oBrowser, obj_AddUser_Btn, "visibility", "", 5);
			strStatus+= appInd.clickObject(oBrowser, obj_AddUser_Btn, test);
			appInd.waitFor(oBrowser, obj_FirstName_Edit, "visibility", "", 5);
			
			if(appInd.verifyElementExist(oBrowser, obj_AddUser_Wnd, test)) {
				
				strStatus+= appInd.setObject(oBrowser, obj_FirstName_Edit, data.get("FN"), test);
				strStatus+= appInd.setObject(oBrowser, obj_LastName_Edit, data.get("LN"), test);
				strStatus+= appInd.setObject(oBrowser, obj_Email_Edit, data.get("EMAIL"), test);
				strStatus+= appInd.setObject(oBrowser, obj_User_UserName_Edit, data.get("UserName"), test);
				strStatus+= appInd.setObject(oBrowser, obj_User_Password_Edit, data.get("Password"), test);
				strStatus+= appInd.setObject(oBrowser, obj_User_RetypePWD_Edit, data.get("RetypePWD"), test);
							
				strStatus+= appInd.clickObject(oBrowser, obj_CreateUser_Btn, test);
								
				userName = data.get("LN")+", "+ data.get("FN");
				String userXpath = "//div[@class='name']/span[text()="+"'"+userName+"'"+"]";
				appInd.waitFor(oBrowser, By.xpath(userXpath), "Text", userName, 10);
				
				strStatus+= appInd.verifyElementExist(oBrowser, By.xpath(userXpath), test);
								
				if(strStatus.contains("false")) {
					reports.writeResult(oBrowser, "Fail", "Failed to create the new user in actiTime", test);
					return null;
				}else {
					reports.writeResult(oBrowser, "Pass", "The new user is created successful", test);
					reports.writeResult(oBrowser, "screenshot", "user created successful", test);
					return userName;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to open the Add User screen", test);
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'createUser()' method. "+ e.getMessage(), test);
			return null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: createUser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean deleteUser(WebDriver oBrowser, String userName, ExtentTest test)
	{
		String strStatus = null;
		try {
			String userXpath = "//div[@class='name']/span[text()="+"'"+userName+"'"+"]";
			strStatus+= appInd.clickObject(oBrowser, By.xpath(userXpath), test);
			appInd.waitFor(oBrowser, obj_DeleteUser_Btn, "clickable", "", 10);
			reports.writeResult(oBrowser, "screenshot", "Before deleting the user", test);
			
			if(appInd.verifyElementExist(oBrowser, obj_DeleteUser_Btn, test)) {
				strStatus+= appInd.clickObject(oBrowser, obj_DeleteUser_Btn, test);
				Thread.sleep(1000);
				oBrowser.switchTo().alert().accept();
				Thread.sleep(1000);
				
				strStatus+= appInd.verifyElementNotExist(oBrowser, By.xpath(userXpath), test);
								
				if(strStatus.contains("false")) {
					reports.writeResult(oBrowser, "Fail", "Failed to delete the user in actiTime", test);
					return false;
				}else {
					reports.writeResult(oBrowser, "Pass", "User is deleted in actiTime successful", test);
					reports.writeResult(oBrowser, "screenshot", "after deleting the user", test);
					return true;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to find the delete user window", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'deleteUser()' method. "+ e.getMessage(), test);
			return false;
		}
	}
}
