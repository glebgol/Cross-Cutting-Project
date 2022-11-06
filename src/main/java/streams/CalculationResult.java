package streams;

import interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CalculationResult implements IStream {
    private final ArrayList<String> lines;
    private final byte[] bytes;
    public CalculationResult(ArrayList<String> lines) {
        this.lines = lines;
        StringBuilder stringBuilder = new StringBuilder();
        for (var line : lines) {
            stringBuilder.append(line).append('\n');
        }
        this.bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
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
