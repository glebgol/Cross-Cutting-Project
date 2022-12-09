package readers;

import com.google.gson.Gson;
import exceptions.CryptoException;
import interfaces.IJsonExpressionList;
import interfaces.IStream;
import parsers.json.JsonExpressionList;
import streams.JsonStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsonFileReader extends DefaultFileReader {

    public JsonFileReader(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    public void Write(IStream stream) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
    }
    public void WriteJsonExpressions(IJsonExpressionList expressionList) throws IOException {
        expressionList.WriteToJsonFile(outputFilename);
    }

    @Override
    public IStream Read() throws IOException {
        var expressions = new JsonExpressionList();
        expressions.ReadFromJsonFile(inputFilename);
        return new JsonStream(expressions);
    }

    public IStream ReadFromJson() throws IOException {
        var expressions = new JsonExpressionList();
        expressions.ReadFromJsonFile(inputFilename);
        return new JsonStream(expressions);
    }

    @Override
    public IStream Transform(IStream stream) {
        var str = new String(stream.bytes());
        var lst = new Gson().fromJson(str, JsonExpressionList.class);
        return new JsonStream(lst);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        var jsonStream = (JsonStream) stream;
        return jsonStream.Calculate();
    }

    @Override
    public void GetResult(String outputFileName) throws IOException, CryptoException {
        var readingResult = Read();
        var calculatedResult = Calculate(readingResult);
        Write(calculatedResult);
    }
}
