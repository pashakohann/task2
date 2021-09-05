package com.epam.text_parse.entity;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word implements TextElementParser, PrintTextElement {
    private String word;
    private Pattern pattern = Pattern.compile("[A-Za-z]+");


    public Word(String word) {
        this.word = word;


    }

    public String getWord() {

        return word;
    }

    public String getWordWithoutPunctiation() {
        Matcher matcher = pattern.matcher(getWord());
        String result = "";
        while (matcher.find()) {
            result = matcher.group();
        }

        return result;

    }

    public void setWord(String word) {
        this.word = this.word.replace(getWordWithoutPunctiation(), word);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word) && Objects.equals(pattern, word1.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, pattern);
    }



    @Override
    public String printElement() {
        return word;
    }

}
