package interfaces;

public interface IFileReaderBuilder {
    void setEncrypting(String key);
    void setZipping();
    void setFileExtension(String extension);
}
