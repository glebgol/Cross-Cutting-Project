package parsers.xml;

import interfaces.IXmlExpressionList;

import java.io.IOException;
import java.util.ArrayList;

public class XmlExpressionList implements IXmlExpressionList {
    protected ArrayList<XmlExpression> expressions;
    public XmlExpressionList() {}
    public XmlExpressionList(ArrayList<XmlExpression> expressions) {
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
