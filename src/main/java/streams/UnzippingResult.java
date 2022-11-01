package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class UnzippingResult implements IStream {
    private ArrayList<String> lines;

    public UnzippingResult(ArrayList<String> lines) {
        this.lines = lines;
    }
    @Override
    public ArrayList<String> lines() {
        return lines;
    }
}
