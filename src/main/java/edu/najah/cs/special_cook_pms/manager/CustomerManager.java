package edu.najah.cs.special_cook_pms.manager;

import edu.najah.cs.special_cook_pms.model.Customer;

import java.util.*;

public class CustomerManager 
{
    private final Map<String, Customer> customers = new HashMap<>();
    private static final List<String> availableIngredients = Arrays.asList(
        "Chicken", "Rice", "Spices", 
        "Tofu", "Broccoli", "Carrots",
        "Beef", "Milk", "Fish", "Tomato",
        "Peanuts" 
    );

    private boolean incompatibleCombinationDetected = false;
    private boolean emptyIngredientsProvided = false;

    public boolean registerCustomer(String name) 
    {
        if (name == null || name.trim().isEmpty())
        {
            System.out.println("❌ Error: Invalid customer name...");
            return false;
        }
        if (customers.containsKey(name))
        {
            System.out.println("⚠️ Customer already exists...");
            return false;
        }
        customers.put(name, new Customer(name));
        return true;
    }

    public boolean updatePreferences(String name, String dietaryPreferences, String allergies)
    {
        Customer customer = customers.get(name);
        if (customer == null) 
        {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        customer.setDietaryPreferences(dietaryPreferences);
        customer.setAllergies(allergies);
        return true;
    }

    public Customer getCustomer(String name) 
    {
        return customers.get(name);
    }

    public boolean isRegistered(String name)
    {
        return customers.containsKey(name);
    }

    public String[] getCustomerPrefrences(String name)
    {
        Customer customer = customers.get(name);
        if (customer == null)
        {
            System.out.println("❌ Error: Customer not found.");
            return null;
        }
        return new String[] {customer.getDietaryPreferences(), customer.getAllergies()};
    }

    public boolean placeOrder(String customerName, String mealName)
    {
        Customer customer = customers.get(customerName);
        if (customer == null)
        {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        if (mealName == null || mealName.trim().isEmpty())
        {
            System.out.println("❌ Error: Invalid meal name.");
            return false;
        }
        customer.addOrder(mealName);
        return true;
    }

    public List<String> getOrderHistory(String customerName)
    {
        Customer customer = customers.get(customerName);
        if (customer == null)
        {
            System.out.println("❌ Error: Customer not found.");
            return null;
        }
        return customer.getOrderHistory();
    }

    public boolean reorderMeal(String customerName, String mealName)
    {
        Customer customer = customers.get(customerName);
        if (customer == null)
        {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        if (!customer.getOrderHistory().contains(mealName))
        {
            System.out.println("❌ Error: Meal not found in order history.");
            return false;
        }

        customer.addOrder(mealName);
        System.out.println("✅ Meal reordered successfully for " + customerName + ": " + mealName);
        return true;
    }

    public boolean requestCustomMeal(String customerName, List<String> ingredients)
    {
        Customer customer = customers.get(customerName);
        if (customer == null)
        {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }

        incompatibleCombinationDetected = false;
        emptyIngredientsProvided = false;

        if (ingredients == null || ingredients.isEmpty()) 
        {
            System.out.println("❌ Error: No ingredients provided.");
            emptyIngredientsProvided = true;
            return false;
        }

        System.out.println("🔍 Ingredients received: " + ingredients);

        for (String ingredient : ingredients)
        {
            if (!availableIngredients.contains(ingredient))
            {
                System.out.println("❌ Ingredient not available: " + ingredient);
                return false;
            }

            if (customer.getAllergies() != null && !customer.getAllergies().isEmpty())
            {
                List<String> allergyList = Arrays.asList(customer.getAllergies().split(","));
                for (String allergy : allergyList) 
                {
                    if (ingredient.equalsIgnoreCase(allergy.trim())) 
                    {
                        System.out.println("❌ Ingredient conflicts with allergy: " + ingredient);
                        customer.setCustomMealIngredients(new ArrayList<>());
                        return false;
                    }
                }
            }
        }

        if (ingredients.contains("Milk") && ingredients.contains("Fish"))
        {
            incompatibleCombinationDetected = true;
        }

        customer.setCustomMealIngredients(ingredients);
        System.out.println("✅ Custom meal saved for " + customerName);
        return true;
    }

    public List<String> getCustomMeal(String customerName) 
    {
        Customer customer = customers.get(customerName);
        if (customer == null) return null;
        return customer.getCustomMealIngredients();
    }

    public boolean hasIncompatibleCombination()
    {
        return incompatibleCombinationDetected;
    }

    public boolean isEmptyIngredientsProvided() 
    {
        return emptyIngredientsProvided;
    }

    public Set<String> getAllCustomers() 
    {
        return customers.keySet();
    }
}
