package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFIleReader {
    String getInputFilename();
    String getOutputFilename();
    void Write(IStream stream) throws IOException;
    IStream Read() throws FileNotFoundException;
    IStream Calculate() throws FileNotFoundException;
}
