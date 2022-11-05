package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class EncryptingResult implements IStream {
    private ArrayList<String> lines;
    private byte[] bytes;

    public EncryptingResult(ArrayList<String> lines, byte[] bytes) {
        this.lines = lines;
        this.bytes = bytes;
    }
    @Override
    public ArrayList<String> lines() {
        return null;
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }
}
