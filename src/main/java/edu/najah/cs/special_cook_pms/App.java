package edu.najah.cs.special_cook_pms;

import edu.najah.cs.special_cook_pms.manager.*;
import edu.najah.cs.special_cook_pms.manager.KitchenManager;
import edu.najah.cs.special_cook_pms.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("Special Cook Personal Management System");
        System.out.println("=====================================\n");

        CustomerManager customerManager = new CustomerManager();
        KitchenManager kitchenManager = new KitchenManager("Head Kitchen");
        ChefManager chefManager = new ChefManager();
        NotificationManager notificationManager = new NotificationManager();
        InventoryManager inventoryManager = new InventoryManager(notificationManager);
        DeliveryManager deliveryManager = new DeliveryManager();
        InvoiceManager invoiceManager = new InvoiceManager();
        ReportingManager reportingManager = new ReportingManager();
        SupplierService supplierService = new SupplierService();

        System.out.println("Registering customers...");
        customerManager.registerCustomer("Alice");
        customerManager.registerCustomer("Bob");
        customerManager.updatePreferences("Alice", "Vegetarian", "Dairy,Peanuts");
        customerManager.updateContactInfo("Alice", "alice@example.com", "555-123-4567", "123 Main St");
        customerManager.updateContactInfo("Bob", "bob@example.com", "555-789-0123", "456 Oak Ave");

        System.out.println("\nRegistering chefs...");
        Chef chef1 = chefManager.registerChef("Gordon");
        Chef chef2 = chefManager.registerChef("Julia");
        kitchenManager.addChef(chef1);
        kitchenManager.addChef(chef2);

        System.out.println("\nStocking inventory...");
        inventoryManager.addIngredient("Chicken", 50);
        inventoryManager.addIngredient("Rice", 100);
        inventoryManager.addIngredient("Spices", 30);
        inventoryManager.addIngredient("Tofu", 20);
        inventoryManager.addIngredient("Broccoli", 15);
        inventoryManager.setThreshold("Chicken", 20);
        inventoryManager.setThreshold("Broccoli", 20);

        System.out.println("\nChecking inventory levels...");
        List<String> lowStock = inventoryManager.checkLowStockIngredients();
        if (!lowStock.isEmpty()) {
            System.out.println("Low stock items: " + lowStock);
            inventoryManager.notifyLowStock(kitchenManager);
        }

        System.out.println("\nProcessing customer orders...");
        customerManager.placeOrder("Alice", "Tofu Stir Fry");
        customerManager.placeOrder("Bob", "Chicken Rice Bowl");

        List<String> customIngredients = new ArrayList<>(Arrays.asList("Tofu", "Broccoli", "Rice"));
        customerManager.requestCustomMeal("Alice", customIngredients);

        System.out.println("\nScheduling cooking tasks...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            CookingTask task1 = chefManager.scheduleCookingTask("Gordon", "ORDER-1", "2023-05-20 10:00:00");
            chefManager.addPreparationRequirement(task1.getTaskId(), "Mise en place", "Prep vegetables 30 min before");

            CookingTask task2 = chefManager.scheduleCookingTask("Julia", "ORDER-2", "2023-05-20 11:30:00");
            chefManager.addPreparationRequirement(task2.getTaskId(), "Sauce preparation", "Prepare special sauce");

            System.out.println("\nCreating urgent cooking task...");
            CookingTask urgentTask = chefManager.createUrgentTask("ORDER-URGENT");
            if (urgentTask != null) {
                System.out.println("Urgent task assigned to: " + urgentTask.getChefId());
            }

            System.out.println("\nSending daily schedules to chefs...");
            Date scheduleDate = sdf.parse("2023-05-20 00:00:00");
            List<CookingTask> gordonTasks = chefManager.getTasksForDate("Gordon", scheduleDate);
            notificationManager.sendDailySchedule(chef1, gordonTasks, scheduleDate);

            System.out.println("\nScheduling deliveries...");
            Delivery delivery1 = deliveryManager.scheduleDelivery("Alice", "ORDER-1", "2023-05-20 12:30:00");
            Delivery delivery2 = deliveryManager.scheduleDelivery("Bob", "ORDER-2", "2023-05-20 13:45:00");

            System.out.println("\nSetting up delivery reminders...");
            Customer alice = customerManager.getCustomer("Alice");
            notificationManager.scheduleDeliveryReminders(alice, delivery1);

            System.out.println("\nGenerating invoices...");
            Order order1 = new Order("ORDER-1", "Alice");
            order1.addItem(new OrderItem("ITEM-1", "Tofu Stir Fry", 1, 15.99));

            Order order2 = new Order("ORDER-2", "Bob");
            order2.addItem(new OrderItem("ITEM-2", "Chicken Rice Bowl", 1, 12.99));

            Invoice invoice1 = invoiceManager.generateInvoice("Alice", "ORDER-1", order1);
            Invoice invoice2 = invoiceManager.generateInvoice("Bob", "ORDER-2", order2);

            customerManager.addInvoiceToCustomer("Alice", invoice1);
            customerManager.addInvoiceToCustomer("Bob", invoice2);

            invoiceManager.sendInvoice(invoice1, alice);

            System.out.println("\nGenerating financial reports...");
            FinancialReport monthlyReport = reportingManager.generateMonthlyRevenueReport(new Date());
            System.out.println("Monthly report generated with " + monthlyReport.getEntries().size() + " entries");

            System.out.println("\nProcessing scheduled notifications...");
            notificationManager.processScheduledNotifications();

        } catch (ParseException e) {
            System.out.println("❌ Error with date parsing: " + e.getMessage());
        }

        System.out.println("\n=====================================");
        System.out.println("System operations completed successfully");
        System.out.println("=====================================");
    }
}