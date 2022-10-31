package streams;

import interfaces.IStream;

import java.util.ArrayList;

public class ReadingResult implements IStream {
    private ArrayList<String> lines;

    public ReadingResult(ArrayList<String> lines) {
        this.lines = lines;
    }
    @Override
    public ArrayList<String> lines() {
        return lines;
    }
}
