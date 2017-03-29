package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Provides methods to work with console input.
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.03.17
 */
public class ConsoleInput implements Input {

    /**
     * Prints a question.
     * @param question text as a String
     * @return user input
     */
    @Override
    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}