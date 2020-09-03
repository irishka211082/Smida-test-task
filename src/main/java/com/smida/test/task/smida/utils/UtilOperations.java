package com.smida.test.task.smida.utils;

import java.math.BigDecimal;

public class UtilOperations {

//    public static double calculateTotalNominalValue(double nominalValue, int sharesNumber) {
//        return nominalValue * sharesNumber;
//    }

        public static BigDecimal calculateTotalNominalValue(double nominalValue, int sharesNumber) {
        return new BigDecimal(nominalValue).multiply(new BigDecimal(sharesNumber));
    }
}
