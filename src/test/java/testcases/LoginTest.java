package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utility.Init;

public class LoginTest extends Init {
	
	@Test
	public void test(){
		System.out.println(driver.getTitle());
		// driver.findElement(By.xpath("s")); 
	}
	
	
	@Test
	public void test1(){
		System.out.println(driver.getTitle());
		
	}
	

}
