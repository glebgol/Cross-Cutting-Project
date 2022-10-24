public class ArithmeticExpressionParser {
    private static final String RegexBrackets = "\\(([1234567890\\.\\+\\-\\*\\/^%]*)\\)";
    private static final String RegexNum = "[-]?\\d+\\.?\\d*";
    private static final String RegexMulOp = "[\\*\\/^%]";
    private static final String RegexAddOp = "[\\+\\-]";
}
