package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class UnzippingResult implements IStream {
    private ArrayList<String> lines;
    private byte[] bytes;

    public UnzippingResult(ArrayList<String> lines, byte[] bytes) {
        this.lines = lines;
        this.bytes = bytes;
    }
    @Override
    public ArrayList<String> lines() {
        return lines;
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }
}
