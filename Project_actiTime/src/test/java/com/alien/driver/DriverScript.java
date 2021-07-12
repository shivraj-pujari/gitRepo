package com.alien.driver;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alien.methods.AppDependentMethods;
import com.alien.methods.AppIndependentMethods;
import com.alien.methods.Datatable;
import com.alien.methods.TaskModuleMethods;
import com.alien.methods.UserModuleMethods;
import com.alien.reports.ReportUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static UserModuleMethods userMethods = null;
	public static TaskModuleMethods taskMethods = null;
	public static Datatable datatable = null;
	public static ReportUtils reports = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String screenshotLocation = null;
	public static String strController = null;
	public static String moduleName = null;
	
	
	@BeforeSuite
	public void loadClasses()
	{
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			userMethods = new UserModuleMethods();
			taskMethods = new TaskModuleMethods();
			datatable = new Datatable();
			reports = new ReportUtils();
			strController = System.getProperty("user.dir")+"\\ExecutionController\\Controller.xlsx";
			extent = reports.startExtentReport("TestResults", appInd.readConfigData("buildNumber"));
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'loadClasses()' method. "+e.getMessage());
		}
	}
	
	
	
	@Test
	public void executeTest()
	{
		Class cls = null;
		Object obj = null;
		Method meth = null;
		String executeTest = null;
		String scriptName = null;
		String className = null;
		int pRows = 0;
		int mRows = 0;
		int tcRows = 0;
		int projectCount = 0;
		int moduleCount = 0;
		int testCaseCount = 0;
		try {
			pRows = datatable.getRowCount(strController, "ProjectNames");
			for(int i=0; i<pRows; i++)
			{
				executeTest = datatable.getCellData(strController, "ProjectNames", "ExecuteProject", i+1);
				if(executeTest.equalsIgnoreCase("Yes"))
				{
					projectCount++;
					String projectName = datatable.getCellData(strController, "ProjectNames", "ProjectName", i+1);
					mRows = datatable.getRowCount(strController, projectName);
					
					for(int j=0; j<mRows; j++)
					{
						executeTest = datatable.getCellData(strController, projectName, "ExecuteModule", j+1);
						if(executeTest.equalsIgnoreCase("Yes"))
						{
							moduleCount++;
							moduleName = datatable.getCellData(strController, projectName, "ModuleName", j+1);
							tcRows = datatable.getRowCount(strController, moduleName);
							for(int k=0; k<tcRows; k++)
							{
								executeTest = datatable.getCellData(strController, moduleName, "ExecuteTest", k+1);
								if(executeTest.equalsIgnoreCase("Yes"))
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
			System.out.println("Exception while executing 'executeTest()' method. "+e.getMessage());
		}
	}
}
