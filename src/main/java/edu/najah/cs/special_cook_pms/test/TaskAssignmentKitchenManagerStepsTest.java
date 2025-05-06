package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.context.TestContext;
import edu.najah.cs.special_cook_pms.model.Chef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskAssignmentKitchenManagerStepsTest 
{

    private TestContext context;

    @BeforeEach
    public void setUp()
    {
        context = new TestContext();
    }

    @Test
    public void testAssignTaskToChefWithExpertise()
    {
        Chef chef = new Chef("Ahmad", "Low", "Grill");
        context.kitchenManager.addChef(chef);
        context.assignedChef = context.kitchenManager.assignTask("Grill");
        assertNotNull(context.assignedChef);
        assertEquals("Ahmad", context.assignedChef.getName());
    }

    @Test
    public void testFallbackAssignmentWhenNoMatchingExpertise()
    {
        Chef chef = new Chef("Ziad", "Low", "Dessert");
        context.kitchenManager.addChef(chef);
        context.assignedChef = context.kitchenManager.assignTask("Vegan");
        assertNotNull(context.assignedChef);
        assertEquals("Ziad", context.assignedChef.getName());
    }

    @Test
    public void testNoAssignmentWhenAllHighWorkload() 
    {
        Chef chef = new Chef("Salem", "High", "Grill");
        context.kitchenManager.addChef(chef);
        context.assignedChef = context.kitchenManager.assignTask("Grill");
        assertNull(context.assignedChef);
    }
}
