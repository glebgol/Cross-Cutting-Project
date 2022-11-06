package readers;

import interfaces.IFileReader;
import interfaces.IStream;
import parsers.ProcessingTxtFile;
import streams.CalculationResult;
import streams.ReadingResult;

import java.io.*;
import java.util.ArrayList;

public class TxtFileReader implements IFileReader {

    protected String inputFilename;
    protected String outputFilename;

    public TxtFileReader(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }
    @Override
    public String getInputFilename() {
        return inputFilename;
    }

    @Override
    public String getOutputFilename() {
        return outputFilename;
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
        return new ReadingResult(bytes);
    }

    @Override
    public IStream Transform(IStream stream) {
        return stream;
    }

    @Override
    public IStream Calculate(IStream stream) {
        var calculatedLines = new ArrayList<String>();
        for (var line : stream.lines()) {
            var calculateLine = ProcessingTxtFile.CalculateLine(line);
            calculatedLines.add(calculateLine);
        }
        var calculationResult = new CalculationResult(calculatedLines);
        return calculationResult;
    }

    @Override
    public void WriteCalculated() throws IOException {
        var readingResult = Read();
        var calculatedResult = Calculate(readingResult);
        Write(calculatedResult);
    }
}
