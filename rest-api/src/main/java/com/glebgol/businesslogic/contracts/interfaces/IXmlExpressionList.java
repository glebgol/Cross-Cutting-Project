package com.glebgol.businesslogic.contracts.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;

public interface IXmlExpressionList extends Serializable {
    void writeToXmlFile(String xmlFileName) throws IOException, JAXBException;
    void readFromXmlFile(String xmlFileName) throws IOException, JAXBException;

    IXmlExpressionList calculate();
}
