package streams;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TxtStream extends DefaultStreamResult {
    public TxtStream(byte[] bytes) {
        super(bytes);
    }
    public TxtStream(ArrayList<String> lines) {
        this.lines = lines;
        StringBuilder stringBuilder = new StringBuilder();
        for (var line : lines) {
            stringBuilder.append(line).append('\n');
        }
        this.bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
