package readers;

import com.google.gson.Gson;
import exceptions.CryptoException;
import interfaces.IExpressionList;
import interfaces.IStream;
import parsers.json.ExpressionList;
import streams.JsonStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsonFileReader extends DefaultFileReader {

    public JsonFileReader(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    public void Write(IStream stream) throws IOException, CryptoException {
        FileOutputStream outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
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

    public IStream ReadFromJson() throws IOException, CryptoException {
        var expressions = new ExpressionList();
        expressions.ReadFromJsonFile(inputFilename);
        return new JsonStream(expressions);
    }

    @Override
    public IStream Transform(IStream stream) throws IOException, CryptoException {
        var str = new String(stream.bytes());
        var lst = new Gson().fromJson(str, ExpressionList.class);
        return new JsonStream(lst);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return null;
    }

    @Override
    public void WriteCalculated() throws IOException, CryptoException {

    }
}
