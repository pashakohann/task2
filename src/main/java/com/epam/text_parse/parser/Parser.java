package com.epam.text_parse.parser;

import com.epam.text_parse.entity.TextElementParser;

import java.util.List;

public abstract class Parser<T extends TextElementParser> {
    protected Parser nextParser;

    public Parser() {
    }

    public Parser(Parser nextParser) {
        this.nextParser = nextParser;
    }


    public abstract List<T> parse(String inputText);
}
