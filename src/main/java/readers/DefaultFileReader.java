package readers;

import interfaces.IFileReader;

public abstract class DefaultFileReader implements IFileReader {
    protected String inputFilename;
    public DefaultFileReader(String inputFilename) {
        this.inputFilename = inputFilename;
    }
    @Override
    public String getInputFilename() {
        return inputFilename;
    }
}
