package parsers.json;

import interfaces.IExpression;

public class ExpressionObject implements IExpression {
    public String expression1;
    public String expression2;
    public String expression3;

    public ExpressionObject(String expression1, String expression2, String expression3) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }
}
