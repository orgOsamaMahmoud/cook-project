Feature: Customer Profile Management

  Scenario: Register a new customer
    Given the system is running
    When the customer "Mahmoud Yaseen" registers 
    Then the system should have "Mahmoud Yaseen" registered  

  Scenario: Store dietary preferences and allergies
    Given the customer "Mahmoud Yaseen" is registered       
    When the customer updates preferences to "Vegetarian" and allergies to "Peanuts"
    Then the system should store "Vegetarian" and "Peanuts"
    
 Scenario: Chef views customer dietary preferences
    Given the chef wants to prepare a meal for "Mahmoud Yaseen"
    When the chef requests customer dietary preferences
    Then the system provides "Vegetarian" and "Peanuts"

  # ✅ 1.2 تتبع الطلبات السابقة وتخصيص الوجبات
  Scenario: Customer views past meal orders
    Given the customer "Mahmoud Yaseen" has placed previous orders
    When the customer views their order history
    Then the system should display a list of their past meal orders

  Scenario: Customer reorders a meal
    Given the customer "Mahmoud Yaseen" has ordered "Grilled Chicken" before
    When the customer selects "Grilled Chicken" to reorder
    Then the system should confirm that the order has been placed again

  Scenario: Chef views customer order history
    Given the chef wants to view "Mahmoud Yaseen"'s order history
    When the chef requests the order history
    Then the system should provide a list of meals ordered by "Mahmoud Yaseen"

  Scenario: Store customer order history
    Given the customer "Mahmoud Yaseen" places an order for "Pasta Alfredo"
    When the system processes the order
    Then the order should be stored in "Mahmoud Yaseen"'s order history