package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Init {
	
    protected WebDriver driver = null;
	
	@BeforeMethod
	public void setUp(ITestResult result){
	    driver = LocalDriverFactory.createInstance("chrome", "http://google.com");	
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		System.out.println(result.getTestName());
		System.out.println(result.getName());
		System.out.println(result.getTestContext());
		LocalDriverFactory.closeBrowser();
		
	}
	

}
