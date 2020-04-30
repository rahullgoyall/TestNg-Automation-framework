package utility;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
	
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	public static void setWebDriver(WebDriver driver){
		webDriver.set(driver);
	}
	
	public static WebDriver getWebDriver(){
		return webDriver.get();
	}
	
	
}
