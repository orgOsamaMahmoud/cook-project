package edu.najah.cs.special_cook_pms.AcceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import edu.najah.cs.special_cook_pms.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerPreferencesSteps
{
	private final TestContext context;

	public CustomerPreferencesSteps(TestContext context)
	{
	    this.context = context;
	}


	    @Then("the system should store {string} and {string}")
	    public void the_system_should_store_and(String preferences, String allergies) 
	    {
	        assertEquals(preferences, context.customer.getDietaryPreferences());
	        assertEquals(allergies, context.customer.getAllergies());
	    }
}
