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
    
    @Given("the chef wants to prepare a meal for {string}")
    public void the_chef_wants_to_prepare_a_meal_for(String name) {
        customer = customerManager.getCustomer(name);
        assertNotNull(customer);
    }

    @When("the chef requests customer dietary preferences")
    public void the_chef_requests_customer_dietary_preferences() {
        assertNotNull(customer.getDietaryPreferences());
        assertNotNull(customer.getAllergies());
    }

    @Given("the customer {string} has placed previous orders")
    public void the_customer_has_placed_previous_orders(String name) {
        customerManager.registerCustomer(name);
        customer = customerManager.getCustomer(name);
        customer.addOrder("Grilled Chicken"); 
    }

    @When("the customer views their order history")
    public void the_customer_views_their_order_history() {
        assertNotNull(customer.getOrderHistory());
    }

    @Then("the system should display a list of their past meal orders")
    public void the_system_should_display_a_list_of_their_past_meal_orders() {
        assertFalse(customer.getOrderHistory().isEmpty());
    }

    @Given("the customer {string} has ordered {string} before")
    public void the_customer_has_ordered_before(String name, String meal) {
        customerManager.registerCustomer(name);
        customer = customerManager.getCustomer(name);
        customer.addOrder(meal);
    }

    @When("the customer selects {string} to reorder")
    public void the_customer_selects_to_reorder(String meal) {
        customer.addOrder(meal); 
    }

    @Then("the system should confirm that the order has been placed again")
    public void the_system_should_confirm_that_the_order_has_been_placed_again() {
        assertTrue(customer.getOrderHistory().size() > 1);
    }

    @Then("the system should provide a list of meals ordered by {string}")
    public void the_system_should_provide_a_list_of_meals_ordered_by(String name) {
        Customer customer = customerManager.getCustomer(name);
        assertNotNull(customer);
        assertFalse(customer.getOrderHistory().isEmpty());
    }

    @Given("the customer {string} places an order for {string}")
    public void the_customer_places_an_order_for(String name, String meal) {
        customerManager.registerCustomer(name);
        customer = customerManager.getCustomer(name);
        customer.addOrder(meal);
    }

    @When("the system processes the order")
    public void the_system_processes_the_order() {
        assertNotNull(customer.getOrderHistory());
    }

    @Then("the order should be stored in {string}'s order history")
    public void the_order_should_be_stored_in_s_order_history(String name) {
        Customer customer = customerManager.getCustomer(name);
        assertNotNull(customer);
        assertFalse(customer.getOrderHistory().isEmpty());
    }

    
}
