//This test is for running the test locally ,before running start appium server

package com.calculator.CalcMavenProject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;





import java.io.File;
import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;




public class LocalAppiumAppTest {
	

	private IOSDriver driver;
	 @Rule 
	 public ErrorCollector collector = new ErrorCollector();
		
	@Before
	public void setUp() throws Exception {
		try {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "TestApp7.zip");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("platformVersion", "8.4");
		capabilities.setCapability("app", app.getAbsolutePath());
		//capabilities.setCapability("appPackage", "com.uniface.client.previewer");
		//capabilities.setCapability("appActivity", ".MainActivity");
		
		// capabilities.setCapability("autoWebview", true);

		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
			capabilities);
		  //driver = new IOSDriver(capabilities); 
		} catch(Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
		
	
	@Test
	
	public void SumCalculatoriOS() {
		 
	    
	    	
	    
	 
	    
	    Integer[] firstFieldVal = {8, 121,131};
	    Integer[] SecondFieldVal = {13, 100,99};
	    Integer[] ExpectedSumVal = {21,201,230};
	    
	    for(int i = 0; i < firstFieldVal.length ; i++)
	    {
	    	 MobileElement firstField1 = (MobileElement) driver.findElementByAccessibilityId("TextField1");
	    	 
	    	
	    	 firstField1.clear();
	    	 firstField1.sendKeys(String.valueOf(firstFieldVal[i]));
	    	 
	    	 
	    	 MobileElement SecondField2 = (MobileElement) driver.findElementsByClassName("UIATextField").get(1);
	    	 SecondField2.clear();
	    	 SecondField2.sendKeys(String.valueOf(SecondFieldVal[i]));
	    	 //SecondField2.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(SecondFieldVal[i]));
	    	 
	    	 driver.findElementByAccessibilityId("ComputeSumButton").click();
	       	 
	       	 // is sum equal?
	    	    String sum = (String) ((RemoteWebElement) driver.findElementsByClassName("UIAStaticText").get(0)).getText();
	    	    
	    	    
	    	   // TestCase.assertEquals(Integer.parseInt(sum), ExpectedSumVal[i]);
	    	    
	    	 
	    	    
	    	    
	    	    collector.checkThat(Integer.parseInt(sum), CoreMatchers.equalTo(ExpectedSumVal[i]));
	    	    
	    	    
	    	    
	   
	    }
	    
	}
}

	

