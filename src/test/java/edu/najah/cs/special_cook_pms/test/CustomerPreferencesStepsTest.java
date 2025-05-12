package edu.najah.cs.special_cook_pms.test;
import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerPreferencesStepsTest 
{
	 private CustomerManager customerManager;

	    @BeforeEach
	    void setup() {
	        customerManager = new CustomerManager();
	    }

	    @Test
	    void testVegetarianNoAllergy() {
	        String name = "Omar Salem";
	        customerManager.registerCustomer(name);
	        customerManager.updatePreferences(name, "Vegetarian", "");
	        Customer customer = customerManager.getCustomer(name);
	        assertEquals("Vegetarian", customer.getDietaryPreferences());
	        assertEquals("", customer.getAllergies());
	    }

	    @Test
	    void testGlutenFreeWithPeanutsAllergy() {
	        String name = "Lina Ahmed";
	        customerManager.registerCustomer(name);
	        customerManager.updatePreferences(name, "Gluten-Free", "Peanuts");
	        Customer customer = customerManager.getCustomer(name);
	        assertEquals("Gluten-Free", customer.getDietaryPreferences());
	        assertEquals("Peanuts", customer.getAllergies());
	    }

	    @Test
	    void testUpdateToVeganWithDairyAllergy() {
	        String name = "Sara Khaled";
	        customerManager.registerCustomer(name);
	        customerManager.updatePreferences(name, "Vegan", "Dairy");
	        Customer customer = customerManager.getCustomer(name);
	        assertEquals("Vegan", customer.getDietaryPreferences());
	        assertEquals("Dairy", customer.getAllergies());
	    }
}
