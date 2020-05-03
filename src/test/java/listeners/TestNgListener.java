package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.EmailUtils;
import utility.Init;
import utility.Utils;

public class TestNgListener implements ITestListener {
	
	public static int totalCount = 0;
	public static int totalFailed = 0;
	public static int totalPassed = 0;
	public static int totalSkipped = 0;
	public static String startDateTime;
	public static String endDateTime;
	
	@Override
	public void onStart(ITestContext context) {
		startDateTime = Utils.getFormattedDateTime();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		totalCount = totalFailed+totalPassed+totalSkipped;
		endDateTime = Utils.getFormattedDateTime();
		
		EmailUtils.sendReport("goel199320@gmail.com", "bharatpur", "goel1993420@gmail.com", "Automation Report");
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		totalPassed++;
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		totalFailed++;
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		totalSkipped++;
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


}
