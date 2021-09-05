package com.epam.text_parse.parser;


import com.epam.text_parse.entity.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SentenseIntoWordsParser extends Parser<Word> {
    private static final Pattern PATTERN_FOR_BREAKING_INTO_WORD = Pattern.compile("(?<=[-\u00200-9,:(){}/\"%\\[=+\\]><&^$#@])");


    public SentenseIntoWordsParser() {
    }

    public SentenseIntoWordsParser(Parser nextParser) {
        super(nextParser);
    }

    public static Pattern getPATTERN_FOR_BREAKING_INTO_WORD() {
        return PATTERN_FOR_BREAKING_INTO_WORD;
    }

    @Override
    public List<Word> parse(String parahraph) {
        List<Word> words = new ArrayList<>();
        List<String> sentenses = Arrays.asList(parahraph.toString().split(getPATTERN_FOR_BREAKING_INTO_WORD().toString()));
        sentenses.forEach(e -> words.add(new Word(e)));

        return words;
    }
}
