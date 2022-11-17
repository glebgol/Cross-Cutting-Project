package streams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.IExpression;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JsonStream extends DefaultStreamResult {
    protected ArrayList<IExpression> objects;
    public JsonStream(byte[] bytes) {
        super(bytes);
    }
    public JsonStream(ArrayList<IExpression> objects) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String str = gson.toJson(objects);
        bytes = str.getBytes();
        var lines = new ArrayList<String>();
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }
}
