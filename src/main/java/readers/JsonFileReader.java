package readers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get(outputFilename));

        var expressions = new ArrayList<>(Arrays.asList(
                new ExpressionObject("1+2", "1+3", "1+4"),
                new ExpressionObject("1*2", "13*12", "10*4")
                ));
        
        gson.toJson(expressions, writer);
        writer.close();
    }

    @Override
    public IStream Read() throws IOException, CryptoException {
        return null;
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
