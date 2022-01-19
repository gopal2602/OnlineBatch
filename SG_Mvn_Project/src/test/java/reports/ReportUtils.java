package reports;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.DriverScript;

public class ReportUtils extends DriverScript{

	/*****************************************************
	 * Method Name		: startExtentReport()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public ExtentReports startExtentReport(String fileName, String buildNumber) {
		String resultPath = null;
		File objResultPath = null;
		File objScreenshot = null;
		try {
			resultPath = System.getProperty("user.dir") + "\\Results";
			
			objResultPath = new File(resultPath);
			if(!objResultPath.exists()) {
				objResultPath.mkdirs();
			}
				
			
			screenshotLocation = resultPath + "\\screenshot";
			objScreenshot = new File(screenshotLocation);
			if(!objScreenshot.exists()) {
				objScreenshot.mkdirs();
			}
			
			extent = new ExtentReports(objResultPath + "\\" + fileName + ".html" , false);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			extent.addSystemInfo("Environment", objMasterData.get("Environment"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
			return extent;
		}catch(Exception e) {
			System.out.println("Exception in 'startExtentReport()' method. " + e);
			return null;
		}
		finally {
			resultPath = null;
			objResultPath = null;
			objScreenshot = null;
		}
	}
	
	
	
	/*****************************************************
	 * Method Name		: endExtentReport()()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public void endExtentReport(ExtentTest test)
	{
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e) {
			System.out.println("Exception in 'endExtentReport()' method. " + e);
		}
	}
	
	
	
	
	/*****************************************************
	 * Method Name		: captureScreenshot()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public String captureScreenshot(WebDriver oBrowser)
	{
		File objSource = null;
		String strDestination = null;
		File objDestination = null;
		try {
			if(oBrowser!=null) {
				strDestination = screenshotLocation + "\\screenshot_" + appInd.getDataTime("ddMMYYYY_hhmmss")+".png";
				TakesScreenshot ts = (TakesScreenshot) oBrowser;
				objSource = ts.getScreenshotAs(OutputType.FILE);
				
				objDestination = new File(strDestination);
				FileHandler.copy(objSource, objDestination);
				return strDestination;
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("Exception in 'captureScreenshot()' method. " + e);
			return null;
		}
		finally {
			objSource = null;
			strDestination = null;
			objDestination = null;
		}
	}
	
	
	
	/*****************************************************
	 * Method Name		: writeResult()
	 * Purpose			:
	 * Author			:
	 * 
	 * 
	 *****************************************************/
	public void writeResult(WebDriver oBrowser, String status, String strDestription)
	{
		try {
			switch(status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS, strDestription);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strDestription + " : " +
							test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "warning":
					test.log(LogStatus.WARNING, strDestription);
					break;
				case "info":
					test.log(LogStatus.INFO, strDestription);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strDestription+ " : " +
							test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				case "screenshot":
					test.log(LogStatus.PASS, strDestription+ " : " +
							test.addScreenCapture(reports.captureScreenshot(oBrowser)));
					break;
				default:
					System.out.println("Invalid result status '" + status + "'. Please provide the valid status");
			}
		}catch(Exception e) {
			System.out.println("Exception in 'writeResult()' method. " + e);
		}
	}
}
