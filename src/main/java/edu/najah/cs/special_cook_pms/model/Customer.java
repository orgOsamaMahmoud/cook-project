package edu.najah.cs.special_cook_pms.model;

public class Customer
{
    private final String name;
    private final String customerId;
    private String dietaryPreferences;
    private String allergies;

    // New fields for billing system
    private String email;
    private String phone;
    private String address;
    private String preferredInvoiceFormat;

    public Customer(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("❌ Error: Invalid customer name.");
        }
        this.name = name;
        this.dietaryPreferences = "";
        this.allergies = "";
        this.customerId = "CUST-" + System.currentTimeMillis();
        this.preferredInvoiceFormat = "PDF"; // Default format
    }

    public String getName()
    {
        return name;
    }

    public String getCustomerId()
    {
        return customerId;
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

    // New getters and setters for billing system

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPreferredInvoiceFormat()
    {
        return preferredInvoiceFormat;
    }

    public void setPreferredInvoiceFormat(String preferredInvoiceFormat)
    {
        if (preferredInvoiceFormat != null && !preferredInvoiceFormat.trim().isEmpty()) {
            this.preferredInvoiceFormat = preferredInvoiceFormat;
        }
    }
}