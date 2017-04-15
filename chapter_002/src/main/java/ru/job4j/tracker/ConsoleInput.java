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
     * Scanner instance.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints a question.
     * @param question text as a String
     * @return user input
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    /**
     * Process the question with check.
     * @param question text for user
     * @param range menu items numbers
     * @return chosen item id
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exits = false;
        for (int val : range) {
             if (key == val) {
                exits = true;
                break;
             }
        }
        if (exits) {
            return key;
        } else {
            throw new MenuOutException("Invalid menu item.");
        }
    }
}