package readers;

import interfaces.IFileReader;
import interfaces.IStream;
import parsers.ProcessingTxtFile;
import streams.CalculationResult;
import streams.ReadingResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DefaultReader implements IFileReader {

    private String inputFilename;
    private String outputFilename;

    public DefaultReader(String inputFilename, String outputFilename) {
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
        FileWriter outputFile = new FileWriter(outputFilename);
        for (var line : stream.lines()) {
            outputFile.write(line);
            outputFile.write('\n');
        }
        outputFile.close();
    }

    @Override
    public IStream Read() throws FileNotFoundException {
        File inputFile = new File(inputFilename);
        Scanner inputScanner = new Scanner(inputFile);
        var lines = new ArrayList<String>();
        while (inputScanner.hasNextLine()) {
            var line = inputScanner.nextLine();
            lines.add(line);
        }
        var readingResult = new ReadingResult(lines);
        return readingResult;
    }

    @Override
    public IStream Transform(IStream stream) {
        return stream;
    }

    @Override
    public IStream Calculate(IStream stream) throws FileNotFoundException {
        var calculatedLines = new ArrayList<String>();
        for (var line : stream.lines()) {
            var calculateLine = ProcessingTxtFile.CalculateLine(line);
            calculatedLines.add(calculateLine);
        }
        var calculationResult = new CalculationResult(calculatedLines);
        return calculationResult;
    }
}
