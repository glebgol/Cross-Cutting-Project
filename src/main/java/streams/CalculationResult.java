package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class CalculationResult implements IStream {
    private ArrayList<String> lines;

    public CalculationResult(ArrayList<String> lines) {
        this.lines = lines;
    }
    @Override
    public ArrayList<String> lines() {
        return lines;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }
}
