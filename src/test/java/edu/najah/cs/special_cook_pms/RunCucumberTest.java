package edu.najah.cs.special_cook_pms;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Matches: src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "edu.najah.cs.special_cook_pms.AcceptanceTest")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")

public class RunCucumberTest {
}
