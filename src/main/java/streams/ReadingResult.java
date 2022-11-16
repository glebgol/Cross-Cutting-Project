package streams;

import interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadingResult extends DefaultStreamResult {
    public ReadingResult(byte[] bytes) {
        super(bytes);
    }
    public ReadingResult(ArrayList<String> lines) {
        this.lines = lines;
        StringBuilder stringBuilder = new StringBuilder();
        for (var line : lines) {
            stringBuilder.append(line).append('\n');
        }
        this.bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
