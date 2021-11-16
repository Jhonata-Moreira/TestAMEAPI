package ame.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/dummy_api.feature"}, glue = {"ame/api/stepDefinitions/efetivacoesDummyStepsdefs.java"})
public class ApiDummyRunner {
}
