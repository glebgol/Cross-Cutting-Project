package readers;

import interfaces.IStream;
import parsers.CalculationEngine;
import streams.TxtStream;

import java.io.*;
import java.util.ArrayList;

public class TxtFileReader extends DefaultFileReader {
    public TxtFileReader(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    public void Write(IStream stream) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
    }

    @Override
    public IStream Read() throws IOException {
        File inputFile = new File(inputFilename);
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        var bytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return new TxtStream(bytes);
    }

    @Override
    public IStream Transform(IStream stream) {
        return stream;
    }

    @Override
    public IStream Calculate(IStream stream) {
        var calculatedLines = new ArrayList<String>();
        for (var line : stream.lines()) {
            var calculateLine = CalculationEngine.CalculateLine(line);
            calculatedLines.add(calculateLine);
        }
        return new TxtStream(calculatedLines);
    }

    @Override
    public void WriteCalculated() throws IOException {
        var readingResult = Read();
        var calculatedResult = Calculate(readingResult);
        Write(calculatedResult);
    }
}
