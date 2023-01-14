package com.glebgol.businesslogic.readers;


import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.businesslogic.streams.TxtStream;
import com.glebgol.businesslogic.calculation.CalculationEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        byte[] bytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return new TxtStream(bytes);
    }

    @Override
    public IStream transform(IStream stream) {
        return stream;
    }

    @Override
    public IStream calculate(IStream stream) {
        List<String> calculatedLines = new ArrayList<>();
        for (var line : stream.lines()) {
            String calculateLine = CalculationEngine.calculateLine(line);
            calculatedLines.add(calculateLine);
        }
        return new TxtStream(calculatedLines);
    }
}
