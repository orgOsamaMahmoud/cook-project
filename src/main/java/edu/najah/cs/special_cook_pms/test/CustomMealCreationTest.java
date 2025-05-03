package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomMealCreationTest 
{

    private CustomerManager manager;

    @BeforeEach
    public void setUp()
    {
        manager = new CustomerManager();
        manager.registerCustomer("Mahmoud");
    }

    @Test
    public void testCustomMealWithValidIngredients() 
    {
        List<String> ingredients = Arrays.asList("Chicken", "Rice", "Spices");
        boolean result = manager.requestCustomMeal("Mahmoud", ingredients);
        assertTrue(result);
        List<String> savedMeal = manager.getCustomMeal("Mahmoud");
        assertEquals(ingredients, savedMeal);
    }

    @Test
    public void testCustomMealWithUnavailableIngredient() 
    {
        List<String> ingredients = Arrays.asList("Chicken", "Chocolate");
        boolean result = manager.requestCustomMeal("Mahmoud", ingredients);
        assertFalse(result);
        List<String> savedMeal = manager.getCustomMeal("Mahmoud");
        assertTrue(savedMeal == null || savedMeal.isEmpty());
    }

    @Test
    public void testCustomMealWithEmptyIngredients()
    {
        boolean result = manager.requestCustomMeal("Mahmoud", Arrays.asList());
        assertFalse(result);
        assertTrue(manager.isEmptyIngredientsProvided());
    }
}
