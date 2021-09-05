package com.epam.text_parse.logic;

import com.epam.text_parse.entity.Sentence;
import com.epam.text_parse.entity.Text;
import com.epam.text_parse.parser.ParagraphIntoSentensesParser;
import com.epam.text_parse.parser.Parser;
import com.epam.text_parse.parser.SentenseIntoWordsParser;
import com.epam.text_parse.parser.TextIntoParagraphsParser;
import com.epam.text_parse.reader.ReadFile;
import com.epam.text_parse.writer.WriteText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    private static final Logger logger = LogManager.getLogger();
    private ReadFile rf = new ReadFile();
    private Text ourParagraphs = new Text(Logic.startParse().parse(rf.readTextFromFile().toString()));
    private WriteText wt = new WriteText();


    public static Parser startParse() {
        SentenseIntoWordsParser sentenseIntoWordsParser = new SentenseIntoWordsParser();
        ParagraphIntoSentensesParser paragraphIntoSentensesParser = new ParagraphIntoSentensesParser(sentenseIntoWordsParser);
        return new TextIntoParagraphsParser(paragraphIntoSentensesParser);
    }


    public void swapFirstWithLastWord() {

        for (int i = 0; i < ourParagraphs.getListParagraphs().size(); i++) {
            for (int y = 0; y < ourParagraphs.getListParagraphs().get(i).getListSentenses().size(); y++) {
                String firstWord = null;
                int firstWordIndex = -1;
                String lastWord = null;
                int lastWordIndex = -1;

                for (int z = 0; z < ourParagraphs.getListParagraphs().get(i).getListSentenses().get(y).getListWords().size(); z++) {
                    if (ourParagraphs.getListParagraphs().get(i).getListSentenses().get(y).getListWords().get(z).getWordWithoutPunctiation() != "" && firstWord == null) {
                        firstWord = ourParagraphs.getListParagraphs().get(i).getListSentenses().get(y).getListWords().get(z).getWordWithoutPunctiation();
                        firstWordIndex = z;
                    }
                    if (ourParagraphs.getListParagraphs().get(i).getListSentenses().get(y).getListWords().get(z).getWordWithoutPunctiation() != "" && firstWord != null) {
                        lastWord = ourParagraphs.getListParagraphs().get(i).getListSentenses().get(y).getListWords().get(z).getWordWithoutPunctiation();
                        lastWordIndex = z;
                    }

                }
                if (firstWord != null && lastWord != null) {
                    firstWord = firstWord.toLowerCase();
                    lastWord = lastWord.substring(0, 1).toUpperCase() + lastWord.substring(1);
                    ourParagraphs.getListParagraphs().get(i).getListSentenses().
                            get(y).getListWords().get(firstWordIndex).setWord(lastWord);
                    ourParagraphs.getListParagraphs().get(i).getListSentenses().
                            get(y).getListWords().get(lastWordIndex).setWord(firstWord);
                }

            }
        }

        wt.writeText(ourParagraphs.printElement());
    }

    public void findUnicWordInFirstSentense() {
        List<String> listWordsFromFristSentense = new ArrayList<>();
        List<String> allWords = new ArrayList<>();
        AtomicInteger repeatWord = new AtomicInteger();
        ourParagraphs.getListParagraphs().get(0).getListSentenses().get(0).getListWords().forEach(words ->
                listWordsFromFristSentense.add(words.getWordWithoutPunctiation()));
        ourParagraphs.getListParagraphs().forEach(paragraphs ->
        {
            paragraphs.getListSentenses().forEach(sentenses ->
            {
                sentenses.getListWords().forEach(words ->
                {
                    allWords.add(words.getWordWithoutPunctiation());
                });
            });
        });

        listWordsFromFristSentense.forEach(firstSentense ->
        {
            allWords.forEach(allSentenses ->
            {
                if (firstSentense.toLowerCase().equals(allSentenses.toLowerCase())) {
                    repeatWord.getAndIncrement();
                }
            });
            if (repeatWord.get() < 2) {
                System.out.println(firstSentense);
            }
            repeatWord.set(0);
        });


    }

    public void printWordsInInterrogativeSentencesByWordLength(int wordLength) {


        List<Sentence> listInterrogativeSentences = new ArrayList<>();
        Pattern patternForInterrogativeSentenses = Pattern.compile("[?]");
        Set<String> setWords = new HashSet<>();
        ourParagraphs.getListParagraphs().forEach(paragraps ->
        {
            paragraps.getListSentenses().forEach(sentenses ->
            {
                sentenses.getListWords().forEach(words ->
                {
                    Matcher matcher = patternForInterrogativeSentenses.matcher(words.getWord());
                    while (matcher.find()) {
                        listInterrogativeSentences.add(sentenses);
                    }
                });
            });
        });
        System.out.println("Found "+ listInterrogativeSentences.size() + " sentenses .");


        listInterrogativeSentences.forEach(sentenses ->
        {
            sentenses.getListWords().forEach(words ->
            {
                if (words.getWordWithoutPunctiation().length() == wordLength) {
                    setWords.add(words.getWordWithoutPunctiation());
                }
            });
        });
        setWords.forEach(words ->
        {
            System.out.println(words);
        });


    }
}
