package streams;

import interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadingResult implements IStream {
    private final ArrayList<String> lines;
    private final byte[] bytes;
    public ReadingResult(byte[] bytes) {
        this.bytes = bytes;
        var lines = new ArrayList<String>();
        var str = new String(bytes, StandardCharsets.UTF_8);
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }

    public ReadingResult(ArrayList<String> lines) {
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
