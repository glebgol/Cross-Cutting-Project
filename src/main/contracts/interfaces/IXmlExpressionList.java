package interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;

public interface IXmlExpressionList extends Serializable {
    void WriteToXmlFile(String xmlFileName) throws IOException, JAXBException;
    void ReadFromXmlFile(String xmlFileName) throws IOException, JAXBException;

    IXmlExpressionList Calculate();
}
