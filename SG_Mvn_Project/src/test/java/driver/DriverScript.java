package driver;

import java.util.Map;

import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import reports.ReportUtils;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static Datatable datatable = null;
	public static UserModuleMethods userMethods = null;
	public static TaskModuleMethods taskMethods = null;
	public static ReportUtils reports = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static Map<String, String> objMasterData = null;
	public static String screenshotLocation = null;
	
	
	@BeforeSuite
	public void loadClasses() {
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			datatable = new Datatable();
			userMethods = new UserModuleMethods();
			taskMethods = new TaskModuleMethods();
			reports = new ReportUtils();
			appInd.deleteReportContent();
			objMasterData = appInd.getPropValues(System.getProperty("user.dir") + "\\Configuration\\MasterData.properties");
			extent = reports.startExtentReport("TestFrameworkUI", objMasterData.get("BuildNumber"));
		}catch(Exception e) {
			System.out.println("Exceptoin in 'loadClasses()' method. " + e);
		}
	}
}
