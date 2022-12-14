package readers;

import exceptions.CryptoException;
import interfaces.IStream;
import parsers.xml.XmlExpressionList;
import streams.XmlStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

public class XmlFileReader extends DefaultFileReader {

    public XmlFileReader(String inputFilename) {
        super(inputFilename);
    }

    @Override
    public void write(IStream stream, String outputFilename) throws IOException {
        var outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
    }

    @Override
    public IStream read() throws IOException, JAXBException {
        var expressions = new XmlExpressionList();
        expressions.readFromXmlFile(inputFilename);
        return new XmlStream(expressions);
    }

    @Override
    public IStream transform(IStream stream) throws JAXBException {
        var xmlString = new String(stream.bytes());
        var jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);

        var jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        var expressions = (XmlExpressionList) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        return new XmlStream(expressions);
    }

    @Override
    public IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException {
        var xmlStream = (XmlStream) stream;
        return xmlStream.calculate();
    }
}
