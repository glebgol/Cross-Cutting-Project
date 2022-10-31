package parsers;

import java.util.regex.Pattern;

public class ArithmeticExpressionParser {
    private static final String RegexBrackets = "\\(([1234567890\\.\\+\\-\\*\\/^%]*)\\)";
    private static final String RegexNum = "[-]?\\d+\\.?\\d*";
    private static final String RegexMulOp = "[\\*\\/^%]";
    private static final String RegexAddOp = "[\\+\\-]";

    private static final String RegexBrackets2 = "(^-|^\\\\+)?[0-9]+((\\\\.)?[0-9]*(e[0-9]+)?)?$";

    public static double Parse(String str) {
        var patternBrackets =  Pattern.compile(RegexBrackets);
        var matchBrackets = patternBrackets.matcher(str);
        while (matchBrackets.find()) {
            System.out.println(matchBrackets.group(0));
            System.out.println(matchBrackets.group(1));
        }

        return 0;
    }
    public static boolean isExpression(String str) {
        return str.matches(RegexBrackets2);
    }
}
