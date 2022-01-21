package testScripts;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import driver.DriverScript;

public class UserModuleScripts extends DriverScript{
	public String testDataFilePath = System.getProperty("user.dir") + "\\TestData\\UserModule.xlsx";
	/****************************************************************
	 * Script Name		: TS_LoginLogout()
	 * Test Case ID		: SRS_01
	 * Author			:
	 * 
	 ****************************************************************/
	@Test
	public void TS_LoginLogout() {
		WebDriver oBrowser = null;
		Map<String, String> objData = null;
		try {
			test = extent.startTest("TS_LoginLogout");
			objData = datatable.readDataFromExcel(testDataFilePath, "UserModule", "SRS_01");
			oBrowser = appInd.launchBrowser(objData.get("Browser"), objMasterData.get("Headless"));
			Assert.assertTrue(appDep.navigateURL(oBrowser, objMasterData.get("URL")));
			Assert.assertTrue(appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password"), "Existing"));
			Assert.assertTrue(appDep.logoutFromApp(oBrowser));
		}catch(Exception e) {
			reports.writeResult(oBrowser, "Exception", "Exception in 'TS_LoginLogout()' method. " + e);
		}
		finally
		{
			reports.endExtentReport(test);
			oBrowser.close();
			oBrowser = null;
		}
	}
	
	

	/****************************************************************
	 * Script Name		: TS_createAndDeleteUser()
	 * Test Case ID		: SRS_02
	 * Author			:
	 * 
	 ****************************************************************/
	@Test
	public void TS_createAndDeleteUser()
	{
		WebDriver oBrowser = null;
		String userName = null;
		Map<String, String> objData = null;
		try {
			test = extent.startTest("TS_createAndDeleteUser");
			objData = datatable.readDataFromExcel(testDataFilePath, "UserModule", "SRS_02");
			oBrowser = appInd.launchBrowser(objData.get("Browser"), objMasterData.get("Headless"));
			Assert.assertTrue(appDep.navigateURL(oBrowser, objMasterData.get("URL")));
			Assert.assertTrue(appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password"), "Existing"));
			userName = userMethods.createUser(oBrowser, objData);
			Assert.assertTrue(userMethods.deleteUser(oBrowser, userName));
			Assert.assertTrue(appDep.logoutFromApp(oBrowser));
			
			reports.writeResult(oBrowser, "Pass", "The test script 'TS_createAndDeleteUser()' Passed.");
		}catch(Exception e) {
			reports.writeResult(oBrowser, "Exception", "Exception in 'TS_createAndDeleteUser()' method. " + e);
		}
		finally
		{
			reports.endExtentReport(test);
			oBrowser.close();
			oBrowser = null;
		}
	}
	
	
	
	
	/****************************************************************
	 * Script Name		: TS_createUser_LoginwithNewUserAndDeleteUser()
	 * Test Case ID		: SRS_03
	 * Author			:
	 * 
	 ****************************************************************/
	@Test
	public void TS_createUser_LoginwithNewUserAndDeleteUser()
	{
		WebDriver oBrowser1 = null;
		WebDriver oBrowser2 = null;
		String userName = null;
		Map<String, String> objData = null;
		try {
			test = extent.startTest("TS_createUser_LoginwithNewUserAndDeleteUser");
			objData = datatable.readDataFromExcel(testDataFilePath, "UserModule", "SRS_03_1");
			oBrowser1 = appInd.launchBrowser(objData.get("Browser"), objMasterData.get("Headless"));
			Assert.assertTrue(appDep.navigateURL(oBrowser1, objMasterData.get("URL")));
			Assert.assertTrue(appDep.loginToApp(oBrowser1, objData.get("UserName"), objData.get("Password"), "Existing"));
			userName = userMethods.createUser(oBrowser1, objData);
			
			
			//Login with newly created user
			oBrowser2 = appInd.launchBrowser(objData.get("Browser"), objMasterData.get("Headless"));
			Assert.assertTrue(appDep.navigateURL(oBrowser2, objMasterData.get("URL")));
			objData = datatable.readDataFromExcel(testDataFilePath, "UserModule", "SRS_03_2");
			Assert.assertTrue(appDep.loginToApp(oBrowser2, objData.get("UserName"), objData.get("Password"), "New"));
			Assert.assertTrue(appDep.logoutFromApp(oBrowser2));
			
			Assert.assertTrue(userMethods.deleteUser(oBrowser1, userName));
			Assert.assertTrue(appDep.logoutFromApp(oBrowser1));
			
			reports.writeResult(oBrowser1, "Pass", "The test script 'TS_createUser_LoginwithNewUserAndDeleteUser()' Passed.");
		}catch(Exception e) {
			reports.writeResult(oBrowser1, "Exception", "Exception in 'TS_createUser_LoginwithNewUserAndDeleteUser()' method. " + e);
		}
		finally
		{
			reports.endExtentReport(test);
			oBrowser1.close();
			oBrowser2.close();
			oBrowser1 = null;
			oBrowser2 = null;
		}
	}
}
