package edu.najah.cs.special_cook_pms.manager;

import edu.najah.cs.special_cook_pms.model.Customer;
import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private final Map<String, Customer> customers = new HashMap<>();

    public boolean registerCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Error: Invalid customer name...");
            return false;
        }
        if (customers.containsKey(name)) {
            System.out.println("⚠️ Customer already exists...");
            return false;
        }
        customers.put(name, new Customer(name));
        return true;
    }

    public boolean updatePreferences(String name, String dietaryPreferences, String allergies) {
        Customer customer = customers.get(name);
        if (customer == null) {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        customer.setDietaryPreferences(dietaryPreferences);
        customer.setAllergies(allergies);
        return true;
    }

    public Customer getCustomer(String name) {
        return customers.get(name);
    }

    public boolean isRegistered(String name) {
        return customers.containsKey(name);
    }
}
