package com.smida.test.task.smida.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UtilOperationsTest {

    @Test
    public void calculateTotalNominalValue() {
        double nominalValue = 50.05;
        int shareNumber = 2;
        BigDecimal expectedTotalNominalValue = new BigDecimal(nominalValue).multiply(new BigDecimal(shareNumber));
        BigDecimal actualTotalNominalValue = UtilOperations.calculateTotalNominalValue(nominalValue, shareNumber);

        assertEquals(expectedTotalNominalValue, actualTotalNominalValue);
    }
}