package com.glebgol.businesslogic.contracts.interfaces;

import java.io.IOException;

public interface IJsonExpressionList {
    void writeToJsonFile(String jsonFileName) throws IOException;
    void readFromJsonFile(String jsonFileName) throws IOException;

    IJsonExpressionList calculate();
}
