package com.glebgol.businesslogic.streams;


import com.glebgol.businesslogic.contracts.interfaces.IStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class DefaultStreamResult implements IStream {
    protected List<String> lines;
    protected byte[] bytes;
    public DefaultStreamResult(byte[] bytes) {
        this.bytes = bytes;
        List<String> lines = new ArrayList<>();
        String str = new String(bytes, StandardCharsets.UTF_8);
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }

    protected DefaultStreamResult() {
    }

    @Override
    public List<String> lines() {
        return lines;
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }
}
