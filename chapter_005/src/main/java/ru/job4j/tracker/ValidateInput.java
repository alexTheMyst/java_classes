package ru.job4j.tracker;

import java.util.List;

/**
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.04.17
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Overrides method with error handling.
     * @param question text for user
     * @param range menu items numbers
     * @return chosen item id
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int result = -1;
        boolean invalid = true;
        do {
            try {
                result = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please choose correct menu number.");
            } catch (NumberFormatException nfe) {
                System.out.println("You entered invalid symbol. Please try again.");
            }
        } while (invalid);
        return result;
    }
}
