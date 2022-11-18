package readers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CryptoException;
import interfaces.IExpression;
import interfaces.IExpressionList;
import interfaces.IFileReader;
import interfaces.IStream;
import parsers.json.ExpressionList;
import parsers.json.ExpressionObject;
import streams.JsonStream;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonFileReader extends DefaultFileReader {

    public JsonFileReader(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    public void Write(IStream stream) throws IOException, CryptoException {

    }
    public void WriteJsonExpressions(IExpressionList expressionList) throws IOException {
        expressionList.WriteToJsonFile(outputFilename);
    }

    @Override
    public IStream Read() throws IOException, CryptoException {
        var expressions = new ExpressionList();
        expressions.ReadFromJsonFile(inputFilename);
        return new JsonStream(expressions);
    }

    @Override
    public IStream Transform(IStream stream) throws IOException, CryptoException {
        return null;
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return null;
    }

    @Override
    public void WriteCalculated() throws IOException, CryptoException {

    }
}
