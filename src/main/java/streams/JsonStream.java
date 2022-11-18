package streams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.IExpression;
import parsers.json.ExpressionList;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JsonStream extends DefaultStreamResult {
    protected ExpressionList expressions;
    public JsonStream(byte[] bytes) {
        super(bytes);
    }
    public JsonStream(ExpressionList expressions) {
        this.expressions = expressions;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String str = gson.toJson(expressions);
        bytes = str.getBytes();
        var lines = new ArrayList<String>();
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }
}
