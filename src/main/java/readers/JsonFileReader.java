package readers;

import com.google.gson.Gson;
import exceptions.CryptoException;
import interfaces.IStream;
import parsers.json.JsonExpressionList;
import streams.JsonStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsonFileReader extends DefaultFileReader {

    public JsonFileReader(String inputFilename) {
        super(inputFilename);
    }

    @Override
    public void write(IStream stream, String outputFilename) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
    }

    @Override
    public IStream read() throws IOException {
        var expressions = new JsonExpressionList();
        expressions.readFromJsonFile(inputFilename);
        return new JsonStream(expressions);
    }

    @Override
    public IStream transform(IStream stream) {
        var str = new String(stream.bytes());
        var lst = new Gson().fromJson(str, JsonExpressionList.class);
        return new JsonStream(lst);
    }

    @Override
    public IStream calculate(IStream stream) throws IOException, CryptoException {
        var jsonStream = (JsonStream) stream;
        return jsonStream.Calculate();
    }

    @Override
    public void getResult(String outputFileName) throws IOException, CryptoException {
        var readingResult = read();
        var calculatedResult = calculate(readingResult);
        write(calculatedResult, outputFileName);
    }
}
