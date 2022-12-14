package streams;

import interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class DefaultStreamResult implements IStream {
    protected ArrayList<String> lines;
    protected byte[] bytes;
    public DefaultStreamResult(byte[] bytes) {
        this.bytes = bytes;
        var lines = new ArrayList<String>();
        var str = new String(bytes, StandardCharsets.UTF_8);
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }

    protected DefaultStreamResult() {
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
