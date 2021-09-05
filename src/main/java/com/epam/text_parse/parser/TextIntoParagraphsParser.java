package com.epam.text_parse.parser;

import com.epam.text_parse.entity.Paragraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class TextIntoParagraphsParser extends Parser<Paragraph> {
    private static Pattern PATTERN_FOR_BREAKING_INTO_PARAGRAPH = Pattern.compile("((\\r\\n)|[\\r\\n])");


    public TextIntoParagraphsParser() {
    }

    public TextIntoParagraphsParser(Parser nextParser) {
        super(nextParser);
    }

    public static Pattern getPATTERN_FOR_BREAKING_INTO_PARAGRAPH() {
        return PATTERN_FOR_BREAKING_INTO_PARAGRAPH;
    }


    @Override
    public List<Paragraph> parse(String sentense) {
        List<Paragraph> paragraphs = new ArrayList<>();
        List<String> paragraphsForbrake = Arrays.asList(sentense.toString().split(getPATTERN_FOR_BREAKING_INTO_PARAGRAPH().toString()));
        paragraphsForbrake.forEach(e -> paragraphs.add(new Paragraph((nextParser.parse(e)))));

        return paragraphs;
    }
}
