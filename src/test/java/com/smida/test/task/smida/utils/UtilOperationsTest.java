package com.smida.test.task.smida.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilOperationsTest {

    @Test
    public void calculateTotalNominalValue() {
        double expectedTotalNominalValue = 100.10;
        double actualTotalNominalValue = UtilOperations.calculateTotalNominalValue(50.05, 2);

        assertEquals(expectedTotalNominalValue, actualTotalNominalValue);
    }
}