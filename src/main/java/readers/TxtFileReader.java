package readers;

import interfaces.IStream;
import parsers.CalculationEngine;
import streams.TxtStream;

import java.io.*;
import java.util.ArrayList;

public class TxtFileReader extends DefaultFileReader {
    public TxtFileReader(String inputFilename) {
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
        File inputFile = new File(inputFilename);
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        var bytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return new TxtStream(bytes);
    }

    @Override
    public IStream transform(IStream stream) {
        return stream;
    }

    @Override
    public IStream calculate(IStream stream) {
        var calculatedLines = new ArrayList<String>();
        for (var line : stream.lines()) {
            var calculateLine = CalculationEngine.CalculateLine(line);
            calculatedLines.add(calculateLine);
        }
        return new TxtStream(calculatedLines);
    }
}
