package parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.IJsonExpressionList;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonExpressionList implements IJsonExpressionList {
    protected List<JsonExpression> expressions;
    public JsonExpressionList() {}
    public JsonExpressionList(List<JsonExpression> expressions) {
        this.expressions = expressions;
    }

    public void WriteToJsonFile(String jsonFileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get(jsonFileName));
        gson.toJson(this, writer);
        writer.close();
    }

    public void ReadFromJsonFile(String jsonFileName) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(jsonFileName));
        var lst = gson.fromJson(reader, JsonExpressionList.class);
        expressions = lst.expressions;
        reader.close();
    }

    @Override
    public IJsonExpressionList Calculate() {
        var calculatedExpressions = new ArrayList<JsonExpression>(expressions.size());
        for (var exp : expressions) {
            calculatedExpressions.add(exp.Calculate());
        }
        return new JsonExpressionList(calculatedExpressions);
    }
}
