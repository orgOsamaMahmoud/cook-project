package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.context.TestContext;
import edu.najah.cs.special_cook_pms.model.Chef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskNotificationChefStepsTest 
{

    private TestContext context;

    @BeforeEach
    public void setUp()
    {
        context = new TestContext();
    }

    @Test
    public void testChefGetsAssignedTask()
    {
        Chef chef = new Chef("Layla", "Medium", "Desserts");
        context.kitchenManager.addChef(chef);
        context.assignedChef = context.kitchenManager.assignTask("Desserts");
        assertNotNull(context.assignedChef);
        assertEquals("Desserts", context.assignedChef.getExpertise());
    }

    @Test
    public void testChefDoesNotGetTaskWithHighWorkload()
    {
        Chef chef = new Chef("Salem", "High", "Vegan");
        context.kitchenManager.addChef(chef);
        context.assignedChef = context.kitchenManager.assignTask("Vegan");
        assertNull(context.assignedChef);
    }
}
