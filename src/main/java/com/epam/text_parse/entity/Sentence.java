package com.epam.text_parse.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements TextElementParser, PrintTextElement {
    private List<Word> listWords = new ArrayList<>();


    public Sentence(List<Word> listWords) {

        this.listWords = listWords;
    }

    public List<Word> getListWords() {
        return listWords;
    }

    public void setListWords(List<Word> listWords) {
        this.listWords = listWords;
    }

    @Override
    public String printElement() {
        StringBuilder wordsToSentense = new StringBuilder();
        getListWords().forEach(e->wordsToSentense.append(e.printElement()));
        return wordsToSentense.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(listWords, sentence.listWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listWords);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "listWords=" + listWords +
                '}';
    }
}
