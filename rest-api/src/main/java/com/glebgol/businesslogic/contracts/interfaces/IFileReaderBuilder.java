package com.glebgol.businesslogic.contracts.interfaces;

import java.util.List;

public interface IFileReaderBuilder {
    void setEncrypting(String key);
    void setEncrypting(List<String> keys);
    void setZipping(boolean isZip);
    IFileReader getFileReader();
}
