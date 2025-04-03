package edu.najah.cs.special_cook_pms.AcceptanceTest;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class customer_profile {
    private final CustomerManager customerManager = new CustomerManager();
    private Customer customer;

    @Given("the system is running")
    public void the_system_is_running() {
        assertNotNull(customerManager);
    }

    @When("the customer {string} registers")
    public void the_customer_registers(String name) {
        customerManager.registerCustomer(name);  // الاسم الذي يتم تمريره هنا يمكن أن يكون "Mahmoud Yaseen"
    }

    @Then("the system should have {string} registered")
    public void the_system_should_have_registered(String name) {
        assertNotNull(customerManager.getCustomer(name));  // التحقق من أن العميل قد تم تسجيله باستخدام الاسم المرسل
    }

    @Given("the customer {string} is registered")
    public void the_customer_is_registered(String name) {
        customerManager.registerCustomer(name);
        customer = customerManager.getCustomer(name);
    }

    @When("the customer updates preferences to {string} and allergies to {string}")
    public void the_customer_updates_preferences_to_and_allergies_to(String preferences, String allergies) {
        assertNotNull(customer);
        customerManager.updatePreferences(customer.getName(), preferences, allergies);
    }

    @Then("the system should store {string} and {string}")
    public void the_system_should_store_and(String preferences, String allergies) {
        assertEquals(preferences, customer.getDietaryPreferences());
        assertEquals(allergies, customer.getAllergies());
    }
}
