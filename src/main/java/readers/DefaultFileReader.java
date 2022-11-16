package readers;

import interfaces.IFileReader;

public abstract class DefaultFileReader implements IFileReader {
    protected String inputFilename;
    protected String outputFilename;

    public DefaultFileReader(String inputFilename, String outputFilename) {
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
}
