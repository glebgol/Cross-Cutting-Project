package com.glebgol.businesslogic.calculation;

import org.mariuszgromada.math.mxparser.Expression;

public class CalculationEngine {
    private static final String DivisionByZeroMessage = "Division by zero!";
    public static String calculateLine(String line) {
        Expression expression = new Expression(line);
        if (expression.checkSyntax()) {
            double result = expression.calculate();
            if (Double.isNaN(result)) {
                return DivisionByZeroMessage;
            }
            return String.valueOf(result);
        }
        return line;
    }
}
