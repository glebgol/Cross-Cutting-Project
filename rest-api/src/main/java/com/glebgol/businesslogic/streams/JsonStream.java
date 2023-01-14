package com.glebgol.businesslogic.streams;

import com.glebgol.businesslogic.contracts.interfaces.IJsonExpressionList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JsonStream extends DefaultStreamResult {
    protected IJsonExpressionList expressions;
    public JsonStream(IJsonExpressionList expressions) {
        this.expressions = expressions;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String str = gson.toJson(expressions);
        bytes = str.getBytes();
        List<String> lines = new ArrayList<>();
        var stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            lines.add(stringTokenizer.nextToken());
        }
        this.lines = lines;
    }

    public JsonStream calculate() {
        return new JsonStream(expressions.calculate());
    }
}
