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
    public void Write(IStream stream, String outputFilename) throws IOException, CryptoException {
        _reader.Write(stream, outputFilename);
    }

    @Override
    public IStream Read() throws IOException, CryptoException, JAXBException {
        var unzippingResult = ArchivationFileManager.GetUnZipped(inputFilename);
        return _reader.Transform(unzippingResult);
    }

    @Override
    public IStream Transform(IStream stream) throws IOException, CryptoException, JAXBException {
        return _reader.Transform(stream);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException, JAXBException {
        return _reader.Calculate(stream);
    }

    @Override
    public void GetResult(String outputFileName) throws IOException, CryptoException, JAXBException {
        var readingResult = Read();
        var calculatedResult = Calculate(readingResult);
        Write(calculatedResult, outputFileName);
    }
}