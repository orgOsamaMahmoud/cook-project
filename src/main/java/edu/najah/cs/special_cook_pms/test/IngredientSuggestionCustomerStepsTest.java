package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientSuggestionCustomerStepsTest
{

    private CustomerManager customerManager;

    @BeforeEach
    public void setUp() 
    {
        customerManager = new CustomerManager();
    }

    @Test
    public void testSuggestAlternativesForUnavailableIngredient() 
    {
        customerManager.registerCustomer("Omar Salem");
        List<String> ingredients = Arrays.asList("Dragonfruit", "Beef");
        Map<String, List<String>> result = customerManager.suggestAlternatives("Omar Salem", ingredients);
        assertTrue(result.containsKey("Dragonfruit"));
        assertFalse(result.get("Dragonfruit").isEmpty());
    }

    @Test
    public void testSuggestAlternativesForAllergyConflict() 
    {
        customerManager.registerCustomer("Noor Hasan");
        customerManager.updatePreferences("Noor Hasan", "Standard", "Milk");
        List<String> ingredients = Arrays.asList("Milk", "Chicken");
        Map<String, List<String>> result = customerManager.suggestAlternatives("Noor Hasan", ingredients);
        assertTrue(result.containsKey("Milk"));
        assertFalse(result.get("Milk").isEmpty());
    }

    @Test
    public void testNoAlternativesForValidIngredients() 
    {
        customerManager.registerCustomer("Mahmoud Yaseen");
        customerManager.updatePreferences("Mahmoud Yaseen", "Standard", "None");
        List<String> ingredients = Arrays.asList("Rice", "Chicken");
        Map<String, List<String>> result = customerManager.suggestAlternatives("Mahmoud Yaseen", ingredients);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testSuggestAlternativesForDragonfruit() {
        customerManager.registerCustomer("TestUser");
        List<String> ingredients = Arrays.asList("Dragonfruit", "Beef");
        Map<String, List<String>> result = customerManager.suggestAlternatives("TestUser", ingredients);

        assertNotNull(result);
        assertTrue(result.containsKey("Dragonfruit"));
        assertFalse(result.get("Dragonfruit").isEmpty());
    }

}