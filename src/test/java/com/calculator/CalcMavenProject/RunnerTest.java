package com.calculator.CalcMavenProject;


import org.junit.runner.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	
				
		format = {"pretty","html:target/html/"},
		features={"src/test/resource/LocalCucumberAppTest.feature"})
				
				//features={"src/cucmberTest/*.feature"})

public class RunnerTest {

}
