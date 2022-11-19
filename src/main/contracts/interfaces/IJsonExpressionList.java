package interfaces;

import java.io.IOException;

public interface IJsonExpressionList {
    void WriteToJsonFile(String jsonFileName) throws IOException;
    void ReadFromJsonFile(String jsonFileName) throws IOException;

    IJsonExpressionList Calculate();
}
