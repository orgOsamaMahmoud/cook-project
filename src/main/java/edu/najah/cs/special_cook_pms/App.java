package edu.najah.cs.special_cook_pms;

import edu.najah.cs.special_cook_pms.manager.CustomerManager;
import edu.najah.cs.special_cook_pms.model.Customer;

public class App 
{
    public static void main(String[] args) 
    {
        System.out.println("🚀 Welcome to Special Cook Project");

        CustomerManager customerManager = new CustomerManager();

        // تسجيل زبون جديد
        System.out.println("\n🔵 Registering customer...");
        customerManager.registerCustomer("Mahmoud Yaseen");

        // تحديث التفضيلات والحساسيات
        System.out.println("\n🔵 Updating preferences...");
        customerManager.updatePreferences("Mahmoud Yaseen", "Vegetarian", "Peanuts");

        // عرض التفضيلات للتأكيد
        Customer customer = customerManager.getCustomer("Mahmoud Yaseen");
        if (customer != null) {
            System.out.println("\n✅ Customer preferences:");
            System.out.println("- Dietary: " + customer.getDietaryPreferences());
            System.out.println("- Allergies: " + customer.getAllergies());
        }

        // إضافة طلبات
        System.out.println("\n🔵 Placing orders...");
        customerManager.placeOrder("Mahmoud Yaseen", "Grilled Chicken");
        customerManager.placeOrder("Mahmoud Yaseen", "Pasta Alfredo");

        // عرض سجل الطلبات
        System.out.println("\n✅ Order History:");
        for (String order : customer.getOrderHistory()) {
            System.out.println("- " + order);
        }

        // إعادة طلب وجبة
        System.out.println("\n🔵 Reordering 'Grilled Chicken'...");
        boolean reordered = customerManager.reorderMeal("Mahmoud Yaseen", "Grilled Chicken");
        if (reordered) {
            System.out.println("✅ Meal reordered successfully!");
        } else {
            System.out.println("❌ Failed to reorder meal.");
        }

        // عرض سجل الطلبات المحدث
        System.out.println("\n✅ Updated Order History:");
        for (String order : customer.getOrderHistory()) {
            System.out.println("- " + order);
        }

        System.out.println("\n🏁 End of Demo - 1.1 and 1.2 are Completed Successfully!");
    }
}
