package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerManagerTest 
{
    private CustomerManager customerManager;
    private final String testCustomerName = "Mahmoud Yaseen";

    @BeforeEach
    void setup() 
    {
        customerManager = new CustomerManager();
    }

    @Test
    void testRegisterCustomer() 
    {
        assertTrue(customerManager.registerCustomer(testCustomerName));
        assertNotNull(customerManager.getCustomer(testCustomerName));
    }

    @Test
    void testUpdatePreferences()
    {
        customerManager.registerCustomer(testCustomerName);
        assertTrue(customerManager.updatePreferences(testCustomerName, "Vegetarian", "Peanuts"));

        Customer customer = customerManager.getCustomer(testCustomerName);
        assertEquals("Vegetarian", customer.getDietaryPreferences());
        assertEquals("Peanuts", customer.getAllergies());
    }

    @Test
    void testGetNonExistentCustomer() 
    {
        assertNull(customerManager.getCustomer("Unknown"));
    }
    
    @Test
    void testChefViewsCustomerPreferences() 
    {
        customerManager.registerCustomer(testCustomerName);
        customerManager.updatePreferences(testCustomerName, "Vegetarian", "Peanuts");

        String[] preferences = customerManager.getCustomerPrefrences(testCustomerName);
        assertNotNull(preferences);
        assertEquals("Vegetarian", preferences[0]);
        assertEquals("Peanuts", preferences[1]);
    }
    
    @Test
    void testPlaceOrderAndGetOrderHistory() {
        customerManager.registerCustomer(testCustomerName);
        assertTrue(customerManager.placeOrder(testCustomerName, "Grilled Chicken"));
        assertTrue(customerManager.placeOrder(testCustomerName, "Pasta Alfredo"));

        Customer customer = customerManager.getCustomer(testCustomerName);
        assertNotNull(customer);
        assertEquals(2, customer.getOrderHistory().size());
        assertEquals("Grilled Chicken", customer.getOrderHistory().get(0));
        assertEquals("Pasta Alfredo", customer.getOrderHistory().get(1));
    }

    @Test
    void testReorderMealSuccessfully() {
        customerManager.registerCustomer(testCustomerName);
        customerManager.placeOrder(testCustomerName, "Grilled Chicken");

        assertTrue(customerManager.reorderMeal(testCustomerName, "Grilled Chicken"));

        Customer customer = customerManager.getCustomer(testCustomerName);
        assertNotNull(customer);
        assertEquals(2, customer.getOrderHistory().size());
        assertEquals("Grilled Chicken", customer.getOrderHistory().get(0));
        assertEquals("Grilled Chicken", customer.getOrderHistory().get(1));
    }

    @Test
    void testReorderMealFailIfMealNotOrderedBefore() {
        customerManager.registerCustomer(testCustomerName);
        assertFalse(customerManager.reorderMeal(testCustomerName, "Shawarma"));
    }

}
