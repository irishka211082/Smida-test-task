package com.smida.test.task.smida.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UtilOperationsTest {

    @Test
    public void calculateTotalNominalValue() {
        BigDecimal expectedTotalNominalValue = new BigDecimal(100.10);
        BigDecimal actualTotalNominalValue = UtilOperations.calculateTotalNominalValue(50.05, 2);

        assertEquals(expectedTotalNominalValue, actualTotalNominalValue);
    }
}