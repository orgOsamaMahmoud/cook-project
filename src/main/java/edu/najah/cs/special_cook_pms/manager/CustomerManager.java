package edu.najah.cs.special_cook_pms.manager;

import edu.najah.cs.special_cook_pms.model.Customer;
import edu.najah.cs.special_cook_pms.model.Invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerManager
{
    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, List<Invoice>> customerInvoices = new HashMap<>();

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
        customerInvoices.put(name, new ArrayList<>());
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
        Customer customer=customers.get(name);
        if(customer==null)
        {
            System.out.println("❌ Error: Customer not found.");
            return null;
        }
        return new String[] {customer.getDietaryPreferences(),customer.getAllergies()};
    }

    // New methods for billing system support

    public boolean updateContactInfo(String name, String email, String phone, String address)
    {
        Customer customer = customers.get(name);
        if (customer == null) {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        return true;
    }

    public boolean setPreferredInvoiceFormat(String name, String format)
    {
        Customer customer = customers.get(name);
        if (customer == null) {
            System.out.println("❌ Error: Customer not found.");
            return false;
        }
        customer.setPreferredInvoiceFormat(format);
        return true;
    }

    public void addInvoiceToCustomer(String name, Invoice invoice)
    {
        if (customers.containsKey(name)) {
            if (!customerInvoices.containsKey(name)) {
                customerInvoices.put(name, new ArrayList<>());
            }
            customerInvoices.get(name).add(invoice);
            System.out.println("✅ Invoice added to customer: " + name);
        } else {
            System.out.println("❌ Error: Cannot add invoice - customer not found.");
        }
    }

    public List<Invoice> getCustomerInvoices(String name)
    {
        if (!customerInvoices.containsKey(name)) {
            System.out.println("⚠️ No invoices found for customer: " + name);
            return new ArrayList<>();
        }
        return customerInvoices.get(name);
    }
}