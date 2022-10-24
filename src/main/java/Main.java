import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Expression expression = new ExpressionBuilder("(3+2) * 2 + 1 + log(12)").build();
        var validationResult = expression.validate();
        if (validationResult.isValid()) {
            double result = expression.evaluate();
            System.out.println(result);
        }
    }
}