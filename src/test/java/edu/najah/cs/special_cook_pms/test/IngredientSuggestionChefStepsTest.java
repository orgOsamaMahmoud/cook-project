package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.context.TestContext;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientSuggestionChefStepsTest 
{

    private TestContext context;

    @BeforeEach
    public void setUp() 
    {
        context = new TestContext();
        context.customerManager.registerCustomer("Ali");
        Customer customer = context.customerManager.getCustomer("Ali");
        customer.setAllergies("Peanuts,Milk");
    }

    @Test
    public void testSuggestAlternativesForAllergies()
    {
        List<String> ingredients = Arrays.asList("Peanuts", "Milk", "Beef");
        context.suggestedAlternatives = context.customerManager.suggestAlternatives("Ali", ingredients);
        assertTrue(context.suggestedAlternatives.containsKey("Peanuts"));
        assertTrue(context.suggestedAlternatives.containsKey("Milk"));
    }

    @Test
    public void testSuggestAlternativesForUnavailableIngredient()
    {
        List<String> ingredients = Arrays.asList("Dragonfruit");
        context.suggestedAlternatives = context.customerManager.suggestAlternatives("Ali", ingredients);
        assertTrue(context.suggestedAlternatives.containsKey("Dragonfruit"));
    }
}
