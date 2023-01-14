package com.glebgol.businesslogic.streams;


import com.glebgol.businesslogic.calculation.xml.XmlExpressionList;
import com.glebgol.businesslogic.contracts.interfaces.IXmlExpressionList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class XmlStream extends DefaultStreamResult {
    protected IXmlExpressionList expressions;
    public XmlStream(IXmlExpressionList expressions) throws JAXBException {
        this.expressions = expressions;

        JAXBContext jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(expressions, sw);
        bytes = sw.toString().getBytes();

        List<String> lines = new ArrayList<>();
        var stringTokenizer = new StringTokenizer(sw.toString(), "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }

    public XmlStream calculate() throws JAXBException {
        return new XmlStream(expressions.calculate());
    }
}
