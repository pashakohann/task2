package com.epam.text_parse.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph implements TextElementParser,PrintTextElement {
    private List<Sentence> listSentenses = new ArrayList<>();


    public Paragraph(List<Sentence> sentenses) {

        this.listSentenses = sentenses;
    }

    public List<Sentence> getListSentenses() {
        return listSentenses;
    }

    public void setListSentenses(List<Sentence> listSentenses) {
        this.listSentenses = listSentenses;
    }

    @Override
    public String printElement() {
        StringBuilder sentensesToParahraph = new StringBuilder();
        getListSentenses().forEach(e->sentensesToParahraph.append(e.printElement()));
        return sentensesToParahraph.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(listSentenses, paragraph.listSentenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSentenses);
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "listSentenses=" + listSentenses +
                '}';
    }
}
