package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import listeners.TestNgListener;

@Listeners(TestNgListener.class)
public class Init {
	
	public static Map<String, ArrayList<Integer>> hashMap = new HashMap<String, ArrayList<Integer>>();
    protected WebDriver driver = null;
    

	@BeforeMethod
	public void setUp(ITestContext testContext){
		System.out.println("test started ");
	    driver = LocalDriverFactory.createInstance("grid_chrome", "http://google.com");	
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		addSuiteInfo(result.getTestContext().getName(),result.getStatus());
		
		LocalDriverFactory.closeBrowser();
		
	}
	
	
	//Associated with @BeforeMethods and @AfterMethods hooks
		private void addSuiteInfo(String key, Integer value) {
			ArrayList<Integer> tempList = null;
			if (hashMap.containsKey(key)) {
				tempList = hashMap.get(key);
				if(tempList == null)
					tempList = new ArrayList<Integer>();
				tempList.add(value);  
			} else {
				tempList = new ArrayList<Integer>();
				tempList.add(value);               
			}
			hashMap.put(key,tempList);
		}
	

}
