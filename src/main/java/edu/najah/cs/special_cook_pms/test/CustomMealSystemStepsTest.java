package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomMealSystemStepsTest
{

    private CustomerManager customerManager;

    @BeforeEach
    public void setup() {
        customerManager = new CustomerManager();
    }

    @Test
    public void testValidCustomMealCombination() {
        customerManager.registerCustomer("Omar");
        customerManager.updatePreferences("Omar", "Standard", "None");

        List<String> ingredients = Arrays.asList("Chicken", "Tomato");
        boolean result = customerManager.requestCustomMeal("Omar", ingredients);

        assertTrue(result, "System should accept valid combination");
        assertFalse(customerManager.hasIncompatibleCombination(), "No incompatibility should be detected");
    }

    @Test
    public void testIncompatibleCustomMealCombination() {
        customerManager.registerCustomer("Lina");
        customerManager.updatePreferences("Lina", "Standard", "");

        List<String> ingredients = Arrays.asList("Milk", "Fish");
        boolean result = customerManager.requestCustomMeal("Lina", ingredients);

        assertFalse(result, "System should reject incompatible combination");
        assertTrue(customerManager.hasIncompatibleCombination(), "Incompatibility should be detected");
    }

    @Test
    public void testEmptyIngredientSelection() {
        customerManager.registerCustomer("Sara");
        customerManager.updatePreferences("Sara", "Vegetarian", "Peanuts");

        boolean result = customerManager.requestCustomMeal("Sara", List.of());

        assertFalse(result, "System should reject empty ingredient selection");
        assertTrue(customerManager.isEmptyIngredientsProvided(), "Empty input should be flagged");
    }
}
