// This test is for sauce labs

package com.calculator.CalcMavenProject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;


public class SauceLabsAppTest implements SauceOnDemandSessionIdProvider{
	
	
	//final private String USERNAME = System.getenv("atest");
	final private String USERNAME = "atestbs";
	

	final private String ACCESS_KEY = "c228a419-";

	private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

	private IOSDriver driver;
	private String sessionId;
	
	@Rule 
	 public ErrorCollector collector = new ErrorCollector();

	@Rule
	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	public String getSessionId() {
	  return sessionId;
	}

	public @Rule TestName name = new TestName();

		
		@Before
		public void setUp() throws Exception {
			try {
			//File classpathRoot = new File(System.getProperty("user.dir"));
			//File appDir = new File(classpathRoot, "apps");
			//File app = new File(appDir, "TestApp.ipa");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appiumVersion", "1.5.0");
			capabilities.setCapability("deviceName", "iPhone 6");
			capabilities.setCapability("platformVersion", "8.4");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability(MobileCapabilityType.APP,"sauce-storage:TestApp7.zip");
			
			
			
			

		//capabilities.setCapability(MobileCapabilityType.APP, "http://appium.s3.amazonaws.com/TestApp7.1.app.zip");
		
					capabilities.setCapability("name", name.getMethodName());
					
	//URL sauceUrl = new URL("http://" + authentication.getUsername() + ":"+ authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
	URL sauceUrl = new URL(MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:80/wd/hub", "atest", "c228a419-62f0-40a6-8f7a-7170fb84b31f"));

	driver = new IOSDriver(sauceUrl, capabilities);
				    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				    sessionId = driver.getSessionId().toString();
			
				    
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
	    	    //String ExpectedS = String.valueOf(ExpectedSumVal[i]);
	    	    
	    	   // TestCase.assertEquals(Integer.parseInt(sum), ExpectedSumVal[i]);
	    	    
	    	   // TestCase.assertSame(Integer.parseInt(sum), ExpectedSumVal[i]);
	    	 
	    	    collector.checkThat(Integer.parseInt(sum), CoreMatchers.equalTo(ExpectedSumVal[i]));
	    	
	    }

	  }
}
	
		
	