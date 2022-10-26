import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessingTxtFile {
    private static ArrayList<Token> GetTokens(Expression expression) {
        var tokens = expression.getCopyOfInitialTokens();
        ArrayList<Token> tokenArrayList = new ArrayList<Token>(tokens.size());
        tokenArrayList.addAll(tokens);
        return tokenArrayList;
    }

    private static boolean CheckSyntax(String str) {
        Expression expression = new Expression(str);
        return  expression.checkSyntax();
    }

    public static String CalculateLine(String line) {
//        Expression expression = new Expression(line);
//        var tokens = GetTokens(expression);
//        String resultLine;
//
//        int k = 0;
//        for (int i = k; i < tokens.size(); i++) {
//            StringBuilder expressionString = new StringBuilder(tokens.get(i).tokenStr);
//            for (int j = k + 1; j < tokens.size(); j++) {
//                expressionString.append(tokens.get(j).tokenStr);
//                if (CheckSyntax(expressionString.toString())) {
//
//                }
//            }
//        }
//        return "";
        Expression expression = new Expression(line);
        if (expression.checkSyntax()) {
            var result = expression.calculate();
            return String.valueOf(result);
        }
        return line;
    }
    public static void Calculate(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        FileWriter outputFile = new FileWriter(outputFileName);
        Scanner inputScanner = new Scanner(inputFile);
        while (inputScanner.hasNextLine()) {
            var line = inputScanner.nextLine();
            var calculatedLine = CalculateLine(line);
            outputFile.write(calculatedLine);
            outputFile.write('\n');
        }
        outputFile.close();
    }
}
