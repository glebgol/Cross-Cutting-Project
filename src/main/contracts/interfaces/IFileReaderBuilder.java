package interfaces;

import java.util.List;

public interface IFileReaderBuilder {
    void setEncrypting(String key);
    void setEncrypting(List<String> keys);
    void setZipping(boolean isZip);
    IFileReader getResult();
}
