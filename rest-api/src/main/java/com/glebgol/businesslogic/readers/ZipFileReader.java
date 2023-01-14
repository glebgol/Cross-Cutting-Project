package com.glebgol.businesslogic.readers;



import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.businesslogic.utils.archivers.ArchivingFileManager;

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
        var unzippingResult = ArchivingFileManager.getUnZipped(inputFilename);
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
}