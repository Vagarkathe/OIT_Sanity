package testRunner;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.ParseException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import resources.Automail;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/featureFile"},
		glue = {"stepDefinitions", "interfaceContractStepDefinitions", "stepDef"},
//		glue = {"stepDefinitions"},
		tags = "@Sanity",
		plugin = {"pretty","html:target/cucumber.html","junit:target/cucumber.xml","json:target/cucumber.json", 
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true,
		dryRun = false
//		publish = true
		)

public class TestRunner {
	@AfterClass
    public static void sendReport() throws InvalidFormatException, ParseException, InterruptedException, IOException, MessagingException, org.json.simple.parser.ParseException, java.text.ParseException{
		Automail automail = new Automail();
		automail.Gen_Reports();
    }
}
