package com.epam.text_parse.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements TextElementParser, PrintTextElement {
    private List<Paragraph> listParagraphs = new ArrayList<>();

    public Text(List<Paragraph> listParagraphs) {
        this.listParagraphs = listParagraphs;
    }

    public List<Paragraph> getListParagraphs() {
        return listParagraphs;
    }

    public void setListParagraphs(List<Paragraph> listParagraphs) {
        this.listParagraphs = listParagraphs;
    }

    @Override
    public String printElement() {
        StringBuilder paragraphsToText = new StringBuilder();
        getListParagraphs().forEach(e ->
        {
            paragraphsToText.append(e.printElement());
            paragraphsToText.append("\n");
        });
        return paragraphsToText.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(listParagraphs, text.listParagraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listParagraphs);
    }

    @Override
    public String toString() {
        return "Text{" +
                "listParagraphs=" + listParagraphs +
                '}';
    }
}




