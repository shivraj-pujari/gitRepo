package com.alien.methods;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.alien.driver.DriverScript;
import com.relevantcodes.extentreports.ExtentTest;



public class AppIndependentMethods extends DriverScript{
	/****************************************************
	 * Method Name		: getDateTime()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public String getDateTime(String strFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(strFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'getDateTime()' method. "+e.getMessage(), test);
			return null;
		}
		finally
		{
			dt = null;
			sdf = null;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: readConfigData()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public String readConfigData(String strKey)
	{
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop = new Properties();
			
			prop.load(fin);
			
			return prop.getProperty(strKey);
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'readConfigData()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e)
			{
				System.out.println("Exception while executing 'readConfigData()' method. "+e.getMessage());
				return null;
			}
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: clickObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean clickObject(WebDriver oBrowser, By objBy, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful", test);
				oEles.get(0).click();
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: clickObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean clickObject(WebDriver oBrowser, String strObjectName, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The element '"+strObjectName+"' was clicked successful", test);
				oEles.get(0).click();
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+strObjectName+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: setObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean setObject(WebDriver oBrowser, By objBy, String strData, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful", test);
				oEles.get(0).sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: setObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean setObject(WebDriver oBrowser, String strObjectName, String strData, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The data '"+strData+"' was entered in the element '"+strObjectName+"' successful", test);
				oEles.get(0).sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+strObjectName+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: clearAndSetObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean clearAndSetObject(WebDriver oBrowser, By objBy, String strData, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful", test);
				oEles.get(0).clear();
				oEles.get(0).sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clearAndSetObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: clearAndSetObject()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean clearAndSetObject(WebDriver oBrowser, String strObjectName, String strData, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The data '"+strData+"' was entered in the element '"+strObjectName+"' successful", test);
				oEles.get(0).clear();
				oEles.get(0).sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+strObjectName+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clearAndSetObject()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: compareValue()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean compareValue(WebDriver oBrowser, String actual, String expected, ExtentTest test)
	{
		try {
			if(actual.equalsIgnoreCase(expected)) {
				reports.writeResult(oBrowser, "Pass", "Both actual '"+actual+"' & expected '"+expected+"' are matching", test);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Mis-match in both actual '"+actual+"' & expected '"+expected+"' values", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'compareValue()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	
	/****************************************************
	 * Method Name		: verifyText()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyText(WebDriver oBrowser, By objBy, String objType, String expected, ExtentTest test)
	{
		List<WebElement> oEles = null;
		Select oSel = null;
		String actual = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0)
			{
				switch(objType.toLowerCase())
				{
					case "text":
						actual = oEles.get(0).getText();
						break;
					case "value":
						actual = oEles.get(0).getAttribute("value");
						break;
					case "dropdown":
						oSel = new Select(oEles.get(0));
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeResult(oBrowser, "Fail", "Invalid object type '"+objType+"'", test);
				}
				
				if(appInd.compareValue(oBrowser, actual, expected, test)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oSel = null;
			oEles = null;
			actual = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyText()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyText(WebDriver oBrowser, String strObjectName, String objType, String expected, ExtentTest test)
	{
		List<WebElement> oEles = null;
		Select oSel = null;
		String actual = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size() > 0)
			{
				switch(objType.toLowerCase())
				{
					case "text":
						actual = oEles.get(0).getText();
						break;
					case "value":
						actual = oEles.get(0).getAttribute("value");
						break;
					case "dropdown":
						oSel = new Select(oEles.get(0));
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeResult(oBrowser, "Fail", "Invalid object type '"+objType+"'", test);
				}
				
				if(appInd.compareValue(oBrowser, actual, expected, test)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+strObjectName+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(), test);
			return false;
		}
		finally {
			oSel = null;
			oEles = null;
			actual = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyElementExist()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyElementExist(WebDriver oBrowser, By objBy, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' exist in the DOM", test);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementExist()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyElementExist()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyElementExist(WebDriver oBrowser, String strObjectName, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Pass", "The element '"+strObjectName+"' exist in the DOM", test);
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Unable to locate the element '"+strObjectName+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementExist()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyElementNotExist()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyElementNotExist(WebDriver oBrowser, By objBy, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Fail", "The element '"+String.valueOf(objBy)+"' exist in the DOM", test);
				return false;
			}else {
				reports.writeResult(oBrowser, "Pass", "Unable to locate the element '"+String.valueOf(objBy)+"'", test);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementNotExist()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyElementNotExist()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyElementNotExist(WebDriver oBrowser, String strObjectName, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size() > 0)
			{
				reports.writeResult(oBrowser, "Fail", "The element '"+strObjectName+"' exist in the DOM", test);
				return false;
			}else {
				reports.writeResult(oBrowser, "Pass", "Unable to locate the element '"+strObjectName+"'", test);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementNotExist()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyOptionalElement()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyOptionalElement(WebDriver oBrowser, By objBy, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			
			if(oEles.size() > 0)
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyOptionalElement()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: verifyOptionalElement()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean verifyOptionalElement(WebDriver oBrowser, String strObjectName, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size() > 0)
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyOptionalElement()' method. "+e.getMessage(), test);
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: launchBrowser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public WebDriver launchBrowser(String browserName, ExtentTest test)
	{
		WebDriver driver = null;
		try {
			switch(browserName.toLowerCase())
			{
				case "chrome":
					System.setProperty("webdriver.chrome.driver", ".\\Library\\drivers\\chromedriver.exe");
					driver = new ChromeDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", ".\\Library\\drivers\\geckodriver.exe");
					driver = new FirefoxDriver();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", ".\\Library\\drivers\\IEDriverServer_32.exe");
					driver = new InternetExplorerDriver();
					break;
				default:
					reports.writeResult(null, "Fail", "Invalid browser name '"+browserName+"' provided. Please correct it.", test);
					
			}
			
			if(driver!=null) {
				reports.writeResult(driver, "Pass", "The '"+browserName+"' browser has launched successful", test);
				driver.manage().window().maximize();
				return driver;
			}else {
				reports.writeResult(null, "Fail", "Failed to launch '"+browserName+"' browser", test);
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'launchBrowser()' method. "+ e.getMessage(), test);
			return null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: closeBrowser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean closeBrowser(WebDriver oDriver, ExtentTest test)
	{
		try {
			oDriver.close();
			return true;
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'closeBrowser()' method. "+ e.getMessage(), test);
			return false;
		}
		finally {
			oDriver = null;
		}
	}
	
	
	
	
	
	/****************************************************
	 * Method Name		: waitFor()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean waitFor(WebDriver oBrowser, By objBy, String waitReason, String strValue, int timeout)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oBrowser, timeout);
			switch(waitReason.toLowerCase())
			{
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, strValue));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(objBy, strValue));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(objBy));
					break;
				case "visibility":
					oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
					break;
				case "invisibility":
					oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
					break;
				default:
					reports.writeResult(oBrowser, "Fail", "Invalid wait condition '"+waitReason+"' was provided.", test);
			}
			return true;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'waitFor()' method. "+ e.getMessage());
			return false;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: waitFor()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public boolean waitFor(WebDriver oBrowser, String strObjectName, String waitReason, String strValue, int timeout)
	{
		WebDriverWait oWait = null;
		WebElement oEle = null;
		try {
			oEle = oBrowser.findElement(By.xpath(strObjectName));
			oWait = new WebDriverWait(oBrowser, timeout);
			switch(waitReason.toLowerCase())
			{
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElement(oEle, strValue));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(oEle, strValue));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(oEle));
					break;
				case "visibility":
					oWait.until(ExpectedConditions.visibilityOf(oEle));
					break;
				case "invisibility":
					oWait.until(ExpectedConditions.invisibilityOf(oEle));
					break;
				default:
					reports.writeResult(oBrowser, "Fail", "Invalid wait condition '"+waitReason+"' was provided.", test);
			}
			return true;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'waitFor()' method. "+ e.getMessage());
			return false;
		}
	}
}
