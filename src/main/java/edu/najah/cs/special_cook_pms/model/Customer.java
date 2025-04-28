package edu.najah.cs.special_cook_pms.model;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
	private List<String>orderHistory;
    private final String name;
    private String dietaryPreferences;
    private String allergies;

    public Customer(String name)
    {
        if (name == null || name.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("❌ Error: Invalid customer name.");
        }
        this.name = name;
        this.dietaryPreferences = "";
        this.allergies = "";
        this.orderHistory=new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public String getDietaryPreferences() 
    
    {
        return dietaryPreferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setDietaryPreferences(String dietaryPreferences) 
    {
        this.dietaryPreferences = dietaryPreferences;
    }

    public void setAllergies(String allergies)
    {
        this.allergies = allergies;
    }
 
    public void addOrder(String mealName)
    {
    	orderHistory.add(mealName);
    }
    
    public List<String> getOrderHistory()
    {
    	return new ArrayList<>(orderHistory);
    }
    
}
