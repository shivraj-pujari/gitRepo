package com.alien.locators;

public interface ObjectLocators {
	final String obj_UserName_Edit = "//input[@placeholder='Username']";
	final String obj_Password_Edit = "//input[@name='pwd']";
	final String obj_Login_Btn = "//a[@id='loginButton']";
	final String obj_defaultLogo_Img = "//img[contains(@src, '/default-logo.png')]";
	final String obj_Shortcut_Wnd = "//div[@style='display: block;']";
	final String obj_Shortcut_Close_btn = "//div[@id='gettingStartedShortcutsMenuCloseId']";
	final String obj_Users_Menu = "//div[text()='USERS']";
	final String obj_AddUser_Btn = "//div[text()='Add User']";
	final String obj_AddUser_Wnd = "//span[text()='Add User']";
	final String obj_FirstName_Edit = "//input[@name='firstName']";
	final String obj_LastName_Edit = "//input[@name='lastName']";
	final String obj_Email_Edit = "//input[@name='email']";
	final String obj_User_UserName_Edit = "//input[@name='username']";
	final String obj_User_Password_Edit = "//input[@name='password']";
	final String obj_User_RetypePWD_Edit = "//input[@name='passwordCopy']";
	final String obj_CreateUser_Btn = "//span[text()='Create User']";
	final String obj_DeleteUser_Btn = "//button[contains(text(), 'Delete User')]";
	final String obj_Logout_Btn = "//a[@id='logoutLink']";
	final String obj_LoginHeader_Label = "//td[@id='headerContainer'][text()='Please identify yourself']";
	final String obj_StartExploringActiTime_Btn = "//span[text()='Start exploring actiTIME']";
}
