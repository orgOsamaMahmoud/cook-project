package edu.najah.cs.special_cook_pms.test;
import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class SystemOrderAnalysisStepsTest
{
	  private CustomerManager customerManager;

	    @BeforeEach
	    void setup() 
	    {
	        customerManager = new CustomerManager();
	    }

	    @Test
	    void testAnalyzeMealFrequency() 
	    {
	        customerManager.registerCustomer("Omar Salem");
	        customerManager.placeOrder("Omar Salem", "Grilled Chicken");
	        customerManager.placeOrder("Omar Salem", "Grilled Chicken");
	        customerManager.placeOrder("Omar Salem", "Pasta");
	        customerManager.placeOrder("Omar Salem", "Grilled Chicken");

	        Map<String, Integer> frequencies = getMealFrequencies(customerManager.getCustomer("Omar Salem").getOrderHistory());
	        assertEquals(3, frequencies.get("Grilled Chicken"));
	        assertEquals(1, frequencies.get("Pasta"));
	    }

	    @Test
	    void testNoOrdersReturnsEmptyAnalysis() 
	    {
	        customerManager.registerCustomer("Fadi Naser");
	        Customer customer = customerManager.getCustomer("Fadi Naser");
	        List<String> history = customer.getOrderHistory();
	        Map<String, Integer> frequencies = getMealFrequencies(history);
	        assertTrue(frequencies.isEmpty());
	    }

	    @Test
	    void testMultipleCustomersTrendAnalysis() 
	    {
	        customerManager.registerCustomer("Lina Ahmed");
	        customerManager.registerCustomer("Omar Salem");
	        customerManager.placeOrder("Lina Ahmed", "Pasta");
	        customerManager.placeOrder("Lina Ahmed", "Pasta");
	        customerManager.placeOrder("Omar Salem", "Pasta");
	        customerManager.placeOrder("Omar Salem", "Grilled Chicken");

	        Map<String, Integer> globalTrend = new HashMap<>();
	        for (String name : new String[]{"Lina Ahmed", "Omar Salem"})
	        {
	            List<String> orders = customerManager.getCustomer(name).getOrderHistory();
	            for (String meal : orders) 
	            {
	                globalTrend.put(meal, globalTrend.getOrDefault(meal, 0) + 1);
	            }
	        }

	        assertEquals(3, globalTrend.get("Pasta"));
	        assertEquals(1, globalTrend.get("Grilled Chicken"));
	    }

	    private Map<String, Integer> getMealFrequencies(List<String> orders)
	    {
	        Map<String, Integer> freq = new HashMap<>();
	        for (String meal : orders)
	        {
	            freq.put(meal, freq.getOrDefault(meal, 0) + 1);
	        }
	        return freq;
	    }
}
