package parsers.json;

import parsers.CalculationEngine;

public class JsonExpression {
    public String expression1;
    public String expression2;
    public String expression3;

    public JsonExpression(String expression1, String expression2, String expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    public JsonExpression Calculate() {
        var calculatedExp1 = CalculationEngine.CalculateLine(expression1);
        var calculatedExp2 = CalculationEngine.CalculateLine(expression2);
        var calculatedExp3 = CalculationEngine.CalculateLine(expression3);

        return new JsonExpression(calculatedExp1, calculatedExp2, calculatedExp3);
    }
}
