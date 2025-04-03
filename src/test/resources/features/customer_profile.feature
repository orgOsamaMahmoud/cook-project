Feature: Customer Profile Management

  Scenario: Register a new customer
    Given the system is running
    When the customer "Mahmoud Yaseen" registers   # تم تعديل الاسم هنا
    Then the system should have "Mahmoud Yaseen" registered   # تم تعديل الاسم هنا

  Scenario: Store dietary preferences and allergies
    Given the customer "Mahmoud Yaseen" is registered   # تم تعديل الاسم هنا
    When the customer updates preferences to "Vegetarian" and allergies to "Peanuts"
    Then the system should store "Vegetarian" and "Peanuts"

  # 1.2 تتبع الطلبات السابقة وخطط الوجبات الشخصية  
#  Scenario: Customer views past orders  
  #  Given the customer has placed previous meal orders  
   # When the customer accesses their order history  
   # Then the system should display a list of past orders  

 # Scenario: Chef accesses customer order history  
   # Given a customer has placed previous meal orders  
  #  When a chef views the customer's profile  
  #  Then the chef should see the customer's order history  

 # Scenario: System stores and retrieves order history  
  #  Given multiple customers have placed meal orders  
  #  When the system administrator accesses customer order history  
  #  Then the system should retrieve and display past orders for analysis  
