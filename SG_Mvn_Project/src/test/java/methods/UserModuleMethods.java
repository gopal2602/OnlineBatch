package methods;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driver.DriverScript;
import locators.ObjectLocators;

public class UserModuleMethods extends DriverScript implements ObjectLocators{
	/************************************************************
	 * Method Name		: createUser()
	 * purpose			: to create the new user in the actiTime application
	 * Parameters		: WebDriver oBrowser, Map<String, String> objData
	 * Return Type		: String
	 **************************************************************/
	public String createUser(WebDriver oBrowser, Map<String, String> objData)
	{
		String userName = null;
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, objUserMenu));
			appInd.WaitForElement(oBrowser, objAddUserButton, "Clickable", "", 10);
			
			Assert.assertTrue(appInd.clickObject(oBrowser, objAddUserButton));
			appInd.WaitForElement(oBrowser, objUser_RetypePassword, "Clickable", "", 10);
			
			
			if(appInd.verifyElementPresent(oBrowser, objAddUserPage))
			{
				userName = objData.get("LastName")+", "+objData.get("FirstName");
				Assert.assertTrue(appInd.setObject(oBrowser, objFirstNameEdit, objData.get("FirstName")));				
				Assert.assertTrue(appInd.setObject(oBrowser, objLastNameEdit, objData.get("LastName")));
				Assert.assertTrue(appInd.setObject(oBrowser, objEmailEdit, objData.get("Email")));
				Assert.assertTrue(appInd.setObject(oBrowser, objUser_UserName, objData.get("User_UN")));
				Assert.assertTrue(appInd.setObject(oBrowser, objUser_Password, objData.get("User_PWD")));
				Assert.assertTrue(appInd.setObject(oBrowser, objUser_RetypePassword, objData.get("User_Retype")));
				Assert.assertTrue(appInd.clickObject(oBrowser, objCreateUserButton));
				appInd.WaitForElement(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userName+"']"), "Text", userName, 10);
				reports.writeResult(oBrowser, "Screenshot", "The required details are entered in the 'Add User' page");
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to open the Add User page. Hence can't create the user");
				return null;
			}
			
			Assert.assertTrue(appInd.verifyElementPresent(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userName+"']")));
			reports.writeResult(oBrowser, "Screenshot", "After creating the user");
			reports.writeResult(oBrowser, "Pass", "User created successful");
			return userName;
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'createUser()' method. "+ e);
			return null;
		}
	}
	
	
	
	
	
	/************************************************************
	 * Method Name		: deleteUser()
	 * purpose			: to delete the given user in the actiTime application
	 * Parameters		: WebDriver oBrowser, String userToDelete
	 * Return Type		: boolean
	 **************************************************************/
	public boolean deleteUser(WebDriver oBrowser, String userToDelete)
	{
		try {
			Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userToDelete+"']")));
			appInd.WaitForElement(oBrowser, objDeleteUserButton, "Clickable", "", 10);
			
			reports.writeResult(oBrowser, "Screenshot", "Before deleting the user");
			if(appInd.verifyText(oBrowser, objUserPage_UserNameLable, "Text", userToDelete))
			{
				reports.writeResult(oBrowser, "Pass", "The selected user is opened successful");
				Assert.assertTrue(appInd.clickObject(oBrowser, objDeleteUserButton));
				Thread.sleep(2000);
				oBrowser.switchTo().alert().accept();
				Thread.sleep(2000);
				
				Assert.assertTrue(appInd.verifyElementNotPresent(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userToDelete+"']")));
				reports.writeResult(oBrowser, "Screenshot", "after deleting the user");
				reports.writeResult(oBrowser, "Pass", "The user was deleted successful");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to open the selected user");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'deleteUser()' method. "+ e);
			return false;
		}
	}
}
