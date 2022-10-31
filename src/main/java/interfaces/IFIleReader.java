package interfaces;

public interface IFIleReader {
    void Write(IStream stream);
    IStream Read();
    IStream Calculate();
}
