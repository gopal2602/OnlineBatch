package methods;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driver.DriverScript;
import locators.ObjectLocators;

public class AppDependentMethods extends DriverScript implements ObjectLocators{
	/************************************************************
	 * Method Name		: navigateURL
	 * purpose			: to navigate the required URL
	 * Parameters		: WebDriver oBrowser, String strURL
	 * Return Type		: boolean
	 **************************************************************/
	public boolean navigateURL(WebDriver oBrowser, String strURL)
	{
		try {
			oBrowser.navigate().to(strURL);
			appInd.WaitForElement(oBrowser, objLoginButton, "Clickable", "", 10);
			reports.writeResult(oBrowser, "Screenshot", "After Loading the URL");
			if(appInd.compareValues(oBrowser, oBrowser.getTitle(), "actiTIME - Login")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'navigateURL()' method. "+ e);
			return false;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: loginToApp()
	 * purpose			: to login to the actiTime application
	 * Parameters		: WebDriver oBrowser, String strURL
	 * Return Type		: boolean
	 **************************************************************/
	public boolean loginToApp(WebDriver oBrowser, String userName, String password)
	{
		try {
			Assert.assertTrue(appInd.setObject(oBrowser, objUserNameEdit, userName));
			Assert.assertTrue(appInd.setObject(oBrowser, objPasswordEdit, password));
			Assert.assertTrue(appInd.clickObject(oBrowser, objLoginButton));
			appInd.WaitForElement(oBrowser, objTimeTrackPageTitle, "Text", "Enter Time-Track", 10);
			
			
			//Verify login is successful
			Assert.assertTrue(appInd.verifyText(oBrowser, objTimeTrackPageTitle, "Text", "Enter Time-Track"));
			reports.writeResult(oBrowser, "Screenshot", "After Login to the actiTime");
			if(appInd.verifyOptionalElement(oBrowser, objShortcutWindow)) {
				Assert.assertTrue(appInd.clickObject(oBrowser, objShortCutWindowCloseButton));
			}
			
			reports.writeResult(oBrowser, "Pass", "Login to application was successful");
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'loginToApp()' method. "+ e);
			return false;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: logoutFromApp()
	 * purpose			: to logout from the actiTime application
	 * Parameters		: WebDriver oBrowser, String userToDelete
	 * Return Type		: boolean
	 **************************************************************/
	public boolean logoutFromApp(WebDriver oBrowser)
	{
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, objLogoutLink));
			appInd.WaitForElement(oBrowser, objLoginPage_HeaderLabel, "Text", "Please identify yourself", 10);
			reports.writeResult(oBrowser, "Screenshot", "After logout from the application");
			Assert.assertTrue(appInd.verifyText(oBrowser, objLoginPage_HeaderLabel, "Text", "Please identify yourself"));
			reports.writeResult(oBrowser, "Pass", "The logout from the application was successful");
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'logoutFromApp()' method. "+ e);
			return false;
		}
	}
}
