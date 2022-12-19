package streams;

import interfaces.IXmlExpressionList;
import parsers.xml.XmlExpressionList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class XmlStream extends DefaultStreamResult {
    protected IXmlExpressionList expressions;
    public XmlStream(IXmlExpressionList expressions) throws JAXBException {
        this.expressions = expressions;

        var jaxbContext = JAXBContext.newInstance(XmlExpressionList.class);
        var jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        var sw = new StringWriter();
        jaxbMarshaller.marshal(expressions, sw);
        bytes = sw.toString().getBytes();

        var lines = new ArrayList<String>();
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
