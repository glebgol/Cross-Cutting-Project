package streams;

import interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UnzippingResult implements IStream {
    private final ArrayList<String> lines;
    private final byte[] bytes;

    public UnzippingResult(byte[] bytes) {
        this.bytes = bytes;
        var lines = new ArrayList<String>();
        var str = new String(bytes, StandardCharsets.UTF_8);
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
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
