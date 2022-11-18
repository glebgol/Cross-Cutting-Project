package interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parsers.json.ExpressionList;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface IExpressionList {
    void WriteToJsonFile(String jsonFileName) throws IOException;
    void ReadFromJsonFile(String jsonFileName) throws IOException;

    IExpressionList Calculate();
}
