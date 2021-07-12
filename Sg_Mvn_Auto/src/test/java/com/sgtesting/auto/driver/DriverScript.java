package com.sgtesting.auto.driver;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sgtesting.auto.methods.AppIndependent;
import com.sgtesting.auto.methods.Appdependent;
import com.sgtesting.auto.methods.Datatable;
import com.sgtesting.auto.methods.TaskModule;
import com.sgtesting.auto.methods.UserModule;
import com.sgtesting.auto.reports.ReportsUtil;

public class DriverScript {
	public static AppIndependent appInd = null;
	public static Appdependent appDep = null;
	public static UserModule userMethods = null;
	public static TaskModule taskMethods = null;
	public static Datatable datatable = null;
	public static ReportsUtil reports = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String screenshotLocation = null;
	public static String strController = null;
	public static String moduleName = null;
	
	
	@BeforeSuite
	public void loadClasses()
	{
		try {
			appInd = new AppIndependent();
			appDep = new Appdependent();
			userMethods = new UserModule();
			taskMethods = new TaskModule();
			datatable = new Datatable();
			reports = new ReportsUtil();
			strController = System.getProperty("user.dir")+"\\ExecutionController\\Controller.xlsx";
			//extent = reports.startExtentReport("TestResults", appInd.readConfigData("buildNumber"));
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'loadClasses()' method. "+e.getMessage());
		}
	}
	
	
	
	@Test
	public void executionStatus()
	{
		Class cls = null;
		Object obj = null;
		Method meth = null;
		String executionStatus = null;
		String scriptName = null;
		String className = null;
		int pRows = 0;
		int mRows = 0;
		int tcRows = 0;
		int projectCount = 0;
		int moduleCount = 0;
		int testCaseCount = 0;
		try {
			pRows = datatable.getRowNumber(strController, "Projects");
			for(int i=0; i<pRows; i++)
			{
				executionStatus = datatable.getCellData(strController, "Projects", "ExecuteProject", i+1);
				if(executionStatus.equalsIgnoreCase("Yes"))
				{
					projectCount++;
					String projectName = datatable.getCellData(strController, "Projects", "ProjectName", i+1);
					mRows = datatable.getRowNumber(strController, projectName);
					
					for(int j=0; j<mRows; j++)
					{
						executionStatus = datatable.getCellData(strController, projectName, "ExecuteModule", j+1);
						if(executionStatus.equalsIgnoreCase("Yes"))
						{
							moduleCount++;
							moduleName = datatable.getCellData(strController, projectName, "ModuleName", j+1);
							tcRows = datatable.getRowNumber(strController, moduleName);
							for(int k=0; k<tcRows; k++)
							{
								executionStatus = datatable.getCellData(strController, moduleName, "executionStatus", k+1);
								if(executionStatus.equalsIgnoreCase("Yes"))
								{
									testCaseCount++;
									scriptName = datatable.getCellData(strController, moduleName, "TestScriptName", k+1);
									className = datatable.getCellData(strController, moduleName, "ClassName", k+1);
									
									cls = Class.forName(className);
									obj = cls.newInstance();
									meth = obj.getClass().getMethod(scriptName);
									String status = String.valueOf(meth.invoke(obj));
									if(status.equalsIgnoreCase("True")) {
										datatable.setCellData(strController, moduleName, "Status", k+1, "PASS");
									}else {
										datatable.setCellData(strController, moduleName, "Status", k+1, "FAIL");
									}
								}
							}
						}
					}
				}
				
				if(projectCount == 0) {
					System.out.println("Please select atleast one PROJECT to continue test execution");
				}
				else if(moduleCount == 0) {
					System.out.println("Please select atleast one MODULE to continue test execution");
				}
				else if(testCaseCount == 0) {
					System.out.println("Please select atleast one TEST SCRIPT to continue test execution");
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'executionStatus()' method. "+e.getMessage());
		}
	}
}
