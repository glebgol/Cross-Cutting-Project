package parsers.xml;

import interfaces.IXmlExpressionList;

import java.io.IOException;
import java.util.List;

public class XmlExpressionList implements IXmlExpressionList {
    protected List<XmlExpression> expressions;
    public XmlExpressionList() {}
    public XmlExpressionList(List<XmlExpression> expressions) {
        this.expressions = expressions;
    }
    @Override
    public void WriteToXmlFile(String xmlFileName) throws IOException {

    }

    @Override
    public void ReadFromXmlFile(String xmlFileName) throws IOException {

    }

    public XmlExpressionList Calculate() {
        return null;
    }
}
