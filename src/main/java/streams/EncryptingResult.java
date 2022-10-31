package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class EncryptingResult implements IStream {
    private ArrayList<String> lines;

    public EncryptingResult(ArrayList<String> lines) {
        this.lines = lines;
    }
    @Override
    public ArrayList<String> lines() {
        return null;
    }
}
