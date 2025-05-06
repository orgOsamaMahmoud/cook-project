package edu.najah.cs.special_cook_pms.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.manager.KitchenManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import edu.najah.cs.special_cook_pms.model.Chef;

public class TestContext 
{
    public CustomerManager customerManager = new CustomerManager();
    public KitchenManager kitchenManager = new KitchenManager(); 
    public Customer customer;
    public boolean requestSuccessful;
    public Map<String, Object> sessionData = new HashMap<>();
    public List<String> orderList;
    public Map<String, Integer> trendAnalysis;
    public List<String> retrievedOrders;
    public String topMeal;
    public Map<String, List<String>> suggestedAlternatives;
    public List<String> selectedIngredients;
    public Chef assignedChef;

}
