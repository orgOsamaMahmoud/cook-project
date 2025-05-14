package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import edu.najah.cs.special_cook_pms.model.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    
    @Test
    void testRequestCustomMeal_AllValid() {
        manager.registerCustomer("Maha");
        manager.updatePreferences("Maha", "Vegetarian", ""); 

        List<String> ingredients = List.of("Rice", "Carrots", "Tofu");
        boolean result = manager.requestCustomMeal("Maha", ingredients);

        assertTrue(result);
        assertEquals(ingredients, manager.getCustomMeal("Maha"));
    }
    
    @Test
    void testRequestCustomMeal_UnavailableIngredient() {
        manager.registerCustomer("Ali");

        List<String> ingredients = List.of("Dragonfruit");
        boolean result = manager.requestCustomMeal("Ali", ingredients);

        assertFalse(result);
        assertTrue(manager.isIngredientUnavailable());
    }

    @Test
    void testRequestCustomMeal_WithAllergy() {
        manager.registerCustomer("Khaled");
        manager.updatePreferences("Khaled", "", "Milk");

        List<String> ingredients = List.of("Rice", "Milk");
        boolean result = manager.requestCustomMeal("Khaled", ingredients);

        assertFalse(result);
        assertTrue(manager.hasAllergyConflict());
    }
    
    @Test
    void testRequestCustomMeal_IncompatibleCombo() {
        manager.registerCustomer("Salma");

        List<String> ingredients = List.of("Milk", "Fish");
        boolean result = manager.requestCustomMeal("Salma", ingredients);

        assertFalse(result);
        assertTrue(manager.hasIncompatibleCombination());
    }
    
    @Test
    void testRequestCustomMeal_EmptyIngredients() {
        manager.registerCustomer("Nour");

        boolean result = manager.requestCustomMeal("Nour", new ArrayList<>());
        assertFalse(result);
        assertTrue(manager.isEmptyIngredientsProvided());
    }

    @Test
    void testSuggestAlternatives_AllergyAndUnavailable() {
        manager.registerCustomer("Dana");
        manager.updatePreferences("Dana", "", "Peanuts");

        List<String> ingredients = List.of("Peanuts", "Dragonfruit");
        Map<String, List<String>> suggestions = manager.suggestAlternatives("Dana", ingredients);

        assertTrue(suggestions.containsKey("Peanuts"));
        assertTrue(suggestions.containsKey("Dragonfruit"));

        assertEquals(List.of("Sunflower Butter", "Pumpkin Seeds"), suggestions.get("Peanuts"));
        assertEquals(List.of("Apple", "Pear"), suggestions.get("Dragonfruit"));
    }
    
    @Test
    void testSuggestAlternatives_NoSuggestions() {
        manager.registerCustomer("Nada");
        manager.updatePreferences("Nada", "", "");

        List<String> ingredients = List.of("Rice", "Carrots"); 
        Map<String, List<String>> suggestions = manager.suggestAlternatives("Nada", ingredients);

        assertTrue(suggestions.isEmpty());
    }

    
    @Test
    void testSuggestAlternatives_CustomerNotFound() {
        Map<String, List<String>> result = manager.suggestAlternatives("Ghost", List.of("Fish"));
        assertTrue(result.isEmpty());
    }
    
    @Test
    void testUpdateContactInfo() {
        manager.registerCustomer("Alaa");
        boolean updated = manager.updateContactInfo("Alaa", "alaa@email.com", "05999999", "Nablus");
        assertTrue(updated);
    }
    @Test
    void testUpdateContactInfo_CustomerNotFound()
    {
        boolean updated = manager.updateContactInfo("Ghost", "ghost@email.com", "000", "Void");
        assertFalse(updated);
    }
    
    @Test
    void testAddInvoiceToCustomer() 
    {
        manager.registerCustomer("Ibrahim");
        Invoice invoice = new Invoice("Ibrahim", "ORD001");
        invoice.setTotalAmount(45.0);
        invoice.setStatus("PAID");
        invoice.setFormat("PDF");
        manager.addInvoiceToCustomer("Ibrahim", invoice);

        List<Invoice> invoices = manager.getCustomerInvoices("Ibrahim");
        assertEquals(1, invoices.size());
        assertEquals("ORD001", invoices.get(0).getOrderId());
    }

    
    @Test
    void testAddInvoiceToCustomer_CustomerNotFound() 
    {
        Invoice invoice = new Invoice("Ghost", "ORD404");
        invoice.setTotalAmount(99.0);

        manager.addInvoiceToCustomer("Ghost", invoice); 
        List<Invoice> result = manager.getCustomerInvoices("Ghost");

        assertTrue(result.isEmpty());
    }
    
    @Test
    void testSetPreferredInvoiceFormat() {
        manager.registerCustomer("Hana");
        boolean result = manager.setPreferredInvoiceFormat("Hana", "HTML");
        assertTrue(result);

        Customer hana = manager.getCustomer("Hana");
        assertEquals("HTML", hana.getPreferredInvoiceFormat());
    }
    
    @Test
    void testSetPreferredInvoiceFormat_CustomerNotFound() {
        boolean result = manager.setPreferredInvoiceFormat("Fake", "PDF");
        assertFalse(result);
    }
    
    @Test
    void testGetCustomerInvoices_Empty() {
        manager.registerCustomer("Omar");
        List<Invoice> result = manager.getCustomerInvoices("Omar");
        assertTrue(result.isEmpty());
    }

}
