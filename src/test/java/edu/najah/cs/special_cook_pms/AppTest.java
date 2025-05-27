package edu.najah.cs.special_cook_pms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    @Test
    void testMainMethodExecutesSuccessfully() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
