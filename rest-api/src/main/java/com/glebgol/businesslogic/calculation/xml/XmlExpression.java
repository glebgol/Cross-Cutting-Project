package com.glebgol.businesslogic.calculation.xml;

import com.glebgol.businesslogic.calculation.CalculationEngine;

public class XmlExpression {
    public String expression1;
    public String expression2;
    public String expression3;

    public XmlExpression(String expression1, String expression2, String expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }
    public XmlExpression() {}

    public XmlExpression Calculate() {
        var calculatedExp1 = CalculationEngine.calculateLine(expression1);
        var calculatedExp2 = CalculationEngine.calculateLine(expression2);
        var calculatedExp3 = CalculationEngine.calculateLine(expression3);

        return new XmlExpression(calculatedExp1, calculatedExp2, calculatedExp3);
    }
}
