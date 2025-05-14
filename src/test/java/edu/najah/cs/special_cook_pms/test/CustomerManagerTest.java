package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerManagerTest {

    private CustomerManager manager;

    @BeforeEach
    void setUp() {
        manager = new CustomerManager();
    }

    @Test
    void testRegisterCustomerValid() {
        boolean result = manager.registerCustomer("Omar");
        assertTrue(result);
        assertNotNull(manager.getCustomer("Omar"));
    }

    @Test
    void testRegisterCustomerDuplicate() {
        manager.registerCustomer("Omar");
        boolean result = manager.registerCustomer("Omar");
        assertFalse(result);
    }

    @Test
    void testUpdatePreferences() {
        manager.registerCustomer("Salem");
        boolean updated = manager.updatePreferences("Salem", "Vegan", "Milk");
        assertTrue(updated);

        String[] prefs = manager.getCustomerPrefrences("Salem");
        assertEquals("Vegan", prefs[0]);
        assertEquals("Milk", prefs[1]);
    }

    @Test
    void testPlaceOrderAndHistory() {
        manager.registerCustomer("Yousef");
        assertTrue(manager.placeOrder("Yousef", "Chicken"));
        List<String> history = manager.getOrderHistory("Yousef");
        assertEquals(1, history.size());
        assertEquals("Chicken", history.get(0));
    }

    @Test
    void testReorderMealSuccess() {
        manager.registerCustomer("Lina");
        manager.placeOrder("Lina", "Pasta");
        boolean result = manager.reorderMeal("Lina", "Pasta");
        assertTrue(result);
        assertEquals(2, manager.getOrderHistory("Lina").size());
    }

    @Test
    void testReorderMealFailure() {
        manager.registerCustomer("Noor");
        boolean result = manager.reorderMeal("Noor", "Burger");
        assertFalse(result);
    }
}
