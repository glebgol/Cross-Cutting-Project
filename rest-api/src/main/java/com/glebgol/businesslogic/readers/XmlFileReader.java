package com.glebgol.businesslogic.readers;


import com.glebgol.businesslogic.calculation.xml.XmlExpressionList;
import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.businesslogic.contracts.interfaces.IXmlExpressionList;
import com.glebgol.businesslogic.streams.XmlStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

public class XmlFileReader extends DefaultFileReader {

    public XmlFileReader(String inputFilename) {
        super(inputFilename);
    }

    @Override
    public void write(IStream stream, String outputFilename) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(outputFilename);
        outputFile.write(stream.bytes());
        outputFile.close();
    }

    @Override
    public IStream read() throws IOException, JAXBException {
        IXmlExpressionList expressions = new XmlExpressionList();
        expressions.readFromXmlFile(inputFilename);
        return new XmlStream(expressions);
    }

    @Override
    public IStream transform(IStream stream) throws JAXBException {
        String xmlString = new String(stream.bytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        IXmlExpressionList expressions = (IXmlExpressionList) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        return new XmlStream(expressions);
    }

    @Override
    public IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException {
        XmlStream xmlStream = (XmlStream) stream;
        return xmlStream.calculate();
    }
}
