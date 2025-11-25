# 🍽️ Cook Project — Kitchen & Cooking Task Management System  
A Java-based cooking and kitchen-management system built using **Object-Oriented Programming**,  
**Manager classes**, **Model classes**, and **Cucumber (Gherkin)** for behavior-driven testing.

The system simulates real kitchen operations such as chefs, cooking tasks, delivery, suppliers, inventory, notifications, and customer orders.

---

## 🚀 Tech Stack

### **Languages & Tools**
- **Java** (OOP)
- **Cucumber (Gherkin)** for BDD testing  
- **JUnit** for unit tests  
- **Maven** (pom.xml)  
- **Git / GitHub Actions**

---

## 🧩 System Overview

The project follows a modular OOP structure with 3 core layers:

### 1️⃣ **Model Layer**  
Contains all entities (objects) used in the system:
- Chef  
- Customer  
- Supplier  
- Kitchen  
- Invoice  
- Inventory  
- CookingTask  
- Delivery  
- Notification  

🧱 Each model represents a real object in the cooking workflow.

---

### 2️⃣ **Manager Layer**  
The "brain" of the system — contains all business logic.

Example managers:
- **ChefManager** – assign chefs, track chef workload  
- **CookingTaskManager** – create + manage cooking tasks  
- **CustomerManager** – handle customer orders  
- **InventoryManager** – add/remove inventory items  
- **DeliveryManager** – schedule deliveries  
- **InvoiceManager** – generate invoices  
- **NotificationManager** – send notifications  
- **KitchenManager** – central operation controller  

✔ Each manager includes functions tested using JUnit + Cucumber.

---

### 3️⃣ **Context Layer**
- Glue code for **Cucumber**
- Step Definitions  
- TestContext.java  
- Shared data between steps  

BDD folder:  
```
src/test/resources/features/*.feature
```

---

## 📂 Project Structure

```
src/
 ├── main/
 │   └── java/edu/najah/cs/special_cook_pms/
 │         ├── model/
 │         ├── manager/
 │         ├── context/
 │         └── App.java
 └── test/
     ├── java/...
     └── resources/features/
```

---

## 🧪 Testing (Cucumber + JUnit)

### ✔ Unit Tests  
- Each manager has JUnit tests  
- Tests business logic (task creation, inventory updates, etc.)

### ✔ BDD Tests (Gherkin)
Feature examples:
```
Feature: Cooking management
  Scenario: Assign chef to cooking task
    Given a chef exists
    And a cooking task is created
    When the chef is assigned to the task
    Then the task should appear in the chef’s active tasks
```

Executed using:
```bash
mvn test
```

---

## ▶️ How to Run the Program

### Run main application:
```bash
mvn clean package
java -cp target/cook-project.jar edu.najah.cs.special_cook_pms.App
```

---

## 🎯 Features Implemented

### 🍳 Cooking Task System  
- Create and manage tasks  
- Assign chefs  
- Track completion

### 🍽 Inventory System  
- Add/remove ingredients  
- Check stock  
- Lock items for tasks

### 👨‍🍳 Chef Management  
- Chef assignment  
- Activity tracking  
- Availability checks

### 🚚 Delivery Module  
- Schedule deliveries  
- Track delivery status

### 🧾 Invoice & Customer System  
- Generate invoices  
- Customer order tracking

### 🔔 Notifications  
- Trigger notifications for events (task done, delivery complete, etc.)

---

## ⭐ Contributors  
- Mahmoud Yaseen  
- osama jamal

