package com.calculator.CalcMavenProject;import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.net.URL;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocalCucumberAppTest {
	
	private IOSDriver driver;
	
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
	
	
	@Given("^the SumCalculator app is launched$")
	public void the_SumCalculator_app_is_launched() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("App launched");
	    
	}

	@When("^give a list of numbers to first field$")
	public void give_a_list_of_numbers_to_first_field(List<Integer> numbers) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		for (Integer number : numbers) {
			System.out.println("firstStep");
			MobileElement firstField1 = (MobileElement) driver.findElementByAccessibilityId("TextField1");
			 firstField1.clear();
			 firstField1.sendKeys(String.valueOf(number));
			 //if we have single value then it could be firstField.sendKeys("12");
	       }

	}

	@When("^give a list of numbers to second field$")
	public void give_a_list_of_numbers_to_second_field(List<Integer> numbers) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		
		for (Integer number : numbers) {
		
			
			MobileElement secondField = (MobileElement) driver.findElementsByClassName("UIATextField").get(1);
			secondField.clear();
			 secondField.sendKeys(String.valueOf(number)); // one need to convert integer to string before passing to string
	        }
	    
	}

	@Then("^click Calculate Sum$")
	public void click_Calculate_Sum() throws Throwable {
	    // This will click on compute Sum button
		driver.findElementById("ComputeSumButton").click();
	    
	}

	@Then("^match the value with ExpectedSum$")
	public void match_the_value_with_ExpectedSum(List<Integer> ExpectedSum) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		
		//ActualSum is the value generated after clicking CalculateSum button and ExpectedSum is the values in decision Table
		
		for (Integer numberSum : ExpectedSum) {
			
			
			
			
			//MobileElement sum = (MobileElement) driver.findElementsByClassName("UIAStaticText").get(0);
			//String sum = (String) driver.findElementsByClassName("UIAStaticText").get(0);
			
			
			 String sum = (String) ((RemoteWebElement) driver.findElementsByClassName("UIAStaticText").get(0)).getText();
	    	   
	    	    
	    	    TestCase.assertSame(Integer.parseInt(sum), numberSum);
	    	 
			//TestCase.assertEquals(Integer.parseInt(sum), numberSum);
			//assertThat(Integer.parseInt(sum), is(numberSum)); // this will fail once the number doesnt match
	       }
	    
		
			
		}
	

}
