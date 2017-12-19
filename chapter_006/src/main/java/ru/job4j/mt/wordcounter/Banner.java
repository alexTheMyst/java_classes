package ru.job4j.mt.wordcounter;

/**
 * Stores and prints given banner.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 19.12.2017
 */
public class Banner implements Runnable {

    /**
     * Message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param message some message
     */
    public Banner(String message) {
        this.message = message;
    }

    /**
     * Prints message.
     */
    @Override
    public void run() {
        System.out.println("#####################");
        System.out.println(String.format("# %17s #", this.message));
        System.out.println("#####################");
    }
}
