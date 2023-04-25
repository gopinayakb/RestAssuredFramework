package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String reportName;

	public void onStart(ITestContext context) {

		//time stamp.
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Reports-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);//specify location of report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//title of the report
		sparkReporter.config().setReportName("Pet Store Users API"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemt", "QA");
		extent.setSystemInfo("User", "Gopi");
		
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	
	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
