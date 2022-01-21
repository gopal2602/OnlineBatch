package locators;

import org.openqa.selenium.By;

public interface ObjectLocators {
	public static By objUserNameEdit = By.id("username");
	public static By objPasswordEdit = By.name("pwd");
	public static By objLoginButton = By.id("loginButton");
	public static By objTimeTrackPageTitle = By.xpath("//td[@class='pagetitle']");
	public static By objShortcutWindow = By.xpath("//div[@style='display: block;']");
	public static By objShortCutWindowCloseButton = By.id("gettingStartedShortcutsMenuCloseId");
	public static By objUserMenu = By.xpath("//div[text()='USERS']");
	public static By objAddUserButton = By.xpath("//div[text()='Add User']");
	public static By objAddUserPage = By.xpath("//div[contains(text(), 'Account Information')]");
	public static By objFirstNameEdit = By.name("firstName");
	public static By objLastNameEdit = By.name("lastName");
	public static By objEmailEdit = By.name("email");
	public static By objUser_UserName = By.name("username");
	public static By objUser_Password = By.name("password");
	public static By objUser_RetypePassword = By.name("passwordCopy");
	public static By objCreateUserButton = By.xpath("//span[text()='Create User']");
	public static By objUserPage_UserNameLable = By.id("userDataLightBox_userNamePlaceholder");
	public static By objDeleteUserButton = By.id("userDataLightBox_deleteBtn");
	public static By objLogoutLink = By.id("logoutLink");
	public static By objLoginPage_HeaderLabel = By.id("headerContainer");
	public static By objExploreActitimeLink = By.xpath("//span[text()='Start exploring actiTIME']");
	
}
