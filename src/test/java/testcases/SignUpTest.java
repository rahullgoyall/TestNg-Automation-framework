package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.Init;

public class SignUpTest extends Init {
	
	@Test
	public void Signup1(){
		System.out.println(driver.getTitle());
		// driver.findElement(By.xpath("s")); 
	}
	
	
	@Test
	public void Signup2(){
		System.out.println(driver.getTitle());
		Assert.assertTrue(false);
	}
	

}
