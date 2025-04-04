package edu.najah.cs.special_cook_pms.AcceptanceTest;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class customer_profile
{
    private final CustomerManager customerManager = new CustomerManager();
    private Customer customer;

    @Given("the system is running")
    public void the_system_is_running()
    {
        assertNotNull(customerManager);
    }

    @When("the customer {string} registers")
    public void the_customer_registers(String name) 
    {
        customerManager.registerCustomer(name); 
    }

    @Then("the system should have {string} registered")
    public void the_system_should_have_registered(String name) 
    {
        assertNotNull(customerManager.getCustomer(name));   
    }

    @Given("the customer {string} is registered")
    public void the_customer_is_registered(String name)
    {
        customerManager.registerCustomer(name);
        customer = customerManager.getCustomer(name);
    }

    @When("the customer updates preferences to {string} and allergies to {string}")
    public void the_customer_updates_preferences_to_and_allergies_to(String preferences, String allergies)
    {
        assertNotNull(customer);
        customerManager.updatePreferences(customer.getName(), preferences, allergies);
    }

    @Then("the system should store {string} and {string}")
    public void the_system_should_store_and(String preferences, String allergies) 
    {
        assertEquals(preferences, customer.getDietaryPreferences());
        assertEquals(allergies, customer.getAllergies());
    }
    
    @Given("the chef wants to view {string}'s order history")
    public void the_chef_wants_to_view_s_order_history(String string)
    {
       customer=customerManager.getCustomer(string);
       assertNotNull(customer);
    }

    @When("the chef requests the order history")
    public void the_chef_requests_the_order_history() 
    {
        assertNotNull(customer);
    }

    @Then("the system provides {string} and {string}")
    public void the_system_provides_and(String expectedPreferences, String expectedAllergies) 
    {
        assertEquals(expectedPreferences, customer.getDietaryPreferences());
        assertEquals(expectedAllergies, customer.getAllergies());
    }
    
}
