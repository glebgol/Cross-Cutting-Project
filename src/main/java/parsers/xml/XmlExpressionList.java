package parsers.xml;

import interfaces.IXmlExpressionList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class XmlExpressionList implements IXmlExpressionList {

    public List<XmlExpression> expressions;
    public XmlExpressionList() {}
    public XmlExpressionList(List<XmlExpression> expressions) {
        this.expressions = expressions;
    }
    @Override
    public void writeToXmlFile(String xmlFileName) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);
        var jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        var sw = new StringWriter();
        jaxbMarshaller.marshal(this, sw);
        var xmlContent = sw.toString();
        var outputFile = new FileOutputStream(xmlFileName);
        outputFile.write(xmlContent.getBytes());
        outputFile.close();
    }

    @Override
    public void readFromXmlFile(String xmlFileName) throws JAXBException {
        var jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);
        var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        var readingResult = (XmlExpressionList) jaxbUnmarshaller.unmarshal(new File(xmlFileName));
        this.expressions = readingResult.expressions;
    }

    public XmlExpressionList calculate() {
        var calculatedExpressions = new ArrayList<XmlExpression>(expressions.size());
        for (var exp : expressions) {
            calculatedExpressions.add(exp.Calculate());
        }
        return new XmlExpressionList(calculatedExpressions);
    }
}
