package com.epam.text_parse.parser;


import com.epam.text_parse.entity.Sentence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ParagraphIntoSentensesParser extends Parser<Sentence> {
    public static final Pattern PATTERN_FOR_BREAKING_INTO_SENTENSES = Pattern.compile("(?<=[.!?])\\s");


    public ParagraphIntoSentensesParser() {
    }

    public ParagraphIntoSentensesParser(Parser nextParser) {
        super(nextParser);
    }

    public static Pattern getPATTERN_FOR_BREAKING_INTO_SENTENSES() {
        return PATTERN_FOR_BREAKING_INTO_SENTENSES;
    }


    @Override
    public List<Sentence> parse(String text) {
        List<Sentence> sentences = new ArrayList<>();
        List<String> inputParagraphs = Arrays.asList(text.toString().split(getPATTERN_FOR_BREAKING_INTO_SENTENSES().toString()));
        inputParagraphs.forEach(e -> sentences.add(new Sentence((nextParser.parse(e)))));

        return sentences;
    }
}
