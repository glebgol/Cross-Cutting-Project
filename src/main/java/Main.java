import org.mariuszgromada.math.mxparser.Expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("(3+2) * 2 + 1 + ln(12)");
        System.out.println(expression.calculate());
    }
}