package methods;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverScript;


public class AppIndependentMethods extends DriverScript{
	/*****************************************************
	 * Method Name		: getPropValues()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public Map<String, String> getPropValues(String strFilePath)
	{
		FileInputStream fin = null;
		Properties prop = null;
		Map<String, String> objMap = null;
		try {
			objMap = new HashMap<String, String>();
			fin = new FileInputStream(strFilePath);
			prop = new Properties();
			prop.load(fin);
			
			Set<Map.Entry<Object, Object>> oBoth = prop.entrySet();
			Iterator<Map.Entry<Object, Object>> it = oBoth.iterator();
			while(it.hasNext()) {
				Map.Entry<Object, Object> mp = (Entry<Object, Object>) it.next();
				String key = String.valueOf(mp.getKey());
				String value = String.valueOf(mp.getValue());
				objMap.put(key, value);
			}
			return objMap;
		}catch(Exception e)
		{
			System.out.println("Exception in 'readPropFile()' method. "+ e);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				prop = null;
				objMap = null;
			}catch(Exception e)
			{
				System.out.println("Exception in 'readPropFile()' method. "+ e);
				return null;
			}
		}
	}
	
	
	
	
	/*****************************************************
	 * Method Name		: getDataTime()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public String getDataTime(String strFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(strFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			System.out.println("Exception in 'getDataTime()' method. "+ e);
			return null;
		}
		finally {
			dt = null;
			sdf = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: clickObject
	 * purpose			: to click on the webelement
	 * Parameters		: WebDriver oBrowser, By objBy
	 * Return Type		: boolean
	 **************************************************************/
	public boolean clickObject(WebDriver oBrowser, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				oEles.get(0).click();
				reports.writeResult(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to locate the element : '"+String.valueOf(objBy)+"'");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clickObject()' method. "+e);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: setObject
	 * purpose			: to enter the values in the text filed webelement
	 * Parameters		: WebDriver oBrowser, By objBy, String strData
	 * Return Type		: boolean
	 **************************************************************/
	public boolean setObject(WebDriver oBrowser, By objBy, String strData)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser, "Pass", "The value '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to locate the element : '"+String.valueOf(objBy)+"'");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'setObject()' method. "+e);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: clearAndSetObject
	 * purpose			: to enter the values in the text filed by removing the old value
	 * Parameters		: WebDriver oBrowser, By objBy, String strData
	 * Return Type		: boolean
	 **************************************************************/
	public boolean clearAndSetObject(WebDriver oBrowser, By objBy, String strData)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				oEles.get(0).clear();
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser, "Pass", "The value '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to locate the element : '"+String.valueOf(objBy)+"'");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'clearAndSetObject()' method. "+e);
			return false;
		}
		finally {
			oEles = null;
		}
	}
	
	
	
	/************************************************************
	 * Method Name		: compareValues
	 * purpose			: to compare both actual and expected value
	 * Parameters		: WebDriver oBrowser, String actual, String expected
	 * Return Type		: boolean
	 **************************************************************/
	public boolean compareValues(WebDriver oBrowser, String actual, String expected)
	{
		try {
			if(actual.equals(expected)) {
				reports.writeResult(oBrowser, "Pass", "Both actual '"+actual+"' and expected '"+expected+"' values are matching");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Mis-match in both actual '"+actual+"' and expected '"+expected+"' values");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'compareValues()' method. "+e);
			return false;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: verifyText
	 * purpose			: to verify the text present on the elements
	 * Parameters		: WebDriver oBrowser, By objBy, String strObjType, String expectedValue
	 * Return Type		: boolean
	 **************************************************************/
	public boolean verifyText(WebDriver oBrowser, By objBy, String strObjType, String expectedValue)
	{
		List<WebElement> oEles = null;
		Select oSelect = null;
		String actual = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				switch(strObjType.toLowerCase()) {
					case "text":
						actual = oEles.get(0).getText();
						break;
					case "value":
						actual = oEles.get(0).getAttribute("value");
						break;
					case "dropdown":
						oSelect = new Select(oEles.get(0));
						actual = oSelect.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeResult(oBrowser, "Fail", "Invalid object type '"+strObjType+"' to read the text");
						return false;
				}
				
				if(compareValues(oBrowser, actual, expectedValue)) return true;
				else return false;
				
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to locate the element : '"+String.valueOf(objBy)+"'");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyText()' method. "+e);
			return false;
		}
		finally
		{
			oEles = null;
			oSelect = null;
			actual = null;
		}
	}
	
	
	
	/************************************************************
	 * Method Name		: verifyElementPresent
	 * purpose			: to verify the presence of the element in the DOM
	 * Parameters		: String actual, String expected
	 * Return Type		: boolean
	 **************************************************************/
	public boolean verifyElementPresent(WebDriver oBrowser, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				reports.writeResult(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was present in the DOM");
				return true;
			}else {
				reports.writeResult(oBrowser, "Fail", "Faile to locate the element '"+String.valueOf(objBy)+"' in the DOM");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementPresent()' method. "+e);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: verifyElementNotPresent
	 * purpose			: to verify the non-presence of the element in the DOM
	 * Parameters		: String actual, String expected
	 * Return Type		: boolean
	 **************************************************************/
	public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				reports.writeResult(oBrowser, "Fail", "The element '"+String.valueOf(objBy)+"' is still present in the DOM");
				return false;
			}else {
				reports.writeResult(oBrowser, "Pass", "The element '"+String.valueOf(objBy)+"' was not present in the DOM");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyElementNotPresent()' method. "+e);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: verifyOptionalElement
	 * purpose			: to verify the presence of the optional element in the DOM
	 * Parameters		: String actual, String expected
	 * Return Type		: boolean
	 **************************************************************/
	public boolean verifyOptionalElement(WebDriver oBrowser, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objBy);
			if(oEles.size() > 0) {
				reports.writeResult(oBrowser, "Pass", "The optional element '"+String.valueOf(objBy)+"' was present in the DOM");
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception while executing 'verifyOptionalElement()' method. "+e);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	
	
	/************************************************************
	 * Method Name		: launchBrowser
	 * purpose			: to launch the different browsers viz., chrome, firefox and ie
	 * Author			: tester1
	 * Reviewer			: tester2
	 * Date created		:
	 * Date modified	:
	 * Modified Reason	: 
	 * Date reviewed	:
	 * Parameters		: String browserName
	 * Return Type		: WebDriver
	 **************************************************************/
	public WebDriver launchBrowser(String browserName, String browserMode)
	{
		WebDriver oBrowser = null;
		DesiredCapabilities capabilities = null;
		ChromeOptions options = null;
		try {			
			switch(browserName.toLowerCase()) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver", ".\\Library\\Drivers\\chromedriver.exe");
					if(browserMode.equalsIgnoreCase("True")) {
						capabilities = new DesiredCapabilities();
						options = new ChromeOptions();
						options.addArguments("--headless");
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
						oBrowser = new ChromeDriver(options);
					}else {
						oBrowser = new ChromeDriver();
					}
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", ".\\Library\\Drivers\\geckoBrowser.exe");
					oBrowser = new FirefoxDriver();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", ".\\Library\\Drivers\\IEDriverServer.exe");
					oBrowser = new InternetExplorerDriver();
					break;
				default:
					reports.writeResult(oBrowser, "Fail", "Invalid browser name '"+browserName+"' was specified.");
			}
			
			if(oBrowser!=null) {
				oBrowser.manage().window().maximize();
				reports.writeResult(oBrowser, "Pass", "The '"+browserName+"' browser was launched successful");
				return oBrowser;
			}else {
				reports.writeResult(oBrowser, "Fail", "Failed to launch '"+browserName+"' browser");
				return  null;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'launchBrowser()' method. "+ e);
			return null;
		}
	}
	
	
	
	/************************************************************
	 * Method Name		: WaitForElement
	 * purpose			: to establish the sync between script and application speed
	 * Parameters		: WebDriver oBrowser, By objBy, String waitCondition, String strTexTvalue, int intWaitTime
	 * Return Type		: boolean
	 **************************************************************/
	public boolean WaitForElement(WebDriver oBrowser, By objBy, String waitCondition, String strTextValue, int intWaitTime)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(intWaitTime));
			switch(waitCondition.toLowerCase()) {
				case "visibility":
					oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(objBy));
					break;
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, strTextValue));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(objBy, strTextValue));
					break;
				case "invisibility":
					oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
					break;
				case "presence":
					oWait.until(ExpectedConditions.presenceOfElementLocated(objBy));
					break;
				default:
					reports.writeResult(oBrowser, "Fail", "Invalid wait condition '" + waitCondition + "' was provided. Please provide appropriate wait condition");
			}
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oBrowser, "Exception", "Exception in 'WaitForElement()' method. "+ e);
			return false;
		}
		finally {
			oWait = null;
		}
	}
	
	
	
	/*****************************************************
	 * Method Name		: deleteContent()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public void deleteReportContent()
	{
		File file = null;
		File content[];
		File screenshots[];
		try {
			file = new File(System.getProperty("user.dir") + "\\Results");
			content = file.listFiles();
			for(int i=0; i<content.length; i++) {
				boolean blnRes = content[i].delete();
				if(blnRes == false) {
					screenshots = content[i].listFiles();
					for(int j=0; j<screenshots.length; j++) {
						screenshots[j].delete();
					}
					i=i-1;
				}
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception in 'deleteContent()' method. "+ e);
		}
		finally {
			file = null;
			content = null;
			screenshots = null;
		}
	}
}
