package readers;

import archivers.ArchivationFileManager;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ZipFileReader extends FileReader {

    public ZipFileReader(IFileReader reader) {
        super(reader);
    }

    @Override
    public void write(IStream stream, String outputFilename) throws IOException, CryptoException {
        _reader.write(stream, outputFilename);
    }

    @Override
    public IStream read() throws IOException, CryptoException, JAXBException {
        var unzippingResult = ArchivationFileManager.GetUnZipped(inputFilename);
        return _reader.transform(unzippingResult);
    }

    @Override
    public IStream transform(IStream stream) throws IOException, CryptoException, JAXBException {
        return _reader.transform(stream);
    }

    @Override
    public IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException {
        return _reader.calculate(stream);
    }

    @Override
    public void getResult(String outputFileName) throws IOException, CryptoException, JAXBException {
        var readingResult = read();
        var calculatedResult = calculate(readingResult);
        write(calculatedResult, outputFileName);
    }
}