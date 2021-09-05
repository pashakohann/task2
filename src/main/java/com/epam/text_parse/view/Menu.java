package com.epam.text_parse.view;

import com.epam.text_parse.entity.Text;
import com.epam.text_parse.logic.Logic;
import com.epam.text_parse.reader.ReadFile;
import com.epam.text_parse.writer.WriteText;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Logic logic = new Logic();


    public void showMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите, что вы хотите сделать с текстом:");
        System.out.println("1. Поменять местами первое и последнее слово в каждом предложении");
        System.out.println("2.Найти такое слово в первом предложении, которого нету ни в одном предложении в тексте.");
        System.out.println("3. В вопросительных предложениях вывести слова заданной длины");
        int result;

        while (!scanner.hasNextInt()) {
            System.out.println("Введите корректное число от 1 до 3.");
            scanner.nextLine();
        }
        result = scanner.nextInt();

        switch (result) {
            case 1:
                logic.swapFirstWithLastWord();


                break;
            case 2:
                logic.findUnicWordInFirstSentense();
                break;
            case 3:

                int resultt;
                System.out.println("Введите число заданной длины слова от 1 до 15");
                while (!scanner.hasNextInt()) {
                    System.out.println("Введите корректное число от 1 до 15.");
                    scanner.nextLine();
                }
                resultt = scanner.nextInt();
                logic.printWordsInInterrogativeSentencesByWordLength(resultt);
                break;
            default:
                System.out.println("Введите число от 1 до 3.");
        }
    }
}
