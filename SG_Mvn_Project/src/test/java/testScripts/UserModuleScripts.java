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
	 * Test Case ID		: SRS_101
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
			Assert.assertTrue(appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password")));
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
	 * Test Case ID		: SRS_101
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
			Assert.assertTrue(appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password")));
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
}
