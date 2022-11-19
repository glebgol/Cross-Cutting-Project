package interfaces;

import java.io.IOException;

public interface IXmlExpressionList {
    void WriteToXmlFile(String xmlFileName) throws IOException;
    void ReadFromXmlFile(String xmlFileName) throws IOException;

    IXmlExpressionList Calculate();
}
