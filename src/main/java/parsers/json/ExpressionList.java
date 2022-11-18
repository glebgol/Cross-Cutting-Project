package parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.IExpressionList;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExpressionList implements IExpressionList {
    protected ArrayList<ExpressionObject> expressions;
    public ExpressionList() {}
    public ExpressionList(ArrayList<ExpressionObject> expressions) {
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
        var lst = gson.fromJson(reader, ExpressionList.class);
        expressions = lst.expressions;
        reader.close();
    }

    @Override
    public IExpressionList Calculate() {
        var calculatedExpressions = new ArrayList<ExpressionObject>(expressions.size());
        for (var exp : expressions) {
            calculatedExpressions.add(exp.Calculate());
        }
        return new ExpressionList(calculatedExpressions);
    }
}