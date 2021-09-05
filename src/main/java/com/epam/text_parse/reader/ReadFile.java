package com.epam.text_parse.reader;

import com.epam.text_parse.entity.Sentence;
import com.epam.text_parse.exception.FileEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
    private static final Logger loger = LogManager.getLogger();

    public StringBuilder readTextFromFile() {
        StringBuilder wholeText = new StringBuilder();


        try (BufferedReader readFile = new BufferedReader(new FileReader("src/main/resources/text.txt"))) {

            while (readFile.ready()) {
                wholeText.append((char) readFile.read());

            }

        } catch (IOException e) {
            loger.error("file reading problem", e);
        }

        return wholeText;
    }

}
