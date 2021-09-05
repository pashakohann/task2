package com.epam.text_parse.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
    private static final Logger loger = LogManager.getLogger();

    public void writeText(String text) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/text2.txt"))) {

            bufferedWriter.write(text);


        } catch (IOException e) {
            loger.error("Problem with writing to file", e);
        }

    }
}